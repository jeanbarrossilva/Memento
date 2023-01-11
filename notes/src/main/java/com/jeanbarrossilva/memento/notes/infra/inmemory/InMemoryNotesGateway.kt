package com.jeanbarrossilva.memento.notes.infra.inmemory

import com.jeanbarrossilva.memento.notes.domain.note.Note
import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.notes.infra.NotesGateway

internal class InMemoryNotesGateway : NotesGateway {
    private var currentFolder: NoteFolder? = null

    override suspend fun getFolders(): List<NoteFolder> {
        return NoteFolder.Custom.samples
    }

    override suspend fun getCurrentFolder(): NoteFolder? {
        return currentFolder
    }

    override suspend fun setCurrentFolder(currentFolder: NoteFolder) {
        this.currentFolder = currentFolder
    }

    override suspend fun getNotes(): List<Note> {
        return Note.samples
    }
}
