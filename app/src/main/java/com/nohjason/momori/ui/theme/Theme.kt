package com.nohjason.momori.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.nohjason.momori.component.theme.LocalColor
import com.nohjason.momori.component.theme.LocalShape
import com.nohjason.momori.component.theme.LocalTypography
import com.nohjason.momori.component.theme.MomoriColor
import com.nohjason.momori.component.theme.MomoriShape
import com.nohjason.momori.component.theme.MomoriTypography

@Composable
fun MomoriTheme(
    color: MomoriColor = MomoriTheme.color,
    typography: MomoriTypography = MomoriTheme.typography,
    shape: MomoriShape = MomoriTheme.shape,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColor provides color,
        LocalTypography provides typography,
        LocalShape provides shape,
        content = content
    )
}

object MomoriTheme {
    val color: MomoriColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val typography: MomoriTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shape: MomoriShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current
}