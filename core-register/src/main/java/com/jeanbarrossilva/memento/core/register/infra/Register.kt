package com.jeanbarrossilva.memento.core.register.infra

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.core.register.domain.Note
import java.util.UUID

abstract class Register {
    suspend fun register(
        title: String,
        folder: Folder? = null,
        body: String = "",
        color: Color = Color.values().random()
    ): String {
        val id = UUID.randomUUID().toString()
        val note = Note(id, folder, title, body, color)
        onRegister(note)
        return id
    }

    abstract suspend fun unregister(noteID: String)

    abstract suspend fun unregisterAll()

    protected abstract suspend fun onRegister(note: Note)
}