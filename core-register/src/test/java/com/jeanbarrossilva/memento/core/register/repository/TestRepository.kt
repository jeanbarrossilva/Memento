package com.jeanbarrossilva.memento.core.register.repository

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.domain.Path
import com.jeanbarrossilva.memento.core.register.infra.Repository

internal class TestRepository : Repository {
    val notes = mutableListOf<Note>()

    override suspend fun getNotes(): Map<Path, List<Note>> {
        return notes.groupBy {
            it.path
        }
    }

    override suspend fun getNoteByID(noteID: String): Note? {
        return notes.find {
            it.id == noteID
        }
    }
}
