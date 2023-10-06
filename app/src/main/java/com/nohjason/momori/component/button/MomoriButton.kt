package com.nohjason.momori.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.nohjason.momori.component.theme.Body
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.component.theme.contentColorFor
import com.nohjason.momori.ui.theme.MomoriTheme

sealed class ButtonType(val buttonColor: Color, val disableColor: Color = MomoriColor.Gray100) {
    object Mint: ButtonType(buttonColor = MomoriColor.Mint)
    object Gray: ButtonType(buttonColor = MomoriColor.Gray100)
    object DarkGray: ButtonType(buttonColor = MomoriColor.Gray700)
    object White: ButtonType(buttonColor = MomoriColor.White)
    object Transparent: ButtonType(buttonColor = MomoriColor.Transparent)
}

@Composable
fun MomoriButton(
    modifier: Modifier = Modifier,
    text: String = "",
    type: ButtonType,
    enabled: Boolean = true,
    shape: Shape = MomoriTheme.shape.medium,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
                contentColor = contentColorFor(type.buttonColor),
                containerColor = type.buttonColor,
                disabledContentColor = contentColorFor(type.disableColor),
                disabledContainerColor = type.disableColor
            ),
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        Body(text = text)
    }
}