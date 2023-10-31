package com.nohjason.momori.ui.post

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriIconButton
import com.nohjason.momori.component.button.MomoriImageButton
import com.nohjason.momori.component.text.MomoriTextField
import com.nohjason.momori.component.theme.MomoriColor

@Composable
fun PostScreen(){
    Column {
        Row(
            modifier = Modifier
                .height(60.dp)
                .padding(start = 9.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.rectangle),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(40.dp)
            )
            MomoriTopBar(titleText = "구지초 짱",
                secondaryContent = {
                    Row (

                    ){
                        Image(
                            painter = painterResource(R.drawable.ic_location),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = 10.dp)
                        )
                        Text(
                            text = "11시간 전",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = 5.dp),
                            color = MomoriColor.Gray300,
                            fontSize = 4.em
                        )
                        MomoriIconButton(
                            iconId = R.drawable.ic_cancel,
                            type = ButtonType.Transparent,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {

                        }
                    }
                }) {

            }
        }
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Row (
            modifier = Modifier
                .drawBehind {
                    val borderSize = 1.dp.toPx()
                    drawLine(
                        color = MomoriColor.Black,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                }
        ){
            MomoriImageButton(
                iconId = R.drawable.ic_likes,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(10.dp)
            ) {
                
            }
            MomoriImageButton(
                iconId = R.drawable.ic_chat,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {

            }
            Spacer(modifier = Modifier.weight(1f))
            MomoriImageButton(
                iconId = R.drawable.ic_post,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 10.dp)
            ) {
                
            }
        }
        var text by remember { mutableStateOf("") }

        MomoriTextField(
            value = text,
            onValueChange = {text = it},
        )
    }
}