package com.nohjason.momori.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.momori.R
import com.nohjason.momori.component.theme.Body
import com.nohjason.momori.component.theme.Label
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.component.theme.contentColorFor
import com.nohjason.momori.ui.theme.MomoriTheme

sealed class ListButtonType(val contentColor: Color) {
    object Normal: ListButtonType(contentColor = MomoriColor.Black)
    object Red: ListButtonType(contentColor = MomoriColor.Red)
}

@Composable
fun MomoriListButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    shape: Shape = MomoriTheme.shape.medium,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    type: ListButtonType = ListButtonType.Normal,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 12.dp,
        vertical = 8.dp
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColorFor(ButtonType.Transparent.buttonColor),
            containerColor = ButtonType.Transparent.buttonColor,
            disabledContentColor = contentColorFor(ButtonType.Transparent.disableColor),
            disabledContainerColor = ButtonType.Transparent.disableColor
        ),
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Label(
                text = text,
                textColor = type.contentColor
            )
            Spacer(modifier = Modifier.weight(1f))
            MomoriIconButton(
                modifier = Modifier,
                iconId = R.drawable.ic_back,
                type = ButtonType.Transparent,
                size = 16.dp,
                flipX = true
            ) {}
        }
    }
}

@Preview
@Composable
fun ListButtonPreview() {
    Column(
        Modifier.background(Color.White)
    ) {
        MomoriListButton(onClick = { /*TODO*/ }, text = "gd", type = ListButtonType.Normal)
        MomoriListButton(onClick = { /*TODO*/ }, text = "gdg", type = ListButtonType.Normal)
        MomoriListButton(onClick = { /*TODO*/ }, text = "qweqwe", type = ListButtonType.Normal)
        MomoriListButton(onClick = { /*TODO*/ }, text = "asdff", type = ListButtonType.Normal)
        MomoriListButton(onClick = { /*TODO*/ }, text = "로그아웃", type = ListButtonType.Red)

    }

}