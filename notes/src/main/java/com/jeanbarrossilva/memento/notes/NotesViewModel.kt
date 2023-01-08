package com.jeanbarrossilva.memento.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.memento.notes.domain.Selection
import com.jeanbarrossilva.memento.ui.utils.flowOf
import kotlinx.coroutines.flow.MutableStateFlow

internal class NotesViewModel(private val gateway: NotesGateway) : ViewModel() {
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
