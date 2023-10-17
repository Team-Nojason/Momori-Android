package com.nohjason.momori.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Looper
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
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
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.shape.DotPoints
import com.kakao.vectormap.shape.Polygon
import com.kakao.vectormap.shape.PolygonOptions
import com.kakao.vectormap.shape.Polyline
import com.kakao.vectormap.shape.PolylineOptions
import com.kakao.vectormap.shape.PolylineStyles
import com.kakao.vectormap.shape.PolylineStylesSet
import com.nohjason.momori.R
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriButton
import com.nohjason.momori.component.button.MomoriIconButton
import com.nohjason.momori.component.dialog.MomoriDialog
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.ui.root.key.Key
import com.nohjason.momori.ui.setting.SettingScreen
import com.nohjason.momori.ui.theme.MomoriTheme
import com.nohjason.momori.util.PermissionUtil.requestPermissions
import com.nohjason.momori.util.TAG

private val locationPermissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController,) {
    val context = LocalContext.current
    var currentLocation by remember {
        mutableStateOf<LatLng?>(null)
    }


    /**
     * 권한
     */
    // 위치 권한이 있는가
    var isAllowLocationPermission by rememberSaveable { mutableStateOf(false) }

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
    val fusedLocationClient by remember {
        mutableStateOf(
            LocationServices.getFusedLocationProviderClient(
                context
            )
        )
    }


    // 마지막 위치 불러오는 함수
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locations: LocationResult) {
            for (location in locations.locations) {
                currentLocation = LatLng.from(location.latitude, location.longitude)
                Log.d(TAG, "w - ${location.latitude} g - ${location.longitude} - onLocationResult() called")
            }
        }
    }


    fun getAllowLocationPermission() = ActivityCompat.checkSelfPermission(
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

    val sheetState = rememberModalBottomSheetState()
    var showPostBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(isAllowLocationPermission) {
        if (isAllowLocationPermission) {
            locationUpdate()
        }
    }

    LaunchedEffect(true) {
        requestPermissions(context, locationPermissions, launcherMultiplePermissions)
    }

    var currentLabel by remember {
        mutableStateOf<Label?>(null)
    }
    var circle by remember {
        mutableStateOf<Polygon?>(null)
    }
    var circleStroke by remember {
        mutableStateOf<Polyline?>(null)
    }

    var map by remember {
        mutableStateOf<KakaoMap?>(null)
    }

    LaunchedEffect(currentLocation) {
        if (currentLocation != null) {
            val temp = LatLng.from(currentLocation!!.latitude + 0.003f, currentLocation!!.longitude - 0.01)
//            currentLabel?.moveTo(currentLocation)
//            circle?.setPosition(currentLocation)
//            circleStroke?.setPosition(currentLocation)
            currentLabel?.moveTo(temp)
            circle?.setPosition(temp)
            circleStroke?.setPosition(temp)

        }
    }

    /**
     * view
     */

    // post
    if (showPostBottomSheet && isAllowLocationPermission)
        ModalBottomSheet(
            onDismissRequest = {
                showPostBottomSheet = false
            },
            sheetState = sheetState
        ) {
            SettingScreen(navController)
        }

    // request permission
    if (!isAllowLocationPermission)
        MomoriDialog(
            title = "잠시만요...",
            description = "권한이 있어야 앱을 이용할 수 있습니다",
            primaryButton = {
                MomoriButton(text = "권한 요청", type = ButtonType.Mint) {
                    requestPermissions(context, locationPermissions, launcherMultiplePermissions)
                }
            },
            onDismiss = {

            }
        ) {
            MomoriButton(text = "설정으로", type = ButtonType.TransparentLight) {
                val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + context.packageName))
                context.startActivity(intent)

            }
        }

    // main
    Box {
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
                        val dgsw = LatLng.from(35.6633349,128.4118222)
                        val update = CameraUpdateFactory.newCenterPosition(dgsw);
                        kakaoMap.moveCamera(update)
                        map = kakaoMap

                        val label = kakaoMap.labelManager?.layer?.addLabel(
                                LabelOptions.from(dgsw)
                                    .setStyles(R.drawable.ic_current)
                            )

                        currentLabel = label

                        val innerCircle = kakaoMap.shapeManager?.layer?.addPolygon(getCirclePolygonOptions(dgsw, 400, MomoriColor.Mint.copy(alpha = 0.2f)))
                        val outerCircle = kakaoMap.shapeManager?.layer?.addPolyline(getCirclePolylineOptions(dgsw, 400, MomoriColor.Mint))

                        circle = innerCircle
                        circleStroke = outerCircle
                    }
                })
                mapView
            },
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    MomoriIconButton(
                        iconId = R.drawable.ic_setting,
                        shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp),
                        type = ButtonType.White,
                        size = 30.dp,
                        color = MomoriColor.Mint
                    ) {
                        navController.navigate(Key.SettingScreen.name)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    MomoriIconButton(
                        iconId = R.drawable.ic_profile,
                        shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp),
                        type = ButtonType.White,
                        size = 30.dp,
                        color = MomoriColor.Mint
                    ) {
                        navController.navigate(Key.ProFileScreen.name)
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    MomoriIconButton(
                        modifier = Modifier
                            .padding(bottom = 44.dp),
                        iconId = R.drawable.ic_post,
                        shape = MomoriTheme.shape.large,
                        size = 35.dp,
                        type = ButtonType.Mint
                    ) {
                        Log.d(TAG, "MainScreen: click post")
                    }
                    Spacer(modifier = Modifier.weight(1f))

                }
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    MomoriIconButton(
                        modifier = Modifier
                            .padding(end = 40.dp),
                        iconId = R.drawable.ic_go,
                        type = ButtonType.White,
                        size = 30.dp,
                        color = MomoriColor.Mint) {
                        if (currentLocation != null) {
                            val update = CameraUpdateFactory.newCenterPosition(currentLocation, 15)
                            map?.moveCamera(update)

                        }
                    }
                }

            }

        }
    }


}
private fun getCirclePolylineOptions(center: LatLng?, radius: Int, color: androidx.compose.ui.graphics.Color): PolylineOptions {
    return PolylineOptions.from().setDotPoints(DotPoints.fromCircle(center, radius.toFloat()))
        .setStylesSet(
            PolylineStylesSet.from(
                PolylineStyles.from(
                    10f,
                    color.toArgb()
                )
            )
        )
}

private fun getCirclePolygonOptions(center: LatLng?, radius: Int, color: androidx.compose.ui.graphics.Color): PolygonOptions {
    return PolygonOptions.from(
        DotPoints.fromCircle(center, radius.toFloat()),
        color.toArgb()
    )
}