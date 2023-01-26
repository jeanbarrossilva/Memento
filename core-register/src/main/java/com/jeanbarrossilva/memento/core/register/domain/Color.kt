package com.jeanbarrossilva.memento.core.register.domain

enum class Color(val id: String) {
    BLUE("blue"),
    PURPLE("purple"),
    YELLOW("purple");

    override fun toString(): String {
        return id
    }
}
