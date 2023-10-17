package com.nohjason.momori.ui.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.MomoriImageButton
import com.nohjason.momori.component.theme.Label
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.ui.root.key.Key

@Composable
fun ProFileScreen(
    navController: NavController,
) {
    MomoriTopBar(
        titleText = "프로필",
        enablePrimaryButton = true,
        primaryButtonCallback = { navController.navigate(Key.MainScreen.name) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Box (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){
                Image(painter = painterResource(R.drawable.rectangle), contentDescription = null)
                MomoriImageButton(
                    iconId = R.drawable.group_44,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                    ) {

                }
            }
            Label(
                text = "@nohjason",
                textColor = MomoriColor.Gray300,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(60.dp))
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
                    iconId = R.drawable.group_55,
                    contentScale = ContentScale.FillBounds,
                ) {
                    Log.d("wow", "click")
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ){
                MomoriImageButton(
                    iconId = R.drawable.img_friend,
                    contentScale = ContentScale.FillBounds,
                ) {
                    Log.d("wow", "click")
                }
                Spacer(modifier = Modifier.width(9.dp))
                MomoriImageButton(
                    iconId = R.drawable.profileimg,
                    contentScale = ContentScale.FillBounds,
                ) {
                    Log.d("wow", "click")
                }
            }
        }

    }
}
