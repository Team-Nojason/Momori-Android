package com.nohjason.momori.component.button

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.momori.component.theme.Body
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.component.theme.contentColorFor
import com.nohjason.momori.ui.theme.MomoriTheme
import com.nohjason.momori.util.TAG

sealed class ButtonType(val buttonColor: Color, val disableColor: Color = MomoriColor.Gray100) {
    object Mint: ButtonType(buttonColor = MomoriColor.Mint)
    object Gray: ButtonType(buttonColor = MomoriColor.Gray100)
    object DarkGray: ButtonType(buttonColor = MomoriColor.Gray700)
    object White: ButtonType(buttonColor = MomoriColor.White)
    object Transparent: ButtonType(buttonColor = MomoriColor.Transparent)
    object TransparentLight: ButtonType(buttonColor = MomoriColor.Transparent)
    object Red: ButtonType(buttonColor = MomoriColor.Red)
}

@Composable
fun MomoriButton(
    modifier: Modifier = Modifier,
    text: String = "",
    type: ButtonType,
    enabled: Boolean = true,
    shape: Shape = MomoriTheme.shape.medium,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(
        defaultElevation = 4.dp,
        pressedElevation = 6.dp
    ),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {
    var contentColor = contentColorFor(backgroundColor = type.buttonColor)
    if (type == ButtonType.TransparentLight) contentColor = MomoriColor.Gray300
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
                contentColor = contentColor,
                containerColor = type.buttonColor,
                disabledContentColor = contentColorFor(type.disableColor),
                disabledContainerColor = type.disableColor
            ),
        elevation = if (type != ButtonType.Transparent) elevation else ButtonDefaults.buttonElevation(),
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        Body(text = text)
    }
}

@Preview
@Composable
fun ButtonPreview() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .padding(10.dp)
    ) {
        MomoriButton(type = ButtonType.DarkGray, text = "버튼입니다") {
            Log.d(TAG, "ButtonPreview: DarkGray")
        }
        MomoriButton(type = ButtonType.Gray, text = "버튼입니다") {
            Log.d(TAG, "ButtonPreview: Gray")
        }
        MomoriButton(type = ButtonType.Mint, text = "버튼입니다") {
            Log.d(TAG, "ButtonPreview: Mint")
        }
        MomoriButton(type = ButtonType.Transparent, text = "버튼입니다") {
            Log.d(TAG, "ButtonPreview: Transparent")
        }
        MomoriButton(type = ButtonType.White, text = "버튼입니다") {
            Log.d(TAG, "ButtonPreview: White")
        }
    }

}