package com.jeanbarrossilva.aurelius.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/** Height of the navigation bar. **/
@Composable
internal fun navigationBarHeight(): Dp {
    return getInsets(WindowInsetsCompat.Type.navigationBars())
        ?.bottom
        ?.toDp(LocalDensity.current)
        ?: 0.dp
}

/** Height of the status bar. **/
@Composable
internal fun statusBarHeight(): Dp {
    return getInsets(WindowInsetsCompat.Type.statusBars())
        ?.top
        ?.toDp(LocalDensity.current)
        ?.`if`({ this < 0.dp }) { 0.dp }
        ?: 0.dp
}

/**
 * Gets the [Insets] that match the given [insetsType].
 *
 * @param insetsType One of the [WindowInsetsCompat.Type]s to get the [Insets] from.
 **/
@Composable
@Suppress("DiscouragedApi", "InternalInsetResource")
private fun getInsets(insetsType: Int): Insets? {
    return ViewCompat.getRootWindowInsets(LocalView.current)?.getInsets(insetsType)
}
