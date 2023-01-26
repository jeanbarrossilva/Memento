package com.jeanbarrossilva.memento.core.register.infra

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Note
import java.util.UUID

abstract class Register {
    suspend fun register(title: String, body: String, color: Color): String {
        val id = UUID.randomUUID().toString()
        val note = Note(id, title, body, color)
        onRegister(note)
        return id
    }

    abstract suspend fun getNoteByID(noteID: String): Note?

    abstract suspend fun unregister(noteID: String)

    abstract suspend fun unregisterAll()

    protected abstract suspend fun onRegister(note: Note)
}