package com.jeanbarrossilva.memento.ui.theme.sizes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp

/**
 * Reserved spaces for hierarchically greater components.
 *
 * @param fab [Dp]s taken by the FAB.
 **/
data class Margin internal constructor(val fab: PaddingValues) {
    companion object {
        /** [Margin] with unspecified [PaddingValues]s. **/
        internal val Unspecified = Margin(fab = PaddingValues(Dp.Unspecified))
    }
}
