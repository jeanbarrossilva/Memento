package com.jeanbarrossilva.aurelius.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import com.jeanbarrossilva.aurelius.theme.AureliusTheme.animation
import com.jeanbarrossilva.aurelius.theme.AureliusTheme.colors
import com.jeanbarrossilva.aurelius.theme.AureliusTheme.sizes
import com.jeanbarrossilva.aurelius.theme.AureliusTheme.text
import com.jeanbarrossilva.aurelius.theme.AureliusTheme.visibility
import com.jeanbarrossilva.aurelius.theme.animation.AnimationProvider
import com.jeanbarrossilva.aurelius.theme.animation.LocalAnimation
import com.jeanbarrossilva.aurelius.theme.colors.ColorsProvider
import com.jeanbarrossilva.aurelius.theme.colors.LocalColors
import com.jeanbarrossilva.aurelius.theme.sizes.LocalSizes
import com.jeanbarrossilva.aurelius.theme.sizes.SizesProvider
import com.jeanbarrossilva.aurelius.theme.text.LocalText
import com.jeanbarrossilva.aurelius.theme.text.TextProvider
import com.jeanbarrossilva.aurelius.theme.visibility.LocalVisibility
import com.jeanbarrossilva.aurelius.theme.visibility.VisibilityProvider

/**
 * Provider of the theme's configuration values, such as [animation], [colors], [sizes], [text] and
 * [visibility].
 **/
object AureliusTheme {
    /** Current [Animation] from [LocalAnimation]. **/
    val animation
        @Composable get() = LocalAnimation.current

    /** Current [Colors] from [LocalColors]. **/
    val colors
        @Composable get() = LocalColors.current

    /** Current [Sizes] from [LocalSizes]. **/
    val sizes
        @Composable get() = LocalSizes.current

    /** Current [Text] from [LocalText]. **/
    val text
        @Composable get() = LocalText.current

    /** Current [Visibility] from [LocalVisibility]. **/
    val visibility
        @Composable get() = LocalVisibility.current
}

/**
 * Themes the given [content] by providing [colors], [text], [sizes] and [visibility][visibility]
 * values to their respective [CompositionLocal]s and sets up the system bars.
 *
 * @param content Content to be themed.
 **/
@Composable
fun AureliusTheme(content: @Composable () -> Unit) {
    ColorsProvider {
        SystemBarsConfigurationEffect()

        AnimationProvider {
            VisibilityProvider {
                TextProvider {
                    SizesProvider {
                        MaterialTheme(
                            colors.material,
                            MaterialTheme.shapes.copy(extraSmall = CircleShape),
                            text.material,
                            content
                        )
                    }
                }
            }
        }
    }
}
