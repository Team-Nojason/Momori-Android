package com.nohjason.momori.component.overlay

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nohjason.momori.R
import com.nohjason.momori.component.modifier.mmrInnerShadow

@Composable
fun MomoriFlashOverlay(
    radius: Dp,
    alpha: Float = 0.35f,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        content()
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    this.alpha = .99f
                    this.translationY = 0.44f
                }
        ) {
            drawRect(Color.Black.copy(alpha = alpha))
            drawCircle(
                color = Color.Transparent,
                blendMode = BlendMode.Clear,
                radius = radius.toPx()
            )
        }
        Surface(
            modifier = Modifier
//                .padding(bottom = 0.1905.dp)
                .size(radius * 2 + 0.095230106.dp)
                .align(Alignment.Center)
                .mmrInnerShadow(
                    color = Color.Black.copy(alpha = alpha + 0.3f),
                    blur = 15.dp,
                    spread = 15.dp,
                    cornerRadius = radius
                ),
            color = Color.Transparent
        ) {

        }
    }
}

@Composable
@Preview
fun FlashOverlayPreview() {
    MomoriFlashOverlay(radius = 133.dp) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
    }
}