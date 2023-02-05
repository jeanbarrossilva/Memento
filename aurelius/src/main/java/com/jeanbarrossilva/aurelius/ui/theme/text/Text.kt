package com.jeanbarrossilva.aurelius.theme.text

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
    val title: Title,
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
            titleLarge = title.large,
            titleMedium = TextStyle.Default,
            titleSmall = title.small,
            labelLarge = TextStyle.Default,
            labelMedium = TextStyle.Default,
            labelSmall = label,
            bodyLarge = body,
            bodyMedium = TextStyle.Default,
            bodySmall = TextStyle.Default
        )

    companion object {
        /** [Text] with default values. **/
        internal val Default = Text(
            headline = TextStyle.Default,
            title = Title.Default,
            body = TextStyle.Default,
            label = TextStyle.Default
        )
    }
}
