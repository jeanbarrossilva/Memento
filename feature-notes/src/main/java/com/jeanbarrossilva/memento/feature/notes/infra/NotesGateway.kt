package com.jeanbarrossilva.memento.feature.notes.infra

import android.content.Context
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import kotlinx.coroutines.flow.Flow

internal interface NotesGateway {
    suspend fun getDefaultFolder(context: Context): NoteFolder

    suspend fun getFolders(context: Context): Flow<List<NoteFolder>>

    suspend fun getCurrentFolder(): Flow<NoteFolder?>

    suspend fun setCurrentFolder(currentFolder: NoteFolder)

    suspend fun getNotes(): Flow<List<Note>>
}
