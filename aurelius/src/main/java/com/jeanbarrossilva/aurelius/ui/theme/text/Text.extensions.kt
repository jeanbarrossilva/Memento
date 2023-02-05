package com.jeanbarrossilva.aurelius.theme.text

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf

/** [CompositionLocal] that provides [Text]. **/
internal val LocalText = compositionLocalOf {
    Text.Default
}
