package com.nohjason.momori.ui.profile

//import android.net.Uri
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nohjason.momori.R
import com.nohjason.momori.component.appbar.MomoriTopBar
import com.nohjason.momori.component.button.MomoriImageButton
import com.nohjason.momori.component.theme.Label
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.ui.root.key.NavGroup

@Composable
fun ProFileScreen(
    navController: NavController,
) {
    MomoriTopBar(
        titleText = "프로필",
        enablePrimaryButton = true,
        primaryButtonCallback = { navController.navigate(NavGroup.Main.MainMap.id) }
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
                var selectedImageUri by remember {
                    mutableStateOf<Uri?>(null)
                }
                val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.PickVisualMedia(),
                    onResult = { uri ->
                        selectedImageUri = uri
                    }
                )

//                Image(
//                    painter = painterResource(R.drawable.rectangle),
//                    contentDescription = null
//                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(selectedImageUri)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape)
                )


                MomoriImageButton(
                    iconId = R.drawable.group_44,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                    ) {
                    // 클릭시 갤러리 앱 열기
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
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