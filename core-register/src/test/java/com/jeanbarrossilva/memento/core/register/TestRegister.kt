package com.jeanbarrossilva.memento.core.register

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Register
import com.jeanbarrossilva.memento.core.register.repository.TestRepository

internal class TestRegister(private val repository: TestRepository) : Register() {
    override suspend fun onRegister(note: Note) {
        repository.notes.add(note)
    }

    override suspend fun unregister(noteID: String) {
        repository.notes.removeIf {
            it.id == noteID
        }
    }

    override suspend fun unregisterAll() {
        repository.notes.clear()
    }
}
