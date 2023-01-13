package com.jeanbarrossilva.memento.feature.notes.infra.inmemory

import android.content.Context
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import com.jeanbarrossilva.memento.feature.notes.utils.uuid
import com.jeanbarrossilva.memento.notes.R
import java.lang.ref.WeakReference

internal class InMemoryNotesGateway(private val contextRef: WeakReference<Context>) :
    NotesGateway {
    private val context
        get() = contextRef.get()

    private var currentFolder: NoteFolder? = null

    constructor(context: Context?) : this(WeakReference(context))

    override suspend fun getDefaultFolder(): NoteFolder {
        val title = context?.getString(R.string.feature_notes).orEmpty()
        return NoteFolder(uuid(), title)
    }

    override suspend fun getFolders(): List<NoteFolder> {
        return NoteFolder.samples
    }

    override suspend fun getCurrentFolder(): NoteFolder? {
        return currentFolder
    }

    override suspend fun setCurrentFolder(currentFolder: NoteFolder) {
        this.currentFolder = currentFolder
    }

    override suspend fun getNotes(): List<Note> {
        return Note.samples
    }
}
