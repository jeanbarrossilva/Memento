package com.jeanbarrossilva.memento.core.register.infra

import com.jeanbarrossilva.memento.core.register.domain.Color

interface Editor {
    suspend fun setTitle(noteID: String, title: String)

    suspend fun setBody(noteID: String, body: String)

    suspend fun setColor(noteID: String, color: Color)
}
