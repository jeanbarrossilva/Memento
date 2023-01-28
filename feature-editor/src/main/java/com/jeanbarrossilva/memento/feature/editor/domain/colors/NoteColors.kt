package com.jeanbarrossilva.memento.feature.editor.domain.colors

import androidx.compose.ui.graphics.Color

internal enum class NoteColors {
    BLUE {
        override val container = NoteContainerColors.Blue
        override val content = Color.Black
    },
    PURPLE {
        override val container = NoteContainerColors.Purple
        override val content = Color.Black
    },
    YELLOW {
        override val container = NoteContainerColors.Yellow
        override val content = Color.Black
    };

    abstract val container: NoteContainerColors
    abstract val content: Color
}
