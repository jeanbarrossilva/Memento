package com.jeanbarrossilva.memento.app.fixtures

import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.notes.domain.note.Gradient

/** Converts the given [Gradient] into [NoteColors]. **/
internal fun Gradient.toNoteColors(): NoteColors {
    return when (this) {
        Gradient.BLUE -> NoteColors.BLUE
        Gradient.PURPLE -> NoteColors.PURPLE
        Gradient.YELLOW -> NoteColors.YELLOW
    }
}
