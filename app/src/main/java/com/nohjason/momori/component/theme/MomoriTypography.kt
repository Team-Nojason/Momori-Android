package com.nohjason.momori.component.theme

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.nohjason.momori.R
import com.nohjason.momori.component.modifier.mmrClickable


val notoSans = FontFamily(
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
    val headline = TextStyle(
        fontFamily = notoSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

    @Stable
    val title = TextStyle(
        fontFamily = notoSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

    @Stable
    val body = TextStyle(
        fontFamily = notoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )

    @Stable
    val label = TextStyle(
        fontFamily = notoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )
}

@Composable
fun Headline(
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
        style = MomoriTypography.headline,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}
@Composable
fun Title(
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
        style = MomoriTypography.title,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}
@Composable
fun Body(
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
        style = MomoriTypography.body,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}
@Composable
fun Label(
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
        style = MomoriTypography.label,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

@Composable
@Preview
fun TypographyPreview() {
    Column {
        Headline(text = "ㅎasdasdㅇ\nasdasdasd")
        Title(text = "ㅎasdasdㅇ\nasdasdasd")
        Body(text = "ㅎasdasdㅇ\nasdasdasd")
        Label(text = "ㅎasdasdㅇ\nasdasdasd")
    }
}