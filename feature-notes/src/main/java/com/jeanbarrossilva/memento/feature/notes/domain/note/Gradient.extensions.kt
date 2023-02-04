package com.jeanbarrossilva.memento.feature.notes.domain.note

import com.jeanbarrossilva.memento.core.register.domain.Color

/** Adapts the given [Color] to the current domain. **/
internal fun Color.adapt(): Gradient {
    return when (this) {
        Color.BLUE -> Gradient.BLUE
        Color.PURPLE -> Gradient.PURPLE
        Color.YELLOW -> Gradient.YELLOW
    }
}
