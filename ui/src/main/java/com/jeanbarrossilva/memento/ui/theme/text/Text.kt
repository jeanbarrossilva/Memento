package com.jeanbarrossilva.memento.ui.theme.text

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle

/**
 * [TextStyle]s for various contexts, representing different hierarchies.
 *
 * @param headline Most prominent [TextStyle].
 * @param title Less prominent than [headline], but more than [body].
 * @param body Less prominent than [title], but more than [label].
 * @param label Least prominent [TextStyle].
 **/
data class Text internal constructor(
    val headline: TextStyle,
    val title: TextStyle,
    val body: TextStyle,
    val label: TextStyle
) {
    /** [Typography] version of this [Text]. **/
    internal val material
        get() = Typography(
            displayLarge = TextStyle.Default,
            displayMedium = TextStyle.Default,
            displaySmall = TextStyle.Default,
            headlineLarge = TextStyle.Default,
            headlineMedium = TextStyle.Default,
            headlineSmall = headline,
            titleLarge = TextStyle.Default,
            titleMedium = TextStyle.Default,
            titleSmall = title,
            labelLarge = TextStyle.Default,
            labelMedium = TextStyle.Default,
            labelSmall = TextStyle.Default,
            bodyLarge = body,
            bodyMedium = TextStyle.Default,
            bodySmall = TextStyle.Default
        )

    companion object {
        /** [Text] with [TextStyle.Default] values. **/
        internal val Default = Text(
            headline = TextStyle.Default,
            title = TextStyle.Default,
            body = TextStyle.Default,
            label = TextStyle.Default
        )
    }
}
