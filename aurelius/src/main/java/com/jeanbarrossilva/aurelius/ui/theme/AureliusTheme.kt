package com.jeanbarrossilva.aurelius.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.composition.LocalMinimumTouchTargetProvider
import com.jeanbarrossilva.aurelius.theme.SystemBarsConfigurationEffect
import com.jeanbarrossilva.aurelius.theme.animation.Animation
import com.jeanbarrossilva.aurelius.theme.animation.LocalAnimation
import com.jeanbarrossilva.aurelius.theme.colors.Colors
import com.jeanbarrossilva.aurelius.theme.colors.LocalColors
import com.jeanbarrossilva.aurelius.theme.sizes.LocalSizes
import com.jeanbarrossilva.aurelius.theme.sizes.Sizes
import com.jeanbarrossilva.aurelius.theme.text.LocalText
import com.jeanbarrossilva.aurelius.theme.text.Text
import com.jeanbarrossilva.aurelius.theme.visibility.LocalVisibility
import com.jeanbarrossilva.aurelius.theme.visibility.Visibility
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.animation
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.colors
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.sizes
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.text
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme.visibility
import com.jeanbarrossilva.aurelius.ui.theme.animation.AnimationProvider
import com.jeanbarrossilva.aurelius.ui.theme.colors.ColorsProvider
import com.jeanbarrossilva.aurelius.ui.theme.sizes.SizesProvider
import com.jeanbarrossilva.aurelius.ui.theme.text.TextProvider
import com.jeanbarrossilva.aurelius.ui.theme.visibility.VisibilityProvider

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
                        LocalMinimumTouchTargetProvider {
                            MaterialTheme(
                                colors.material,
                                MaterialTheme.shapes.copy(
                                    extraSmall = CircleShape,
                                    small = RoundedCornerShape(14.dp),
                                    large = RoundedCornerShape(24.dp)
                                ),
                                text.material,
                                content
                            )
                        }
                    }
                }
            }
        }
    }
}