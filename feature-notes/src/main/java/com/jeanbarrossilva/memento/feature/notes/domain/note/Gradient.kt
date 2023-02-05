package com.jeanbarrossilva.memento.feature.notes.domain.note

import androidx.compose.ui.graphics.Color

enum class Gradient {
    BLUE {
        override val start = Color(0xFFD1ECFF)
        override val end = Color(0xFFA5FFFA)
    },
    PURPLE {
        override val start = Color(0xFFFFDBFB)
        override val end = Color(0xFFFFDBFB)
    },
    YELLOW {
        override val start = Color(0xFFFDFFA3)
        override val end = Color(0xFFFFDF70)
    };

    internal abstract val start: Color
    internal abstract val end: Color
}
