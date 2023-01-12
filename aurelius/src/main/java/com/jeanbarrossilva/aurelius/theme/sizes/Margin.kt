package com.jeanbarrossilva.aurelius.theme.sizes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp

/**
 * Reserved spaces for hierarchically greater components.
 *
 * @param statusBar [Dp]s taken by the status bar.
 * @param fab [Dp]s taken by the FAB.
 * @param navigationBar [Dp]s taken by the navigation bar.
 **/
data class Margin internal constructor(
    val statusBar: PaddingValues,
    val fab: PaddingValues,
    val navigationBar: PaddingValues
) {
    companion object {
        /** [Margin] with unspecified [PaddingValues]s. **/
        internal val Unspecified = Margin(
            statusBar = PaddingValues(Dp.Unspecified),
            fab = PaddingValues(Dp.Unspecified),
            navigationBar = PaddingValues(Dp.Unspecified)
        )
    }
}
