package com.jeanbarrossilva.memento.core.register.domain

data class Note(
    val id: String,
    val path: Path,
    val title: String,
    val body: String,
    val color: Color
)
