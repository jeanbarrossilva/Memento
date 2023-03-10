package com.jeanbarrossilva.memento.core.register.editor

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Editor
import com.jeanbarrossilva.memento.core.register.repository.TestRepository
import com.jeanbarrossilva.memento.core.register.utils.replaceBy

internal class TestEditor(private val repository: TestRepository) : Editor {
    override suspend fun setTitle(noteID: String, title: String) {
        edit(noteID) {
            copy(title = title)
        }
    }

    override suspend fun setBody(noteID: String, body: String) {
        edit(noteID) {
            copy(body = body)
        }
    }

    override suspend fun setColor(noteID: String, color: Color) {
        edit(noteID) {
            copy(color = color)
        }
    }

    private fun edit(noteID: String, edit: Note.() -> Note) {
        repository.notes.value = repository
            .notes
            .value
            .toMutableList()
            .apply {
                replaceBy(edit) {
                    it.id == noteID
                }
            }
            .toList()
    }
}
