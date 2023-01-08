package com.jeanbarrossilva.memento.ui.theme.visibility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

/**
 * Provides the [Visibility] to be used in the [MementoTheme].
 *
 * @param content Content to be able to access the provided value through [LocalVisibility].
 **/
@Composable
internal fun VisibilityProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalVisibility provides Visibility(medium = .5f), content = content)
}
