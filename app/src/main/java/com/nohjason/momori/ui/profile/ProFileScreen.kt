package com.nohjason.momori.ui.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.MomoriImageButton

@Composable
fun ProFile() {
    MomoriTopBar(titleText = "프로필") {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column (
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ){
                Image(painter = painterResource(R.drawable.rectangle), contentDescription = null)
                MomoriImageButton(
                    iconId = R.drawable.naver,
                    modifier = Modifier.align(Alignment.End)
                    ) {

                }
            }
            Row (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){
                MomoriImageButton(
                    iconId = R.drawable.img_feed,
                    contentScale = ContentScale.FillBounds,
                ) {
                    Log.d("wow", "click")
                }
                Spacer(modifier = Modifier.width(9.dp))
                MomoriImageButton(
                    iconId = R.drawable.img_feed,
                    contentScale = ContentScale.FillBounds,
                ) {
                    Log.d("wow", "click")
                }
            }
            Spacer(modifier = Modifier.height(9.dp))
            Row (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){
                MomoriImageButton(
                    iconId = R.drawable.img_feed,
                    contentScale = ContentScale.FillBounds,
                ) {
                    Log.d("wow", "click")
                }
                Spacer(modifier = Modifier.width(9.dp))
                MomoriImageButton(
                    iconId = R.drawable.img_feed,
                    contentScale = ContentScale.FillBounds,
                ) {
                    Log.d("wow", "click")
                }
            }
        }

    }
}
