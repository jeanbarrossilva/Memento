package com.jeanbarrossilva.aurelius.composition

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.DpSize

/** [CompositionLocal] that provides the minimum touch target size. **/
internal val LocalMinimumTouchTargetSize = compositionLocalOf {
    DpSize.Unspecified
}
