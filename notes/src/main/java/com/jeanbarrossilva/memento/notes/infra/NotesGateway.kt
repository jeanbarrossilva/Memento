package com.jeanbarrossilva.memento.notes.infra

import com.jeanbarrossilva.memento.notes.domain.note.Note
import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder

internal interface NotesGateway {
    suspend fun getFolders(): List<NoteFolder>

    suspend fun getNotes(): List<Note>
}
