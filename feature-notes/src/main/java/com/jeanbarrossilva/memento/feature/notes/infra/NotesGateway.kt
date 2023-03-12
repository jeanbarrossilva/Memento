package com.jeanbarrossilva.memento.feature.notes.infra

import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import kotlinx.coroutines.flow.Flow

internal interface NotesGateway {
    suspend fun getCurrentFolder(): Flow<Folder?>

    suspend fun setCurrentFolder(currentFolder: Folder)

    suspend fun getNotes(): Flow<Loadable<SerializableList<Note>>>
}
