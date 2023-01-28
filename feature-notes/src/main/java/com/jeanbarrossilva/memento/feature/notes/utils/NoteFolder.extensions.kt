package com.jeanbarrossilva.memento.feature.notes.utils

import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.feature.notes.infra.main.folder.NoteFolderEntity

/** Converts the given [NoteFolder] into a [NoteFolderEntity]. **/
internal fun NoteFolder.toNoteFolderEntity(): NoteFolderEntity {
    return NoteFolderEntity(id)
}
