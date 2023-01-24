package com.jeanbarrossilva.memento.feature.editor.domain

import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors

internal sealed interface EditorMode {
    object Reading : EditorMode

    data class Editing(val colors: List<NoteColors>?) : EditorMode
}
