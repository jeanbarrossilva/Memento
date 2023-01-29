package com.jeanbarrossilva.memento.core.register

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Register
import com.jeanbarrossilva.memento.core.register.repository.TestRepository

internal class TestRegister(private val repository: TestRepository) : Register() {
    override suspend fun onRegister(note: Note) {
        repository.notes.value = repository.notes.value + note
    }

    override suspend fun unregister(noteID: String) {
        val notes = repository.notes.value
        notes.find { it.id == noteID }?.let { repository.notes.value = notes - it }
    }

    override suspend fun unregisterAll() {
        repository.notes.value = emptyList()
    }
}
