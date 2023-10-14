package com.nohjason.momori.component.button

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nohjason.momori.R
import com.nohjason.momori.component.modifier.mmrClickable
import com.nohjason.momori.ui.theme.MomoriTheme
import com.nohjason.momori.util.TAG

@Composable
fun MomoriImageButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    contentDescription: String = "",
    size: Dp = Dp.Unspecified,
    enable: Boolean = true,
    shape: Shape = MomoriTheme.shape.medium,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    contentScale: ContentScale = ContentScale.Fit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    flipX: Boolean = false,
    flipY: Boolean = false,
    type: ButtonType = ButtonType.Transparent,
    onClick: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier
                .size(size)
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
        MomoriImageButton(iconId = R.drawable.google, size = 50.dp) {
            Log.d(TAG, "IconButtonPreview: Google Image Button")
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        MomoriImageButton(iconId = R.drawable.kaka, size = 50.dp) {
            Log.d(TAG, "ImageButtonPreview: Kakao Image Button")
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        MomoriImageButton(iconId = R.drawable.naver, size = 50.dp) {
            Log.d(TAG, "ImageButtonPreview: Kakao Image Button")
        }
    }

}
