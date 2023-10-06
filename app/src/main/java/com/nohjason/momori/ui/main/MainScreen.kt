package com.nohjason.momori.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriButton
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.util.PermissionUtil.requestPermissions
import com.nohjason.momori.util.TAG
import net.daum.mf.map.api.MapView

private val locationPermissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

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
    val fusedLocationClient by remember { mutableStateOf(LocationServices.getFusedLocationProviderClient(context)) }


    // 마지막 위치 불러오는 함수
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locations: LocationResult) {
            for (location in locations.locations) {
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
            modifier = Modifier.fillMaxSize(), // Occupy the max size in the Compose UI tree
            factory = { context ->
                // Creates view
                MapView(context).apply {

                }
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