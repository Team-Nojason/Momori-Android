package com.nohjason.momori.ui.root.key

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nohjason.momori.ui.main.MainScreen
import com.nohjason.momori.ui.onboard.OnBoardScreen
import com.nohjason.momori.ui.profile.ProFileScreen
import com.nohjason.momori.ui.setting.SettingScreen
import com.nohjason.momori.ui.upload.UpLoadScreen

@Composable
fun KeyArray(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Key.MainScreen.name,
    ) {
        composable(route = Key.MainScreen.name) {
            MainScreen(navController = navController)
        }
        composable(route = Key.OnBoardScreen.name){
            OnBoardScreen(navController = navController)
        }
        composable(route = Key.ProFileScreen.name){
            ProFileScreen(navController = navController)
        }
        composable(route = Key.SettingScreen.name){
            SettingScreen(navController = navController)
        }
        composable(route = Key.UpLoadScreen.name){
            UpLoadScreen(navController = navController)
        }
    }
}