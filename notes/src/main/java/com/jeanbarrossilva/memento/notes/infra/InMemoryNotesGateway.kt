package com.jeanbarrossilva.memento.notes.infra

import com.jeanbarrossilva.memento.notes.NotesGateway
import com.jeanbarrossilva.memento.notes.domain.note.Note

internal class InMemoryNotesGateway : NotesGateway {
    override suspend fun getNotes(): List<Note> {
        return Note.samples
    }
}