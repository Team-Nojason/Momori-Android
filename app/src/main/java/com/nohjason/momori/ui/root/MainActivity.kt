package com.nohjason.momori.ui.root

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kakao.sdk.common.util.Utility
import com.nohjason.momori.ui.main.MainScreen
import com.nohjason.momori.ui.theme.MomoriTheme
import com.nohjason.momori.util.TAG
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hashKey = Utility.getKeyHash(this)
        Log.d(TAG, "onCreate: $hashKey")
        setContent {
            MomoriTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
//                    OnBoardScreen()
                    MainScreen()
                }
            }
        }
    }
}