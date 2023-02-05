package com.jeanbarrossilva.aurelius.ui.theme.colors

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.jeanbarrossilva.aurelius.theme.colors.Colors
import com.jeanbarrossilva.aurelius.theme.colors.LayeredColors
import com.jeanbarrossilva.aurelius.theme.colors.LocalColors
import com.jeanbarrossilva.aurelius.theme.colors.TextColors
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.ui.R

/**
 * Provides the [Colors] to be used in the [AureliusTheme].
 *
 * @param content Content to be able to access the provided value through [LocalColors].
 **/
@Composable
internal fun ColorsProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColors provides Colors.of(
            R.color.background,
            R.color.scrim,
            container = LayeredColors.of(
                R.color.container_primary,
                R.color.container_secondary,
                R.color.container_tertiary
            ),
            content = LayeredColors.of(
                R.color.content_primary,
                R.color.content_secondary,
                R.color.content_tertiary
            ),
            TextColors.of(R.color.text_highlighted, R.color.text_default)
        )
    ) {
        CompositionLocalProvider(
            LocalContentColor provides AureliusTheme.colors.text.default,
            content = content
        )
    }
}
