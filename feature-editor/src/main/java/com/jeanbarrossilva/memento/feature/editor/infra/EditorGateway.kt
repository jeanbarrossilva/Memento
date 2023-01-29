package com.jeanbarrossilva.memento.feature.editor.infra

import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors

internal interface EditorGateway {
    suspend fun getNoteById(noteID: String): Note?

    suspend fun save(noteID: String?, title: String, body: String, colors: NoteColors)

    suspend fun delete(noteID: String)
}
