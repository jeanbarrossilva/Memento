package com.jeanbarrossilva.memento.feature.notes.domain.note

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note as _Note

/** Adapts the given [Note] to the current domain. **/
internal fun Note.adapt(): _Note {
    val colors = color.adapt()
    return _Note(id, title, body, colors, lastEditedAt = "Yesterday, 23:59")
}
