package com.jeanbarrossilva.memento.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.aurelius.utils.flowOf
import com.jeanbarrossilva.memento.notes.domain.Selection
import com.jeanbarrossilva.memento.notes.domain.note.Note
import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.notes.infra.NotesGateway
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class NotesViewModel(private val gateway: NotesGateway) : ViewModel() {
    private val defaultFolder = flowOf(NoteFolder.empty) { emit(gateway.getDefaultFolder()) }
    private val folders = flowOf(emptyList()) { emit(gateway.getFolders()) }
    private val notes = flowOf(emptyList()) { emit(gateway.getNotes()) }

    val currentFolder = flowOf<NoteFolder?>(defaultFolder, NoteFolder.empty) {
        gateway.getCurrentFolder()?.let {
            emit(it)
        }
    }
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
        fun createFactory(gateway: NotesGateway): ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(NotesViewModel::class) {
                    NotesViewModel(gateway)
                }
            }
        }
    }
}
