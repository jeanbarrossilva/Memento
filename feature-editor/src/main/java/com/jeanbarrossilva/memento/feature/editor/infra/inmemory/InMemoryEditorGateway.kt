package com.jeanbarrossilva.memento.feature.editor.infra.inmemory

import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway

internal class InMemoryEditorGateway : EditorGateway {
    override suspend fun getNoteById(noteID: String): Note {
        return Note.sample
    }

    override suspend fun save(noteID: String?, title: String, body: String, colors: NoteColors) {
    }

    override suspend fun delete(noteID: String) {
    }
}
