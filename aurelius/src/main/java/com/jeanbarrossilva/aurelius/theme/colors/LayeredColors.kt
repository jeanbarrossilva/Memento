package com.jeanbarrossilva.aurelius.theme.colors

import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

/**
 * [Color]s that might be used on top of each other following an descending order.
 *
 * @param primary Greatest [Color] in the hierarchy.
 * @param secondary Lower than [primary], greater than [tertiary].
 * @param tertiary Lowest [Color] in the hierarchy.
 **/
data class LayeredColors internal constructor(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color
) {
    companion object {
        /** [LayeredColors] with [Color.Unspecified] values. **/
        internal val Unspecified = LayeredColors(
            primary = Color.Unspecified,
            secondary = Color.Unspecified,
            tertiary = Color.Unspecified
        )

        /**
         * Creates [LayeredColors] with each of the given [Color] resource IDs.
         *
         * @param primary Resource ID of [LayeredColors.primary].
         * @param secondary Resource ID of [LayeredColors.secondary].
         * @param tertiary Resource ID of [LayeredColors.tertiary].
         **/
        @Composable
        internal fun of(
            @ColorRes primary: Int,
            @ColorRes secondary: Int,
            @ColorRes tertiary: Int
        ): LayeredColors {
            return LayeredColors(
                colorResource(primary),
                colorResource(secondary),
                colorResource(tertiary)
            )
        }
    }
}
