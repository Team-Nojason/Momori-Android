package com.nohjason.momori.ui.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.theme.Body

@Composable
fun SettingScreen() {
    MomoriTopBar(
        titleText = "설정"
    ) {
        Body(text = "그래그래 이것이 설정이다")
    }
}

//@Composable
//@Preview()