package com.jeanbarrossilva.memento.feature.notes.infra

import android.content.Context
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder

internal interface NotesGateway {
    suspend fun getDefaultFolder(context: Context): NoteFolder

    suspend fun getFolders(): List<NoteFolder>

    suspend fun getCurrentFolder(): NoteFolder?

    suspend fun setCurrentFolder(currentFolder: NoteFolder)

    suspend fun getNotes(): List<Note>
}
