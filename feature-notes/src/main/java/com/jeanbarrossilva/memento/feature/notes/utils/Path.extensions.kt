package com.jeanbarrossilva.memento.feature.notes.utils

import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder

/** Converts the given [Folder] into a [NoteFolder]. **/
internal fun Folder.toNoteFolder(): NoteFolder {
    return NoteFolder(path, title)
}
