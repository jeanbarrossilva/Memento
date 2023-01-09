package com.jeanbarrossilva.memento.notes.infra.inmemory

import com.jeanbarrossilva.memento.notes.domain.note.Note
import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.notes.infra.NotesGateway

internal class InMemoryNotesGateway : NotesGateway {
    override suspend fun getFolders(): List<NoteFolder> {
        return NoteFolder.Custom.samples
    }

    override suspend fun getNotes(): List<Note> {
        return Note.samples
    }
}
