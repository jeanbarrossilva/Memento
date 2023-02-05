package com.jeanbarrossilva.aurelius.theme.visibility

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf

/** [CompositionLocal] that provides a [Visibility]. **/
internal val LocalVisibility = compositionLocalOf {
    Visibility.Zero
}
