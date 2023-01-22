package com.jeanbarrossilva.aurelius.composition

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalViewConfiguration

/**
 * Provides a minimum touch target to the given [content].
 *
 * @param content Content to be able to access the provided value through
 * [LocalMinimumTouchTargetSize].
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LocalMinimumTouchTargetProvider(content: @Composable () -> Unit) {
    if (LocalMinimumTouchTargetEnforcement.current) {
        CompositionLocalProvider(
            LocalMinimumTouchTargetSize.provides(
                LocalViewConfiguration.current.minimumTouchTargetSize
            ),
            content = content
        )
    } else {
        content()
    }
}
