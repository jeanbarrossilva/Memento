package com.jeanbarrossilva.memento.feature.notes.domain.note

/** An empty [NoteFolder] if the receiver one is `null`. **/
internal val NoteFolder?.orEmpty
    get() = this ?: NoteFolder.empty
