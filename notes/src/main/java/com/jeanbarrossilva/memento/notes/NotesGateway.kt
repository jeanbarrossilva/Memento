package com.jeanbarrossilva.memento.notes

import com.jeanbarrossilva.memento.notes.domain.note.Note

internal interface NotesGateway {
    suspend fun getNotes(): List<Note>
}
