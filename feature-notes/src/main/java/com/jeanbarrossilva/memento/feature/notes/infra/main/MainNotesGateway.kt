package com.jeanbarrossilva.memento.feature.notes.infra.main

import android.content.Context
import com.jeanbarrossilva.memento.core.register.domain.Path
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.feature.notes.domain.note.adapt
import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import com.jeanbarrossilva.memento.feature.notes.infra.main.folder.CurrentNoteFolderDao
import com.jeanbarrossilva.memento.feature.notes.utils.toNoteFolder
import com.jeanbarrossilva.memento.feature.notes.utils.toNoteFolderEntity
import com.jeanbarrossilva.memento.notes.R

internal class MainNotesGateway(
    private val repository: Repository,
    private val currentNoteFolderDao: CurrentNoteFolderDao
) : NotesGateway {
    override suspend fun getDefaultFolder(context: Context): NoteFolder {
        val title = context.getString(R.string.feature_notes)
        return NoteFolder(Path.root.value, title)
    }

    override suspend fun getFolders(): List<NoteFolder> {
        return repository.getNotes().keys.map {
            it.toNoteFolder()
        }
    }

    override suspend fun getCurrentFolder(): NoteFolder? {
        return currentNoteFolderDao.select()?.toNoteFolder()
    }

    override suspend fun setCurrentFolder(currentFolder: NoteFolder) {
        val entity = currentFolder.toNoteFolderEntity()
        currentNoteFolderDao.select()?.pathValue?.let { currentNoteFolderDao.delete(it) }
        currentNoteFolderDao.insert(entity)
    }

    override suspend fun getNotes(): List<Note> {
        return repository.getNotes().values.flatten().map {
            it.adapt()
        }
    }
}
