package com.jeanbarrossilva.memento.feature.editor.utils

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors

/** Adapts the given [Color] to the current domain. **/
internal fun Color.adapt(): NoteColors {
    return when (this) {
        Color.BLUE -> NoteColors.BLUE
        Color.YELLOW -> NoteColors.YELLOW
        Color.PURPLE -> NoteColors.PURPLE
    }
}
