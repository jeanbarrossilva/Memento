package com.jeanbarrossilva.memento.feature.editor.domain.colors

import androidx.compose.ui.graphics.Color

data class NoteContainerColors internal constructor(val primary: Color, val secondary: Color) {
    companion object {
        internal val Blue =
            NoteContainerColors(primary = Color(0xFFD1ECFF), secondary = Color(0xFFA5FFFA))
        internal val Purple =
            NoteContainerColors(primary = Color(0xFFFFDBFB), secondary = Color(0xFFFFDBFB))
        internal val Yellow =
            NoteContainerColors(primary = Color(0xFFFDFFA3), secondary = Color(0xFFFFDF70))
    }
}
