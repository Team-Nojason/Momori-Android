package com.nohjason.momori.ui.main

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MainScreen() {
    val singapore = LatLng(35.6649411, 128.411552)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 16f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            position = singapore,
            title = "Sex",
            snippet = "Marker in Singapore"
        )
    }
}