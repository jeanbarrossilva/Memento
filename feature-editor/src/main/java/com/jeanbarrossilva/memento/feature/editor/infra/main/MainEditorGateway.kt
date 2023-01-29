package com.jeanbarrossilva.memento.feature.editor.infra.main

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.infra.Editor
import com.jeanbarrossilva.memento.core.register.infra.Register
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway
import com.jeanbarrossilva.memento.feature.editor.utils.adapt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MainEditorGateway(
    private val repository: Repository,
    private val register: Register,
    private val editor: Editor
) : EditorGateway {
    override suspend fun getNoteById(noteID: String): Flow<Note?> {
        return repository.getNoteByID(noteID).map {
            it?.adapt()
        }
    }

    override suspend fun save(noteID: String?, title: String, body: String, colors: NoteColors) {
        val color = colors.toColor()
        noteID?.let { edit(noteID, title, body, color) } ?: register(title, body, color)
    }

    override suspend fun delete(noteID: String) {
        register.unregister(noteID)
    }

    private suspend fun edit(noteID: String, title: String, body: String, color: Color) {
        editor.setTitle(noteID, title)
        editor.setBody(noteID, body)
        editor.setColor(noteID, color)
    }

    private suspend fun register(title: String, body: String, color: Color) {
        register.register(title, body = body, color = color)
    }
}
