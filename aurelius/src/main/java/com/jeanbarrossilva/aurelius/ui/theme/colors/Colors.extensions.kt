package com.jeanbarrossilva.aurelius.theme.colors

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf

/** [CompositionLocal] that provides [Colors]. **/
internal val LocalColors = compositionLocalOf {
    Colors.Unspecified
}