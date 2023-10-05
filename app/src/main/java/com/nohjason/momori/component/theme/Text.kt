package com.nohjason.momori.component.theme

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.nohjason.momori.R
import com.nohjason.momori.component.modifier.mmrClickable


val pretendard = FontFamily(
    Font(R.font.notosans_light, FontWeight.Light),
    Font(R.font.notosans_regular, FontWeight.Normal),
    Font(R.font.notosans_medium, FontWeight.Medium),
    Font(R.font.notosans_bold, FontWeight.Bold),
    Font(R.font.notosans_semibold, FontWeight.SemiBold),
    Font(R.font.notosans_black, FontWeight.Black)
)
internal val LocalTypography = staticCompositionLocalOf { MomoriTypography }

object MomoriTypography {

    @Stable
    val headline1 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    )

    @Stable
    val headline2 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    )

    @Stable
    val title1 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 26.sp
    )

    @Stable
    val title2 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 22.sp
    )

    @Stable
    val body1 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    @Stable
    val body2 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )

    @Stable
    val body3 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )

    @Stable
    val label1 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 10.sp
    )

    @Stable
    val label2 = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )

}

@Composable
fun Headline1(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    rippleColor: Color = Color.Unspecified,
    rippleEnable: Boolean = false,
    bounded: Boolean = true,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.mmrClickable(
            onClick = onClick,
            interactionSource = interactionSource,
            rippleColor = rippleColor,
            rippleEnable = rippleEnable,
            bounded = bounded
        ),
        text = text,
        style = MomoriTypography.headline1,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}