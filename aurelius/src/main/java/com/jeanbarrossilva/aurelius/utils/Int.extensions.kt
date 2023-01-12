package com.jeanbarrossilva.aurelius.utils // ktlint-disable filename

import androidx.annotation.Px
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

/**
 * Converts the given pixels in [Float] into [Dp].
 *
 * @param density [Density] through which this will be converted into [Dp].
 **/
internal fun @receiver:Px Int.toDp(density: Density): Dp {
    return with(density) {
        toDp()
    }
}
