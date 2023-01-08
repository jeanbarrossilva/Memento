package com.jeanbarrossilva.memento.ui.theme.colors

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.jeanbarrossilva.memento.ui.R
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

/**
 * Provides the [Colors] to be used in the [MementoTheme].
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
            LocalContentColor provides MementoTheme.colors.text.default,
            content = content
        )
    }
}
