package com.jeanbarrossilva.memento.feature.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.aurelius.utils.flowOf
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

internal class EditorViewModel(
    application: Application,
    private val gateway: EditorGateway,
    private val noteID: String?
) : AndroidViewModel(application) {
    private val originalNote = flow {
        noteID?.let {
            emit(gateway.getNoteById(it))
        }
    }
    private val editedNote = flowOf(originalNote, Note.getEmpty(application))

    val isEditing = MutableStateFlow(noteID == null)

    fun getEditedNote(): StateFlow<Note> {
        return editedNote.asStateFlow()
    }

    fun setTitle(title: String) {
        edit {
            copy(title = title)
        }
    }

    fun setBody(body: String) {
        edit {
            copy(body = body)
        }
    }

    fun save() {
        viewModelScope.launch {
            gateway.save(noteID, getEditedNote().value.title, getEditedNote().value.body)
        }
        isEditing.value = false
    }

    private fun edit(edit: Note.() -> Note) {
        editedNote.value = getEditedNote().value.edit()
    }

    companion object {
        fun createFactory(application: Application, gateway: EditorGateway, noteID: String?):
            ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(EditorViewModel::class) {
                    EditorViewModel(application, gateway, noteID)
                }
            }
        }
    }
}
