package com.jeanbarrossilva.memento.feature.editor.utils

import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.core.register.infra.Repository
import kotlinx.coroutines.flow.last

/** Gets the last [Note] that's been added to this [Repository]. **/
internal suspend fun Repository.getLastNote(): Note? {
    return getNotes().last().lastOrNull()
}
