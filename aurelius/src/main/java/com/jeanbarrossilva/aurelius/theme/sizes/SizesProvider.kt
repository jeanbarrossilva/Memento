package com.jeanbarrossilva.aurelius.theme.sizes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.navigationBarHeight
import com.jeanbarrossilva.aurelius.utils.statusBarHeight

/**
 * Provides the [Sizes] to be used in the [AureliusTheme].
 *
 * @param content Content to be able to access the provided value through [LocalSizes].
 **/
@Composable
internal fun SizesProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalSizes provides Sizes(
            Spacing(huge = 32.dp, large = 24.dp, medium = 16.dp, small = 8.dp, tiny = 4.dp),
            Margin(
                statusBar = PaddingValues(top = statusBarHeight()),
                fab = PaddingValues(bottom = navigationBarHeight() + 16.dp * 2 + 56.dp),
                navigationBar = PaddingValues(bottom = navigationBarHeight())
            )
        ),
        content = content
    )
}
