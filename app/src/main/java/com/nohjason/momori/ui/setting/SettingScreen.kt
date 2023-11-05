package com.nohjason.momori.ui.setting

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.ListButtonType
import com.nohjason.momori.component.button.MomoriListButton
import com.nohjason.momori.component.list.DividerType
import com.nohjason.momori.component.list.MomoriDivider
import com.nohjason.momori.ui.root.key.NavGroup

//import com.nohjason.momori.ui.root.key.Key

@Composable
fun SettingScreen(
    navController: NavController,
) {
    MomoriTopBar(
        titleText = "설정",
        enablePrimaryButton = true,
        primaryButtonCallback = {navController.navigate(NavGroup.Main.MainMap.id)}
    ) {
        MomoriListButton(onClick = { /*TODO*/ }, text = "내 계정")
        MomoriListButton(onClick = { /*TODO*/ }, text = "개인정보 수집 및 이용 약관")
        MomoriDivider(type = DividerType.Large)
        MomoriListButton(onClick = { /*TODO*/ }, text = "로그아웃", type = ListButtonType.Red)
    }
}

//@Composable
//@Preview
//fun SettingPreview() {
//    SettingScreen()
//}