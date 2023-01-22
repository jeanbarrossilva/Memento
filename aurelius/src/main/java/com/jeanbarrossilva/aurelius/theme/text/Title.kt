package com.jeanbarrossilva.aurelius.theme.text

import androidx.compose.ui.text.TextStyle

/**
 * Different sizes of title [TextStyle]s.
 *
 * @param large Larger [TextStyle].
 * @param small Smallest [TextStyle].
 **/
data class Title internal constructor(val large: TextStyle, val small: TextStyle) {
    companion object {
        /** [Title] with [TextStyle.Default] values. **/
        internal val Default = Title(large = TextStyle.Default, small = TextStyle.Default)
    }
}
