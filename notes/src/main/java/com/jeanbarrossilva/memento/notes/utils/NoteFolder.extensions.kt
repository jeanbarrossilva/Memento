package com.jeanbarrossilva.memento.notes.utils

import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder

/** An empty [NoteFolder] if the receiver one is `null`. **/
internal val NoteFolder?.orEmpty
    get() = this ?: NoteFolder.empty
