package com.nohjason.momori.ui.Upload

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.MomoriImageButton

@Composable
fun UpLoadScreen(){
    MomoriTopBar(titleText = "추억저장") {
        MomoriImageButton(iconId = R.drawable.naver) {

        }
    }
}