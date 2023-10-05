package com.nohjason.momori.ui.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.ListButtonType
import com.nohjason.momori.component.button.MomoriButton
import com.nohjason.momori.component.button.MomoriIconButton
import com.nohjason.momori.component.button.MomoriListButton
import com.nohjason.momori.component.list.DividerType
import com.nohjason.momori.component.list.MomoriDivier
import com.nohjason.momori.component.theme.Body

@Composable
fun SettingScreen() {
    MomoriTopBar(
        titleText = "설정",
        enablePrimaryButton = true
    ) {
        MomoriListButton(onClick = { /*TODO*/ }, text = "내 계정")
        MomoriListButton(onClick = { /*TODO*/ }, text = "개인정보 수집 및 이용 약관")
        MomoriDivier(type = DividerType.Big)
        MomoriListButton(onClick = { /*TODO*/ }, text = "로그아웃", type = ListButtonType.Red)
    }
}

@Composable
@Preview
fun SettingPreview() {
    SettingScreen()
}