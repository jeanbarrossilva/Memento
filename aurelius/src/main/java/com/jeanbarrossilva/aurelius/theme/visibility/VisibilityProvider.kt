package com.jeanbarrossilva.aurelius.theme.visibility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.jeanbarrossilva.aurelius.theme.AureliusTheme

/**
 * Provides the [Visibility] to be used in the [AureliusTheme].
 *
 * @param content Content to be able to access the provided value through [LocalVisibility].
 **/
@Composable
internal fun VisibilityProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalVisibility provides Visibility(medium = .5f, low = .38f),
        content = content
    )
}
