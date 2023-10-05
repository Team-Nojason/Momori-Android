package com.nohjason.momori.component.appbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nohjason.momori.R
import com.nohjason.momori.component.button.ButtonType
import com.nohjason.momori.component.button.MomoriIconButton
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.component.theme.Title

@Composable
fun MomoriTopBar(
    modifier: Modifier = Modifier,
    titleText: String,
    enablePrimaryButton: Boolean = false,
    primaryButtonCallback: () -> Unit = {},
    @DrawableRes primaryIconId: Int = R.drawable.ic_back,
    color: Color = MomoriColor.White,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            if (enablePrimaryButton)
                MomoriIconButton(
                    modifier = Modifier,
                    iconId = primaryIconId,
                    contentDescription = "to back",
                    onClick = primaryButtonCallback,
                    type = ButtonType.Transparent
                )
            Spacer(modifier = Modifier.width(8.dp))
            Title(text = titleText)
            Spacer(modifier = Modifier.weight(1f))
        }
        content()
    }
}