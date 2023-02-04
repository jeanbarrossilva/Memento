package com.jeanbarrossilva.memento.feature.editor.utils

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.Note as _Note

/** Adapts the given [Note] to the current domain. **/
internal fun Note.adapt(): _Note {
    val colors = color.adapt()
    return _Note(title, body, colors, lastEditedAt = "Yesterday, 23:59")
}
