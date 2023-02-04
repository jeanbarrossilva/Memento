package com.jeanbarrossilva.memento.feature.editor.domain

internal sealed interface EditorMode {
    object Reading : EditorMode

    data class Editing(val isColorPickerVisible: Boolean = true) : EditorMode
}
