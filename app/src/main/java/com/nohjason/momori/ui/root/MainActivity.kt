package com.nohjason.momori.ui.root

//import com.nohjason.momori.ui.upload.UpLoadScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kakao.sdk.common.util.Utility
import com.nohjason.momori.ui.onboard.OnBoardScreen1
import com.nohjason.momori.ui.root.key.KeyArray
import com.nohjason.momori.ui.theme.MomoriTheme
import com.nohjason.momori.util.TAG

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hashKey = Utility.getKeyHash(this)
        Log.d(TAG, "onCreate: $hashKey")
        setContent {
            val navController = rememberNavController()

            MomoriTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){

//                    KeyArray(navController)
                    OnBoardScreen1()
//                    MainScreen()
//                    UpLoadScreen()
//                    ProFileScreen()
                }
            }
        }
    }
}