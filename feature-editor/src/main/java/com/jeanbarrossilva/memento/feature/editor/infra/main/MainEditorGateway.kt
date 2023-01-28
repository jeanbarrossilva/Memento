package com.jeanbarrossilva.memento.feature.editor.infra.main

import com.jeanbarrossilva.memento.core.register.infra.Editor
import com.jeanbarrossilva.memento.core.register.infra.Register
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway
import com.jeanbarrossilva.memento.feature.editor.utils.adapt

internal class MainEditorGateway(
    private val repository: Repository,
    private val register: Register,
    private val editor: Editor
) : EditorGateway {
    override suspend fun getNoteById(noteID: String): Note? {
        return repository.getNoteByID(noteID)?.adapt()
    }

    override suspend fun save(noteID: String?, title: String, body: String) {
        noteID?.let { edit(noteID, title, body) } ?: register(title, body)
    }

    override suspend fun delete(noteID: String) {
        register.unregister(noteID)
    }

    private suspend fun edit(noteID: String, title: String, body: String) {
        editor.setTitle(noteID, title)
        editor.setBody(noteID, body)
    }

    private suspend fun register(title: String, body: String) {
        register.register(title, body = body)
    }
}
