package com.nohjason.momori.ui.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriButton

@Composable
fun OnBoardScreen2(){
    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
        ,
        verticalArrangement = Arrangement.Bottom,
    ){
        MomoriButton(
            type = ButtonType.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            ,
            text = "완료하기",
        ) {
            ///////클릭/////////
        }
    }
}