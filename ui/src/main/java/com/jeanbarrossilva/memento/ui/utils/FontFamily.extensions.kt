package com.jeanbarrossilva.memento.ui.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.jeanbarrossilva.memento.ui.R

internal val FontFamily.Companion.DMSans
    get() = FontFamily(
        Font(R.font.dm_sans_normal),
        Font(R.font.dm_sans_normal_italic, style = FontStyle.Italic),
        Font(R.font.dm_sans_medium, FontWeight.Medium),
        Font(R.font.dm_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.dm_sans_bold, FontWeight.Bold),
        Font(R.font.dm_sans_bold_italic, FontWeight.Bold, FontStyle.Italic)
    )
