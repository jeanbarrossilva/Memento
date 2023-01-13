package com.jeanbarrossilva.memento.feature.notes.infra

import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder

internal interface NotesGateway {
    suspend fun getDefaultFolder(): NoteFolder

    suspend fun getFolders(): List<NoteFolder>

    suspend fun getCurrentFolder(): NoteFolder?

    suspend fun setCurrentFolder(currentFolder: NoteFolder)

    suspend fun getNotes(): List<Note>
}
