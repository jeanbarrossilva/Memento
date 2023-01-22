package com.jeanbarrossilva.memento.feature.editor.domain.colors

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.jeanbarrossilva.memento.ui.R

internal data class NoteContainerColors(val primary: Color, val secondary: Color) {
    companion object {
        val sample = NoteContainerColors(primary = Color(0xFFFDFFA3), secondary = Color(0xFFFFDF70))

        fun getEmpty(context: Context): NoteContainerColors {
            val colorValue = context.getColor(R.color.background)
            val color = Color(colorValue)
            return NoteContainerColors(primary = color, secondary = color)
        }
    }
}
