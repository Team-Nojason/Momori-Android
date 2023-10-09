package com.nohjason.momori.component.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

object MomoriColor {

    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)

    val Mint = Color(0xFF01FFC2)

    val Red = Color(0xFFF31E2B)

    val Transparent = Color(0x00000000)

    val Gray50 = Color(0xFFF4F5F9)
    val Gray100 = Color(0xFFDBDCE0)
    val Gray200 = Color(0xFFC2C2C7)
    val Gray300 = Color(0xFFA8A9AD)
    val Gray400 = Color(0xFF8F8F94)
    val Gray500 = Color(0xFF76767B)
    val Gray600 = Color(0xFF5C5C60)
    val Gray700 = Color(0xFF424245)
    val Gray800 = Color(0xFF28282A)
    val Gray900 = Color(0xFF0E0E0F)
}

@Composable
fun contentColorFor(backgroundColor: Color) =
    when (backgroundColor) {
        MomoriColor.Transparent -> MomoriColor.Black
        MomoriColor.White -> MomoriColor.Black
        MomoriColor.Gray100 -> MomoriColor.Black
        MomoriColor.Mint -> MomoriColor.Black
        else -> MomoriColor.White
    }

internal val LocalColor = staticCompositionLocalOf { MomoriColor }
internal val LocalContentColor = compositionLocalOf { Color.Black }
internal val LocalContentAlpha = compositionLocalOf { 1f }
