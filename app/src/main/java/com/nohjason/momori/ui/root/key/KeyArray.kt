package com.nohjason.momori.ui.root.key

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nohjason.momori.application.MomoriApp
import com.nohjason.momori.application.PreferenceManager
import com.nohjason.momori.ui.main.MainScreen
import com.nohjason.momori.ui.onboard.OnBoardScreen
import com.nohjason.momori.ui.profile.ProFileScreen
import com.nohjason.momori.ui.setting.SettingScreen
import com.nohjason.momori.ui.upload.UpLoadScreen

@Composable
fun KeyArray(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = getStartDestination(),
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

private fun getStartDestination() =
    if (MomoriApp.prefs.isLogin) Key.MainScreen.name else Key.OnBoardScreen.name