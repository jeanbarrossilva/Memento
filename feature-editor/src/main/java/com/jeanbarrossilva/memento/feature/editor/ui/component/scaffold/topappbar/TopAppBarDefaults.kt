package com.jeanbarrossilva.memento.feature.editor.ui.component.scaffold.topappbar

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.aurelius.theme.AureliusTheme

internal object TopAppBarDefaults {
    val buttonEnterTransition
        @Composable get() = fadeIn(AureliusTheme.animation.spec())
    val buttonExitTransition
        @Composable get() = fadeOut(AureliusTheme.animation.spec())
}
