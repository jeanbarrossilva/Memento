package com.jeanbarrossilva.memento.core.register.infra

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.domain.Path
import java.util.UUID

abstract class Register {
    @Suppress("NAME_SHADOWING")
    suspend fun register(
        title: String,
        path: String = Path.root.value,
        body: String = "",
        color: Color = Color.values().random()
    ): String {
        val id = UUID.randomUUID().toString()
        val path = Path to path
        val note = Note(id, path, title, body, color)
        onRegister(note)
        return id
    }

    abstract suspend fun unregister(noteID: String)

    abstract suspend fun unregisterAll()

    protected abstract suspend fun onRegister(note: Note)
}