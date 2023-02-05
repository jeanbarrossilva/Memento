package com.jeanbarrossilva.aurelius.theme.sizes

import androidx.compose.ui.unit.Dp

/**
 * Sizes for spacing, such as the distance between one component and another or padding.
 *
 * @param huge Greatest possible size.
 * @param large Smaller than [huge], greater than [medium].
 * @param medium Smaller than [large], greater than [small].
 * @param small Smaller than [medium], greater than [tiny].
 * @param tiny Smallest possible size.
 **/
data class Spacing internal constructor(
    val huge: Dp,
    val large: Dp,
    val medium: Dp,
    val small: Dp,
    val tiny: Dp
) {
    companion object {
        /** [Spacing] with [Dp.Unspecified] values. **/
        internal val Unspecified = Spacing(
            huge = Dp.Unspecified,
            large = Dp.Unspecified,
            medium = Dp.Unspecified,
            small = Dp.Unspecified,
            tiny = Dp.Unspecified
        )
    }
}
