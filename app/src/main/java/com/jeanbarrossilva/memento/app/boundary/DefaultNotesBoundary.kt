package com.jeanbarrossilva.memento.app.boundary

import android.content.Context
import com.jeanbarrossilva.memento.feature.editor.EditorActivity
import com.jeanbarrossilva.memento.feature.notes.NotesBoundary

internal class DefaultNotesBoundary : NotesBoundary {
    override fun navigateToEditor(context: Context, noteID: String?) {
        EditorActivity.start(context, noteID)
    }
}
