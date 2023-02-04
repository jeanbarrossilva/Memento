package com.jeanbarrossilva.memento.feature.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.aurelius.utils.flowOf
import com.jeanbarrossilva.memento.feature.notes.domain.Selection
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll

internal class NotesViewModel(application: Application, private val gateway: NotesGateway) :
    AndroidViewModel(application) {
    private val defaultFolder =
        flowOf(NoteFolder.empty) { emit(gateway.getDefaultFolder(application)) }
    private val folders = flowOf(emptyList()) { emitAll(gateway.getFolders(application)) }
    private val notes = flowOf(emptyList()) { emitAll(gateway.getNotes()) }

    val currentFolder =
        flowOf<NoteFolder?>(defaultFolder, NoteFolder.empty) { emitAll(gateway.getCurrentFolder()) }
    val selection = MutableStateFlow<Selection>(Selection.Off)

    fun getDefaultFolder(): StateFlow<NoteFolder> {
        return defaultFolder.asStateFlow()
    }

    fun getFolders(): StateFlow<List<NoteFolder>> {
        return folders.asStateFlow()
    }

    fun getNotes(): StateFlow<List<Note>> {
        return notes.asStateFlow()
    }

    companion object {
        fun createFactory(application: Application, gateway: NotesGateway):
            ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(NotesViewModel::class) {
                    NotesViewModel(application, gateway)
                }
            }
        }
    }
}
