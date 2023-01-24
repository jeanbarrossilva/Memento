package com.jeanbarrossilva.memento.feature.notes

import android.content.Context

interface NotesBoundary {
    fun navigateToEditor(context: Context, noteID: String?)
}
