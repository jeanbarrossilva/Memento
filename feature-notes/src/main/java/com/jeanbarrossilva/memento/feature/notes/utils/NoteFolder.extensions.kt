package com.jeanbarrossilva.memento.feature.notes.utils

import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.feature.notes.infra.main.folder.CurrentNoteFolderEntity

/** Converts the given [NoteFolder] into a [CurrentNoteFolderEntity]. **/
internal fun NoteFolder.toNoteFolderEntity(): CurrentNoteFolderEntity {
    return CurrentNoteFolderEntity(id)
}
