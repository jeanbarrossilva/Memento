package com.jeanbarrossilva.memento.core.register

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Register

internal class TestRegister : Register() {
    val notes = mutableListOf<Note>()

    override suspend fun onRegister(note: Note) {
        notes.add(note)
    }

    override suspend fun getNoteByID(noteID: String): Note? {
        return notes.find {
            it.id == noteID
        }
    }

    override suspend fun unregister(noteID: String) {
        notes.removeIf {
            it.id == noteID
        }
    }

    override suspend fun unregisterAll() {
        notes.clear()
    }
}
