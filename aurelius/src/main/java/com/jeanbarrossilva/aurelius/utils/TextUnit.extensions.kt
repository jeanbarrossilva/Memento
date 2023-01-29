package com.jeanbarrossilva.aurelius.utils

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

/**
 * Converts the given [TextUnit] into [Dp].
 *
 * @param density [Density] through which this will be converted.
 **/
internal fun TextUnit.toDp(density: Density): Dp {
    return with(density) {
        if (isSp) toDp() else Dp.Unspecified
    }
}
