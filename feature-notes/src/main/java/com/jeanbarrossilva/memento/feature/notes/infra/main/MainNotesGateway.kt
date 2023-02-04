package com.jeanbarrossilva.memento.feature.notes.infra.main

import android.content.Context
import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.feature.notes.domain.note.adapt
import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import com.jeanbarrossilva.memento.feature.notes.infra.main.folder.CurrentNoteFolderDao
import com.jeanbarrossilva.memento.feature.notes.utils.toNoteFolder
import com.jeanbarrossilva.memento.feature.notes.utils.toNoteFolderEntity
import com.jeanbarrossilva.memento.notes.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

internal class MainNotesGateway(
    private val repository: Repository,
    private val currentNoteFolderDao: CurrentNoteFolderDao
) : NotesGateway {
    override suspend fun getDefaultFolder(context: Context): NoteFolder {
        val title = context.getString(R.string.feature_notes)
        return Folder.titled(title).toNoteFolder()
    }

    override suspend fun getFolders(context: Context): Flow<List<NoteFolder>> {
        return repository.getNotes().map { map ->
            map.keys.map { folder ->
                folder?.toNoteFolder() ?: getDefaultFolder(context)
            }
        }
    }

    override suspend fun getCurrentFolder(): Flow<NoteFolder?> {
        return currentNoteFolderDao.select().map {
            it?.toNoteFolder()
        }
    }

    override suspend fun setCurrentFolder(currentFolder: NoteFolder) {
        val entity = currentFolder.toNoteFolderEntity()
        currentNoteFolderDao
            .select()
            .filterNotNull()
            .map { it.path }
            .collect(currentNoteFolderDao::delete)
        currentNoteFolderDao.insert(entity)
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return repository.getNotes().map { map ->
            map.values.flatten().map {
                it.adapt()
            }
        }
    }
}
