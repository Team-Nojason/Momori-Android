package com.nohjason.momori.component.button

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nohjason.momori.R
import com.nohjason.momori.component.modifier.mmrClickable
import com.nohjason.momori.util.TAG

@Composable
fun MomoriImageButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    contentDescription: String = "",
    width: Dp = Dp.Unspecified,
    height: Dp = Dp.Unspecified,
    scale: Float = 1f,
    contentScale: ContentScale = ContentScale.Fit,
    flipX: Boolean = false,
    flipY: Boolean = false,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier
                .size(width, height)
                .scale(scale)
                .graphicsLayer(
                    scaleY = if (flipY) -1f else 1f,
                    scaleX = if (flipX) -1f else 1f
                )
                .mmrClickable(
                    rippleEnable = false
                ) {
                    onClick()
                }
        )
    }
}

@Composable
@Preview
fun ImageButtonPreview() {
    Row {
        MomoriImageButton(iconId = R.drawable.google) {
            Log.d(TAG, "IconButtonPreview: Google Image Button")
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        MomoriImageButton(iconId = R.drawable.kakao) {
            Log.d(TAG, "ImageButtonPreview: Kakao Image Button")
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        MomoriImageButton(iconId = R.drawable.naver) {
            Log.d(TAG, "ImageButtonPreview: Kakao Image Button")
        }
    }

}
