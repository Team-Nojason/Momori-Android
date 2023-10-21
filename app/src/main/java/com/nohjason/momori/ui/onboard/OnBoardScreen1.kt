package com.nohjason.momori.ui.onboard

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.nohjason.momori.R
import com.nohjason.momori.component.button.MomoriImageButton
import com.nohjason.momori.component.theme.Headline
import com.nohjason.momori.component.theme.MomoriColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardScreen1(){
    var text by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.padding(horizontal = 20.dp)
    ){
        Spacer(modifier = Modifier.height(30.dp))
        Headline(text = "회원 정보 입력")
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "아이디", fontSize = 6.em)
        TextField(
            value = text,
            onValueChange = {text = it},
            singleLine = true,
            modifier = Modifier
                .background(MomoriColor.Transparent),
            colors = TextFieldDefaults
                .textFieldColors(
                    containerColor = MomoriColor.Transparent,
                    focusedIndicatorColor = MomoriColor.Black,
                    cursorColor = MomoriColor.Black,
                ),
            maxLines = 1,

        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "프로필", fontSize = 6.em)
        Spacer(modifier = Modifier.height(40.dp))
        Box {
            Image(
                painter = painterResource(R.drawable.rectangle),
                contentDescription = null,
                modifier = Modifier.size(90.dp)
            )
            Column (
                modifier = Modifier.align(Alignment.BottomEnd)
            ){
                MomoriImageButton(
                    iconId = R.drawable.group_44,
                ) {

                }
            }

        }
    }
}