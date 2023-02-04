package com.jeanbarrossilva.memento.feature.notes.infra.main

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note as _Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.adapt
import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import com.jeanbarrossilva.memento.feature.notes.infra.main.folder.CurrentNoteFolderDao
import com.jeanbarrossilva.memento.feature.notes.infra.main.folder.CurrentNoteFolderEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

internal class MainNotesGateway(
    private val repository: Repository,
    private val currentNoteFolderDao: CurrentNoteFolderDao
) : NotesGateway {
    override suspend fun getCurrentFolder(): Flow<Folder?> {
        return currentNoteFolderDao.select().map {
            it?.toFolder()
        }
    }

    override suspend fun setCurrentFolder(currentFolder: Folder) {
        val entity = CurrentNoteFolderEntity(currentFolder.path)
        currentNoteFolderDao
            .select()
            .filterNotNull()
            .map { it.path }
            .collect(currentNoteFolderDao::delete)
        currentNoteFolderDao.insert(entity)
    }

    override suspend fun getNotes(): Flow<List<_Note>> {
        return repository.getNotes().map {
            it.map(Note::adapt)
        }
    }
}
