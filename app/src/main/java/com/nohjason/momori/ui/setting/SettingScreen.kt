package com.nohjason.momori.ui.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriButton
import com.nohjason.momori.component.button.MomoriIconButton
import com.nohjason.momori.component.theme.Body

@Composable
fun SettingScreen() {
    MomoriTopBar(
        titleText = "설정",
        enablePrimaryButton = true
    ) {
        Body(text = "그래그래 이것이 설정이다")
        MomoriButton(onClick = { /*TODO*/ }, type = ButtonType.Mint, text = "클릭하셈")
        MomoriIconButton(iconId = R.drawable.ic_back, type = ButtonType.Mint) {
            
        }
    }
}

@Composable
@Preview
fun SettingPreview() {
    SettingScreen()
}