package com.jeanbarrossilva.aurelius.ui.theme.visibility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.jeanbarrossilva.aurelius.theme.visibility.LocalVisibility
import com.jeanbarrossilva.aurelius.theme.visibility.Visibility
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

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
