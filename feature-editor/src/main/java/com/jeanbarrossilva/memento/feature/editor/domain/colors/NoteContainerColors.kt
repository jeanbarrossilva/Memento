package com.jeanbarrossilva.memento.feature.editor.domain.colors

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.jeanbarrossilva.memento.aurelius.R

internal data class NoteContainerColors(val primary: Color, val secondary: Color) {
    companion object {
        val Blue = NoteContainerColors(primary = Color(0xFFD1ECFF), secondary = Color(0xFFA5FFFA))
        val Purple = NoteContainerColors(primary = Color(0xFFFFDBFB), secondary = Color(0xFFFFDBFB))
        val Yellow = NoteContainerColors(primary = Color(0xFFFDFFA3), secondary = Color(0xFFFFDF70))

        fun getEmpty(context: Context): NoteContainerColors {
            val colorValue = context.getColor(R.color.background)
            val color = Color(colorValue)
            return NoteContainerColors(primary = color, secondary = color)
        }
    }
}
