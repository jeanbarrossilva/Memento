package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.domain.Path
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.platform.register.note.NoteDao
import com.jeanbarrossilva.memento.platform.register.note.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomRepository(private val dao: NoteDao) : Repository {
    override suspend fun getNotes(): Flow<Map<Path, List<Note>>> {
        return dao.selectAll().map {
            it.map(NoteEntity::toNote).groupBy(Note::path)
        }
    }

    override suspend fun getNoteByID(noteID: String): Flow<Note?> {
        return dao.selectByID(noteID).map {
            it?.toNote()
        }
    }
}
