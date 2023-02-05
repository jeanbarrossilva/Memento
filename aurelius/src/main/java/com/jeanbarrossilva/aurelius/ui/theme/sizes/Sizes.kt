package com.jeanbarrossilva.aurelius.theme.sizes

/**
 * Different types of area measurement.
 *
 * @param spacing Sizes for spacing, such as the distance between one component and another or
 * padding.
 * @param margin Reserved spaces for hierarchically greater components.
 **/
data class Sizes(val spacing: Spacing, val margin: Margin) {
    companion object {
        /** [Sizes] with unspecified values. **/
        internal val Unspecified = Sizes(Spacing.Unspecified, Margin.Unspecified)
    }
}
