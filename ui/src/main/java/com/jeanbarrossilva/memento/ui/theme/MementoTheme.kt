package com.jeanbarrossilva.memento.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import com.jeanbarrossilva.memento.ui.theme.MementoTheme.animation
import com.jeanbarrossilva.memento.ui.theme.MementoTheme.colors
import com.jeanbarrossilva.memento.ui.theme.MementoTheme.sizes
import com.jeanbarrossilva.memento.ui.theme.MementoTheme.text
import com.jeanbarrossilva.memento.ui.theme.MementoTheme.visibility
import com.jeanbarrossilva.memento.ui.theme.animation.Animation
import com.jeanbarrossilva.memento.ui.theme.animation.AnimationProvider
import com.jeanbarrossilva.memento.ui.theme.animation.LocalAnimation
import com.jeanbarrossilva.memento.ui.theme.colors.Colors
import com.jeanbarrossilva.memento.ui.theme.colors.ColorsProvider
import com.jeanbarrossilva.memento.ui.theme.colors.LocalColors
import com.jeanbarrossilva.memento.ui.theme.sizes.LocalSizes
import com.jeanbarrossilva.memento.ui.theme.sizes.Sizes
import com.jeanbarrossilva.memento.ui.theme.sizes.SizesProvider
import com.jeanbarrossilva.memento.ui.theme.text.LocalText
import com.jeanbarrossilva.memento.ui.theme.text.Text
import com.jeanbarrossilva.memento.ui.theme.text.TextProvider
import com.jeanbarrossilva.memento.ui.theme.visibility.LocalVisibility
import com.jeanbarrossilva.memento.ui.theme.visibility.Visibility
import com.jeanbarrossilva.memento.ui.theme.visibility.VisibilityProvider

/**
 * Provider of the theme's configuration values, such as [animation], [colors], [sizes], [text] and
 * [visibility].
 **/
object MementoTheme {
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
fun MementoTheme(content: @Composable () -> Unit) {
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
