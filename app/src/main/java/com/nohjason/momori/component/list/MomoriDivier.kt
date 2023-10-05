package com.nohjason.momori.component.list

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nohjason.momori.component.theme.MomoriColor

sealed class DividerType(val thickness: Dp) {
    object Normal: DividerType(thickness = 1.dp)
    object Big: DividerType(thickness = 12.dp)
}

@Composable
fun MomoriDivier(
    modifier: Modifier = Modifier,
    color: Color = MomoriColor.Gray50,
    type: DividerType
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = type.thickness
    )
}