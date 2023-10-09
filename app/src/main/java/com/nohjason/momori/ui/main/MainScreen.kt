package com.nohjason.momori.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Paint.Align
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriButton
import com.nohjason.momori.component.button.MomoriIconButton
import com.nohjason.momori.component.overlay.MomoriFlashOverlay
import com.nohjason.momori.util.PermissionUtil.requestPermissions
import com.nohjason.momori.util.TAG


private val locationPermissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current

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
                Log.d(
                    TAG,
                    "w - ${location.latitude} g - ${location.longitude} - onLocationResult() called"
                )
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
        MomoriFlashOverlay(radius = 133.dp) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    val a = MapView(context)
                    a.start(object : MapLifeCycleCallback() {
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
                        }
                    });
                    a
                },
            )
        }
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
    Box (
        modifier = Modifier
            .padding(bottom = 20.dp)
    ){
        Row (modifier = Modifier
            .align(Alignment.BottomCenter)
        ){
            MomoriButton(
                type = ButtonType.DarkGray,
                modifier = Modifier
                    .height(42.dp)
                    .width(62.dp)
            ) {

            }
            MomoriButton(
                type = ButtonType.Mint,
                modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp)
                    .height(42.dp)
                    .width(62.dp)
            ) {

            }
            MomoriButton(
                type = ButtonType.Gray,
                modifier = Modifier
                    .height(42.dp)
                    .width(62.dp)
            ) {

            }
        }
//        FloatingActionButton(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .width(62.dp)
//                .height(42.dp)
//            ,
//            onClick = { /*TODO*/ },
//        ) {
//
//        }
    }
}