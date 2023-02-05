package com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.composition.LocalMinimumTouchTargetSize
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.Horizontal

object TopAppBarDefaults {
    internal val NavigationButtonIconSize = 24.dp

    internal val containerBrush
        @Composable get() = Brush.linearGradient(
            listOf(topAppBarBackgroundColor, AureliusTheme.colors.container.tertiary),
            start = Offset.Horizontal
        )

    val navigationButtonSize
        @Composable get() = with(LocalMinimumTouchTargetSize.current / 2) {
            DpSize(NavigationButtonIconSize + width, NavigationButtonIconSize + height)
        }
}
