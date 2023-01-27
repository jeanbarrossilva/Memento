package com.jeanbarrossilva.memento.core.register.infra

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.domain.Path

interface Repository {
    suspend fun getNotes(): Map<Path, List<Note>>

    suspend fun getNoteByID(noteID: String): Note?
}
