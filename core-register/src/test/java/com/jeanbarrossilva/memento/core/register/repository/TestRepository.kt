package com.jeanbarrossilva.memento.core.register.repository

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

internal class TestRepository : Repository {
    val notes = MutableStateFlow(emptyList<Note>())

    override suspend fun getNotes(): Flow<List<Note>> {
        return notes.asStateFlow()
    }

    override suspend fun getNoteByID(noteID: String): Flow<Note?> {
        return notes.map { notes ->
            notes.find { note ->
                note.id == noteID
            }
        }
    }
}
