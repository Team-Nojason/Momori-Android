package com.nohjason.momori.ui.upload

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.navigation.NavController
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.appbar.TopBarPrevie1
import com.nohjason.momori.component.button.MomoriImageButton
import com.nohjason.momori.ui.root.key.Key

@Composable
fun UpLoadScreen(
    navController: NavController,
) {
    val width = 20.dp
    val height = 20.dp

    var value by remember { mutableStateOf("") }
    var value1 by remember { mutableStateOf("") }

    Column {
        MomoriTopBar(titleText = "추억 저장", secondaryContent = {
            MomoriImageButton(iconId = R.drawable.ic_post) {
                Log.d("wow", "click")
            }
            Spacer(modifier = Modifier.width(10.dp))
            MomoriImageButton(iconId = R.drawable.ic_cancel) {
//                Log.d("wow", "click")
                navController.navigate(Key.MainScreen.name)
            }
        }) {
            Column{
                Box {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp)
                            .padding(top = 30.dp)
                            .aspectRatio(16 / 9f),
                        contentScale = ContentScale.FillBounds
                    )
                    MomoriImageButton(
                        width = width,
                        height = height,
                        iconId = R.drawable.naver,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 50.dp)
                            .padding(top = 30.dp)
                            .offset(x = width / 2, y = -height / 2)
                    ) {
                        Log.d("mom", "click")
                    }
                }
                TextField(
                    value = value,
                    onValueChange = { value = it },
                    maxLines = 1,
                    singleLine = true,
                    label = { Text(text = "제목")},
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()

                )
                TextField(
                    value = value1,
                    onValueChange = { value1 = it },
                    label = { Text(text = "내용")},
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()
                    )
            }
        }
    }
}