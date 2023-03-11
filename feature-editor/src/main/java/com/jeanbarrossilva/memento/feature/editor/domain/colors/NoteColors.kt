package com.jeanbarrossilva.memento.feature.editor.domain.colors

import androidx.compose.ui.graphics.Color
import com.jeanbarrossilva.memento.core.register.domain.Color as DomainColor

enum class NoteColors {
    BLUE {
        override val container = NoteContainerColors.Blue
        override val content = Color.Black

        override fun toColor(): com.jeanbarrossilva.memento.core.register.domain.Color {
            return DomainColor.BLUE
        }
    },
    PURPLE {
        override val container = NoteContainerColors.Purple
        override val content = Color.Black

        override fun toColor(): com.jeanbarrossilva.memento.core.register.domain.Color {
            return DomainColor.PURPLE
        }
    },
    YELLOW {
        override val container = NoteContainerColors.Yellow
        override val content = Color.Black

        override fun toColor(): com.jeanbarrossilva.memento.core.register.domain.Color {
            return DomainColor.YELLOW
        }
    };

    abstract val container: NoteContainerColors
    abstract val content: Color

    abstract fun toColor(): DomainColor
}
