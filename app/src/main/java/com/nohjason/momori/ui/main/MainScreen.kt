package com.nohjason.momori.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.shape.DimScreenLayer
import com.kakao.vectormap.shape.DotPoints
import com.kakao.vectormap.shape.Polygon
import com.kakao.vectormap.shape.PolygonOptions
import com.kakao.vectormap.shape.PolygonStyle
import com.kakao.vectormap.shape.ShapeManager
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriButton
import com.nohjason.momori.util.PermissionUtil.requestPermissions
import com.nohjason.momori.util.TAG


private val locationPermissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

private lateinit var dimScreenLayer: DimScreenLayer
private lateinit var shapeManager: ShapeManager

var circleList = arrayListOf<Polygon>()

@Composable
fun MainScreen() {
    val context = LocalContext.current

    val coroutine = rememberCoroutineScope()

    var currentLocation by remember {
        mutableStateOf<LatLng?>(null)
    }

    LaunchedEffect(currentLocation) {


            val circles = getDimCirclePolyline(currentLocation?: return@LaunchedEffect, 133, 30)
            circleList.forEach {
                shapeManager.layer.remove(it)
            }
            circleList = circles
    }

    /**
     * 권한
     */
    // 위치 권한이 있는가
    var isAllowLocationPermission by remember { mutableStateOf(false) }

    // 권한 결과 처리
    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        if (permissionsMap.values.isEmpty()) return@rememberLauncherForActivityResult
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        isAllowLocationPermission = areGranted
    }

    /**
     * 위치 조회
     */
    // 내 위치 조회 요청 클라이언트
    val fusedLocationClient by remember { mutableStateOf(LocationServices.getFusedLocationProviderClient(context)) }


    // 마지막 위치 불러오는 함수
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locations: LocationResult) {
            for (location in locations.locations) {
                currentLocation = LatLng.from(location.latitude, location.longitude)
                Log.d(TAG, "w - ${location.latitude} g - ${location.longitude} - onLocationResult() called")
            }
        }
    }

    fun getAllowLocationPermission()
    = ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    // 1초 마다 위치 업데이트 되었는지 체크
    fun locationUpdate() {
        if (!getAllowLocationPermission()) return

        val locationRequest = LocationRequest.Builder(1000) // 1초마다 체크
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .build()

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    LaunchedEffect(isAllowLocationPermission) {
        if (isAllowLocationPermission) {
            locationUpdate()
        }
    }

    LaunchedEffect(true) {
        requestPermissions(context, locationPermissions, launcherMultiplePermissions)
    }

    // view
    if (isAllowLocationPermission)
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                val mapView = MapView(context)
                mapView.start(object : MapLifeCycleCallback() {
                    override fun onMapDestroy() {
                        // 지도 API 가 정상적으로 종료될 때 호출됨
                        Log.d(TAG, " - onMapDestroy() called")
                    }

                    override fun onMapError(error: Exception) {
                        // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                        Log.d(TAG, "${error} - onMapError() called")
                    }
                }, object : KakaoMapReadyCallback() {
                    override fun onMapReady(kakaoMap: KakaoMap) {
                        // 인증 후 API 가 정상적으로 실행될 때 호출됨
                        Log.d(TAG, " - onMapReady() called")

                        dimScreenLayer = kakaoMap.dimScreenManager?.dimScreenLayer!!
                        shapeManager = kakaoMap.shapeManager!!

                        dimScreenLayer.setVisible(true)

                        val dgsw = LatLng.from(35.6632493, 128.4141269)
                        val dgswPoints = CameraUpdateFactory.newCenterPosition(dgsw)
                        kakaoMap.moveCamera(dgswPoints)

                        val arr = getDimCirclePolyline(dgsw, 250, 60)
                        circleList = arr
                    }
                })
                mapView
            },
        )
        else
            Column {
                MomoriButton(
                    text = "권한 요청",
                    type = ButtonType.Mint
                ) {
                    requestPermissions(
                        context,
                        locationPermissions,
                        launcherMultiplePermissions
                    )
                }
            }
}

private const val dimScreenLayerOpacity = 95.0f

private fun getDimCirclePolyline(
    latLng: LatLng,
    radius: Int,
    stroke: Int
): ArrayList<Polygon> {
    val arr = arrayListOf<Polygon>()

    var options: PolygonOptions = PolygonOptions.from(DotPoints.fromCircle(LatLng.from(latLng.latitude, latLng.longitude), radius.toFloat()), PolygonStyle.from(Color.TRANSPARENT))
    val circle = dimScreenLayer.addPolygon(options)
    arr.add(circle!!)

    val innerRadius: Int = radius - stroke

    for (i in innerRadius..radius) {
        options = PolygonOptions.from(
            DotPoints.fromCircle(
                LatLng.from(latLng.latitude, latLng.longitude), i.toFloat()
            ).setHoleCircle(i - 1.0f),
            PolygonStyle.from(Color.argb(((i - innerRadius).toFloat() / (stroke).toFloat() * dimScreenLayerOpacity).toInt(), 0, 0, 0)) // 0 ~ 63
        )

        val polyline = shapeManager.layer.addPolygon(options)
        arr.add(polyline)
    }
    return arr
}