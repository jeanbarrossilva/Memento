package com.jeanbarrossilva.memento.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.memento.notes.domain.Selection
import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.notes.infra.NotesGateway
import com.jeanbarrossilva.memento.ui.utils.flowOf
import kotlinx.coroutines.flow.MutableStateFlow

internal class NotesViewModel(private val gateway: NotesGateway) : ViewModel() {
    val defaultFolder = flowOf(NoteFolder.empty) { emit(gateway.getDefaultFolder()) }
    val currentFolder = flowOf<NoteFolder?>(defaultFolder, NoteFolder.empty) {
        gateway.getCurrentFolder()?.let {
            emit(it)
        }
    }
    val folders = flowOf(emptyList()) { emit(gateway.getFolders()) }
    val notes = flowOf(emptyList()) { emit(gateway.getNotes()) }
    val selection = MutableStateFlow<Selection>(Selection.Off)

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
