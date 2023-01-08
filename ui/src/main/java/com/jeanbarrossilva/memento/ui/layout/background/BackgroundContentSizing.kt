package com.jeanbarrossilva.memento.ui.layout.background

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

enum class BackgroundContentSizing {
    WRAP {
        override val modifier = Modifier
    },
    FILL {
        override val modifier = Modifier.fillMaxSize()
    };

    internal abstract val modifier: Modifier
}
