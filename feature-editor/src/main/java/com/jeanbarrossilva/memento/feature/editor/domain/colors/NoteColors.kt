package com.jeanbarrossilva.memento.feature.editor.domain.colors

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.jeanbarrossilva.memento.ui.R

internal data class NoteColors(val container: NoteContainerColors, val content: Color) {
    companion object {
        val sample = NoteColors(NoteContainerColors.sample, content = Color.Black)

        fun getEmpty(context: Context): NoteColors {
            val container = NoteContainerColors.getEmpty(context)
            val contentValue = context.getColor(R.color.text_highlighted)
            val content = Color(contentValue)
            return NoteColors(container, content)
        }
    }
}