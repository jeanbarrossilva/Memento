package com.jeanbarrossilva.memento.feature.notes.domain.note

import com.jeanbarrossilva.memento.feature.notes.utils.uuid

internal data class NoteFolder(val id: String, val title: String) {
    companion object {
        private val stoicism = NoteFolder(uuid(), title = "Stoicism")

        val empty = NoteFolder(id = "", title = "")
        val sample = stoicism
        val samples = listOf(stoicism)
    }
}
