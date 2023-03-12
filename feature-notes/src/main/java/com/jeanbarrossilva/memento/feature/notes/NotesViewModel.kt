package com.jeanbarrossilva.memento.feature.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.loadable.utils.emptySerializableList
import com.jeanbarrossilva.loadable.utils.loadable
import com.jeanbarrossilva.memento.feature.notes.domain.Selection
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class NotesViewModel(application: Application, private val gateway: NotesGateway) :
    AndroidViewModel(application) {
    val currentFolder = MutableStateFlow(Folder.getDefault(application))
    val selection = MutableStateFlow<Selection>(Selection.Off)

    fun getNotes(): StateFlow<Loadable<SerializableList<Note>>> {
        return loadable(emptySerializableList(), gateway::getNotes)
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
