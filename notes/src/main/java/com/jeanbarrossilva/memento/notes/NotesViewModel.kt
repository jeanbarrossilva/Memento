package com.jeanbarrossilva.memento.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.memento.notes.domain.Selection
import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.notes.infra.NotesGateway
import com.jeanbarrossilva.memento.ui.utils.flowOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class NotesViewModel(private val gateway: NotesGateway) : ViewModel() {
    private val currentFolder = MutableStateFlow<NoteFolder>(NoteFolder.All)

    val folders = flowOf(emptyList()) { emit(gateway.getFolders()) }
    val notes = flowOf(emptyList()) { emit(gateway.getNotes()) }
    val selection = MutableStateFlow<Selection>(Selection.Off)

    fun getCurrentFolder(): StateFlow<NoteFolder> {
        return currentFolder.asStateFlow()
    }

    fun setCurrentFolder(currentFolder: NoteFolder) {
        this.currentFolder.value = currentFolder
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
