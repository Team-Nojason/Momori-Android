package com.nohjason.momori.ui.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nohjason.momori.ui.main.MainScreen
//import com.nohjason.momori.ui.main.checkAndRequestPermissions
import com.nohjason.momori.ui.onboard.OnBoardScreen
import com.nohjason.momori.ui.theme.MomoriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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