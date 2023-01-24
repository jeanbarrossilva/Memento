package com.jeanbarrossilva.memento.feature.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.aurelius.utils.flowOf
import com.jeanbarrossilva.memento.feature.editor.domain.EditorMode
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.domain.isEditing
import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

internal class EditorViewModel(
    application: Application,
    private val gateway: EditorGateway,
    private val noteID: String?
) : AndroidViewModel(application) {
    private val mode = flowOf<EditorMode>(flow { emit(getInitialMode()) }, EditorMode.Reading)
    private val originalNote = flow {
        noteID?.let {
            emit(gateway.getNoteById(it))
        }
    }
    private val editedNote = flowOf(originalNote, Note.getEmpty(application))

    fun getMode(): StateFlow<EditorMode> {
        return mode.asStateFlow()
    }

    fun getEditedNote(): StateFlow<Note> {
        return editedNote.asStateFlow()
    }

    fun edit() {
        viewModelScope.launch {
            mode.value = getEditingMode()
        }
    }

    fun setColorPickerVisible(isColorPickerVisible: Boolean) {
        viewModelScope.launch {
            val currentMode = getMode().value
            if (currentMode.isEditing()) {
                mode.value = currentMode.copy(
                    colors = if (isColorPickerVisible) gateway.getColors() else null
                )
            }
        }
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

    fun setColors(colors: NoteColors) {
        edit {
            copy(colors = colors)
        }
    }

    fun save() {
        viewModelScope.launch {
            gateway.save(noteID, getEditedNote().value.title, getEditedNote().value.body)
        }
        mode.value = EditorMode.Reading
    }

    private suspend fun getInitialMode(): EditorMode {
        return noteID?.let { EditorMode.Reading } ?: getEditingMode()
    }

    private suspend fun getEditingMode(): EditorMode.Editing {
        val colors = gateway.getColors()
        return EditorMode.Editing(colors)
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
