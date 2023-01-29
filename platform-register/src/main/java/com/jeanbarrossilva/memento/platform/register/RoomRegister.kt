package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Register
import com.jeanbarrossilva.memento.platform.register.note.NoteDao
import com.jeanbarrossilva.memento.platform.register.note.NoteEntity

class RoomRegister(private val dao: NoteDao) : Register() {
    override suspend fun unregister(noteID: String) {
        dao.delete(noteID)
    }

    override suspend fun unregisterAll() {
        dao.deleteAll()
    }

    override suspend fun onRegister(note: Note) {
        val entity = NoteEntity(note.id, note.path.value, note.title, note.body, note.color.id)
        dao.insert(entity)
    }
}
