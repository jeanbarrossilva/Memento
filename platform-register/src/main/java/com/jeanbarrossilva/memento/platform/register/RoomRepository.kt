package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.platform.register.note.NoteDao

internal class RoomRepository(private val dao: NoteDao) : Repository {
    override suspend fun getNotes(): List<Note> {
        return dao.selectAll().map {
            it.toNote()
        }
    }

    override suspend fun getNoteByID(noteID: String): Note? {
        return dao.selectByID(noteID)?.toNote()
    }
}
