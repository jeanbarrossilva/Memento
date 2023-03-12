package com.jeanbarrossilva.memento.feature.notes.infra.main

import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.loadable.utils.loadable
import com.jeanbarrossilva.loadable.utils.serialize
import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Register
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
    private val register: Register,
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

    override suspend fun getNotes(): Flow<Loadable<SerializableList<_Note>>> {
        return repository.getNotes().map { it.map(Note::adapt).serialize() }.loadable()
    }

    override suspend fun delete(notes: List<_Note>) {
        notes.forEach {
            register.unregister(it.id)
        }
    }
}
