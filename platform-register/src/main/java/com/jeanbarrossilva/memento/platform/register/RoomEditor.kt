package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.infra.Editor
import com.jeanbarrossilva.memento.platform.register.note.NoteDao

class RoomEditor(private val dao: NoteDao) : Editor {
    override suspend fun setTitle(noteID: String, title: String) {
        dao.updateTitle(noteID, title)
    }

    override suspend fun setBody(noteID: String, body: String) {
        dao.updateBody(noteID, body)
    }

    override suspend fun setColor(noteID: String, color: Color) {
        dao.updateColorID(noteID, color.id)
    }
}
