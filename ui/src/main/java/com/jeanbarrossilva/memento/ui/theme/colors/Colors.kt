package com.jeanbarrossilva.memento.ui.theme.colors

import androidx.annotation.ColorRes
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

/**
 * Palette for various contexts.
 *
 * @param background [Color] of the container of the whole app.
 * @param scrim [Color] of the scrim that's put underneath modals.
 * @param container [LayeredColors] for containers in which components may be placed.
 * @param content [LayeredColors] for components in a certain container.
 **/
data class Colors internal constructor(
    val background: Color,
    val scrim: Color,
    val container: LayeredColors,
    val content: LayeredColors,
    val text: TextColors
) {
    /** [ColorScheme] version of these [Colors]. **/
    internal val material
        get() = ColorScheme(
            primary = Color.Unspecified,
            onPrimary = Color.Unspecified,
            primaryContainer = container.primary,
            onPrimaryContainer = content.primary,
            inversePrimary = Color.Unspecified,
            secondary = Color.Unspecified,
            onSecondary = Color.Unspecified,
            secondaryContainer = container.secondary,
            onSecondaryContainer = content.secondary,
            tertiary = Color.Unspecified,
            onTertiary = Color.Unspecified,
            tertiaryContainer = container.tertiary,
            onTertiaryContainer = content.tertiary,
            background = background,
            onBackground = text.default,
            surface = Color.Unspecified,
            onSurface = Color.Unspecified,
            surfaceVariant = Color.Unspecified,
            onSurfaceVariant = Color.Unspecified,
            surfaceTint = Color.Unspecified,
            inverseSurface = Color.Unspecified,
            inverseOnSurface = Color.Unspecified,
            error = Color.Unspecified,
            onError = Color.Unspecified,
            errorContainer = Color.Unspecified,
            onErrorContainer = Color.Unspecified,
            outline = Color.Unspecified,
            outlineVariant = Color.Unspecified,
            scrim
        )

    companion object {
        /** [Colors] with unspecified values. **/
        internal val Unspecified = Colors(
            background = Color.Unspecified,
            scrim = Color.Unspecified,
            container = LayeredColors.Unspecified,
            content = LayeredColors.Unspecified,
            TextColors.Unspecified
        )

        /**
         * Creates [Colors] by getting the corresponding [Color] for the given [background] resource
         * ID and with the [container] and [content][content] [LayeredColors].
         *
         * @param background Resource ID of [Colors.background].
         * @param scrim Resource ID of [Colors.scrim].
         * @param container Container-specific [LayeredColors].
         * @param content Content-specific [LayeredColors].
         * @param text Text-specific colors.
         **/
        @Composable
        internal fun of(
            @ColorRes background: Int,
            @ColorRes scrim: Int,
            container: LayeredColors,
            content: LayeredColors,
            text: TextColors
        ): Colors {
            return Colors(colorResource(background), colorResource(scrim), container, content, text)
        }
    }
}
