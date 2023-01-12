package com.jeanbarrossilva.aurelius.theme.animation

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf

/** [CompositionLocal] that provides an [Animation]. **/
internal val LocalAnimation = compositionLocalOf {
    Animation.Default
}
