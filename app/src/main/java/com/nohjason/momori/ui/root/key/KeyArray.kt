package com.nohjason.momori.ui.root.key

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nohjason.momori.application.MomoriApp
import com.nohjason.momori.application.PreferenceManager
import com.nohjason.momori.ui.main.MainScreen
import com.nohjason.momori.ui.onboard.OnBoardScreen
import com.nohjason.momori.ui.post.PostScreen
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
        composable(route = NavGroup.Main.MainMap.id) {
            MainScreen(navController = navController)
        }
        composable(route = NavGroup.Main.On_Board_1.id){
            OnBoardScreen(navController = navController)
        }
        composable(route = NavGroup.Setting.Setting_Profile_View.id){
            ProFileScreen(navController = navController)
        }
        composable(route = NavGroup.Setting.Setting_View.id){
            SettingScreen(navController = navController)
        }
        composable(route = NavGroup.Peed.Upload_Peed.id){
            UpLoadScreen(navController = navController)
        }
        composable(route = NavGroup.Peed.Watch_Peed.id){
            PostScreen(navController = navController)
        }
    }
}

private fun getStartDestination() =
    NavGroup.Main.MainMap.id

//    if (MomoriApp.prefs.isLogin) Key.MainScreen.name else Key.OnBoardScreen.name