package com.jeanbarrossilva.memento.feature.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.memento.feature.editor.domain.EditorMode
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.domain.isEditing
import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway
import com.jeanbarrossilva.memento.platform.extensions.flowOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

internal class EditorViewModel(private val gateway: EditorGateway, private val noteID: String?) :
    ViewModel() {
    private val initialMode =
        noteID?.let { EditorMode.Reading } ?: EditorMode.Editing()
    private val mode = MutableStateFlow(initialMode)
    private val originalNote = flow {
        noteID?.let { emitAll(gateway.getNoteById(it)) } ?: emit(null)
    }
    private val editedNote = flowOf(Note.empty) { originalNote.filterNotNull() }

    fun getMode(): StateFlow<EditorMode> {
        return mode.asStateFlow()
    }

    fun getEditedNote(): StateFlow<Note> {
        return editedNote.asStateFlow()
    }

    fun edit() {
        mode.value = EditorMode.Editing()
    }

    fun setColorPickerVisible(isColorPickerVisible: Boolean) {
        val currentMode = getMode().value
        if (currentMode.isEditing()) {
            mode.value = currentMode.copy(isColorPickerVisible)
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
        val note = getEditedNote().value
        viewModelScope.launch { gateway.save(noteID, note.title, note.body, note.colors) }
        mode.value = EditorMode.Reading
    }

    private fun edit(edit: Note.() -> Note) {
        editedNote.value = getEditedNote().value.edit()
    }

    companion object {
        fun createFactory(gateway: EditorGateway, noteID: String?):
            ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(EditorViewModel::class) {
                    EditorViewModel(gateway, noteID)
                }
            }
        }
    }
}
