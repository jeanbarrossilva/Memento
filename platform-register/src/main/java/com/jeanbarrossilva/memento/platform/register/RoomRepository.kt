package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.domain.Path
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.platform.register.note.NoteDao
import com.jeanbarrossilva.memento.platform.register.note.NoteEntity

internal class RoomRepository(private val dao: NoteDao) : Repository {
    override suspend fun getNotes(): Map<Path, List<Note>> {
        return dao.selectAll().map(NoteEntity::toNote).groupBy {
            it.path
        }
    }

    override suspend fun getNoteByID(noteID: String): Note? {
        return dao.selectByID(noteID)?.toNote()
    }
}
