package com.nohjason.momori.component.button

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nohjason.momori.R
import com.nohjason.momori.component.theme.contentColorFor
import com.nohjason.momori.util.TAG

@Composable
fun MomoriIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    contentDescription: String = "",
    size: Dp = 20.dp,
    enable: Boolean = true,
    shape: Shape = CircleShape,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    color: Color? = null,
    flipX: Boolean = false,
    flipY: Boolean = false,
    type: ButtonType,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        MomoriButton(
            onClick = onClick,
            modifier = Modifier
                .size(size * 2),
            type = type,
            enabled = enable,
            shape = shape,
            border = border,
            contentPadding = contentPadding,
            interactionSource = interactionSource
        )
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = color.let { color }?: contentColorFor(type.buttonColor),
            modifier = Modifier
                .size(size)
                .offset(x = (size.div(2)), y = size.div(2))
                .graphicsLayer(
                    scaleY = if (flipY) -1f else 1f,
                    scaleX = if (flipX) -1f else 1f
                )
        )
    }
}

@Composable
@Preview
fun IconButtonPreview() {
    Column(modifier = Modifier.background(Color.White)) {
        MomoriIconButton(
            iconId = R.drawable.ic_back,
            type = ButtonType.Transparent,
            size = 100.dp) {
            Log.d(TAG, "IconButtonPreview: Transparent Icon Button")
        }
        MomoriIconButton(
            iconId = R.drawable.ic_back,
            type = ButtonType.Gray,
            size = 100.dp) {
            Log.d(TAG, "IconButtonPreview: Transparent Icon Button")
        }
        MomoriIconButton(
            iconId = R.drawable.ic_back,
            type = ButtonType.Mint,
            size = 100.dp) {
            Log.d(TAG, "IconButtonPreview: Transparent Icon Button")
        }
        MomoriButton(type = ButtonType.Mint, onClick = {
            // click 했을때
        })

        MomoriButton(type = ButtonType.Mint) {
            // click했을때
        }
    }
}
