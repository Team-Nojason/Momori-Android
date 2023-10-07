package com.nohjason.momori.component.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nohjason.momori.component.theme.MomoriColor

sealed class DividerType(val thickness: Dp) {
    object Small: DividerType(thickness = 1.dp)
    object Large: DividerType(thickness = 12.dp)
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

@Composable
@Preview
fun DividerPreview() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        MomoriDivier(type = DividerType.Small)

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        MomoriDivier(type = DividerType.Large)

        Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }
}