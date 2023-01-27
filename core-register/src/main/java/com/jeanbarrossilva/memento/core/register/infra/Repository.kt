package com.jeanbarrossilva.memento.core.register.infra

import com.jeanbarrossilva.memento.core.register.domain.Note

interface Repository {
    suspend fun getNotes(): List<Note>

    suspend fun getNoteByID(noteID: String): Note?
}
