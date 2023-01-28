package com.jeanbarrossilva.memento.feature.notes.domain.note

import com.jeanbarrossilva.memento.core.register.domain.Color

/** Adapts the given [Color] to the current domain. **/
internal fun Color.adapt(): NoteColors {
    return when (this) {
        Color.BLUE -> NoteColors.BLUE
        Color.PURPLE -> NoteColors.PURPLE
        Color.YELLOW -> NoteColors.YELLOW
    }
}
