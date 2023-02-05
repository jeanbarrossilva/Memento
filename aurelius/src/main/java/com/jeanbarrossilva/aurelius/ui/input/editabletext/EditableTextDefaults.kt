package com.jeanbarrossilva.aurelius.ui.input.editabletext

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

object EditableTextDefaults {
    @Composable
    fun colors(
        text: Color = LocalTextStyle.current.color.takeOrElse { LocalContentColor.current },
        label: Color = text.copy(AureliusTheme.visibility.medium),
        cursor: Color = text
    ): EditableTextColors {
        return EditableTextColors(text, label, cursor)
    }
}
