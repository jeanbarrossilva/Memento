package com.jeanbarrossilva.memento.feature.notes.utils

import com.jeanbarrossilva.memento.core.register.domain.Path
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder

/** Converts the given [Path] into a [NoteFolder]. **/
internal fun Path.toNoteFolder(): NoteFolder {
    val valueBytes = value.toByteArray(Path.charset)
    val title = String(valueBytes, Path.charset)
    return NoteFolder(value, title)
}
