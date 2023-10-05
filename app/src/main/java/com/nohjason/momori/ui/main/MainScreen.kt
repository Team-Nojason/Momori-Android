package com.nohjason.momori.ui.main

import com.google.android.gms.location.FusedLocationProviderClient

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.nohjason.momori.util.PermissionUtil.checkAndRequestPermissions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var fusedLocationClient: FusedLocationProviderClient

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val fusedLocationClient by remember { mutableStateOf(LocationServices.getFusedLocationProviderClient(context)) }

    val coroutine = rememberCoroutineScope()

    val singapore = LatLng(35.6649411, 128.411552)

    LaunchedEffect(true) {
        coroutine.launch {
            while (true) {
                val a = ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                val b = ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                if (a == PackageManager.PERMISSION_GRANTED && b == PackageManager.PERMISSION_GRANTED
                ) {
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location : Location? ->
                            Log.d("location", location.toString())
                        }
//                    Log.d("location", "location.toString()")
                }
                Log.d("location", "a - $a b - $b")
                delay(1000)
            }
        }


    }

//    val polyline = map.addPolyline(polylineOptions)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 16f)
    }
    /** 요청할 권한 **/
    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    var a by remember { mutableStateOf(false) }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        /** 권한 요청시 동의 했을 경우 **/
        if (areGranted) {
            a = false
            Log.d("test5", "권한이 동의되었습니다.")
        }
        /** 권한 요청시 거부 했을 경우 **/
        else {
            a = true
            Log.d("test5", "권한이 거부되었습니다.")
        }
    }

    if (a) {
        checkAndRequestPermissions(context, permissions, launcherMultiplePermissions)
    }

    LaunchedEffect(true) {
        checkAndRequestPermissions(context, permissions, launcherMultiplePermissions)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            position = singapore,
            title = "wow",
            snippet = "Marker in Singapore"
        )
    }
}