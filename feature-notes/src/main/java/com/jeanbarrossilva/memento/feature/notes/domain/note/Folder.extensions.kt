package com.jeanbarrossilva.memento.feature.notes.domain.note

import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder as _Folder

/** Adapts the given [Folder] to the current domain. **/
internal fun Folder.adapt(): _Folder {
    return _Folder(path, title)
}
