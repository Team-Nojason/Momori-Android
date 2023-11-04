package com.nohjason.momori.ui.post

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriIconButton
import com.nohjason.momori.component.button.MomoriImageButton
import com.nohjason.momori.component.list.DividerType
import com.nohjason.momori.component.list.MomoriDivider
import com.nohjason.momori.component.text.MomoriTextField
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.ui.theme.MomoriTheme

@Composable
fun PostScreen(){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
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
            Row {

                MomoriIconButton(
                    iconId = R.drawable.ic_likes,
                    type = ButtonType.Transparent,
                    size = 25.dp
                ) {

                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                MomoriIconButton(
                    iconId = R.drawable.ic_post,
                    type = ButtonType.Transparent,
                    size = 25.dp
                ) {

                }
            }
            MomoriDivider(type = DividerType.Small)

            repeat(100) {
                Text("Item $it", modifier = Modifier.padding(2.dp))
            }
        }
        var text by remember { mutableStateOf("") }
        Column {
            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.rectangle),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterVertically)
                        .padding(end = 10.dp)
                )
                MomoriTextField(
                    value = text,
                    onValueChange = {text = it},
                    modifier = Modifier
                        .weight(1f),
                    singleLine = true
                )
                MomoriIconButton(
                    iconId = R.drawable.ic_post,
                    type = ButtonType.Mint,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp),
                    shape = MomoriTheme.shape.large,
                    size = 30.dp
                ) {
                    
                }
            }
        }
    }
}