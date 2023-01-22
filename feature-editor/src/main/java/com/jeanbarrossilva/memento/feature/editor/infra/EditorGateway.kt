package com.jeanbarrossilva.memento.feature.editor.infra

import com.jeanbarrossilva.memento.feature.editor.domain.Note

internal interface EditorGateway {
    suspend fun getNoteById(noteID: String): Note

    suspend fun save(noteID: String?, title: String, body: String)

    suspend fun delete(noteID: String)
}
