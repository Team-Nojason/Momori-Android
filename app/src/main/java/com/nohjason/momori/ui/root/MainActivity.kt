package com.nohjason.momori.ui.root

//import com.nohjason.momori.ui.upload.UpLoadScreen
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.kakao.sdk.common.util.Utility
import com.nohjason.momori.application.MomoriApp
import com.nohjason.momori.application.PreferenceManager
import com.nohjason.momori.ui.onboard.OnBoardScreen2
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
                    val viewModel: MainViewModel = viewModel()
                    val sideEffect by viewModel.sideEffect.collectAsState()
                    when (sideEffect) {
                        MainState.InvalidUser -> MomoriApp.prefs.isLogin = false
                        MainState.Success -> {
                            Log.d(TAG, "MainActivity - onCreate() called")
                            MomoriApp.prefs.isLogin = true
                        }
                        MainState.None -> {}
                    }
                    viewModel.checkLogin()
                    MomoriApp.prefs = PreferenceManager(applicationContext)

                    KeyArray(navController)
//                    OnBoardScreen2()
//                    MainScreen()
//                    UpLoadScreen()
//                    ProFileScreen()
                }
            }
        }
    }
}