package com.jeanbarrossilva.memento.core.register.infra

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.domain.Path
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNotes(): Flow<Map<Path, List<Note>>>

    suspend fun getNoteByID(noteID: String): Flow<Note?>
}
