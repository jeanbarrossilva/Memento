package com.jeanbarrossilva.memento.core.register.domain

data class Path(val value: String) {
    val isRoot
        get() = this == root

    init {
        assert(value.isNotBlank()) { "Path cannot be empty." }
        assert(value.startsWith(SEPARATOR)) { "Path $this should start with a '/'." }
        assert(value.filter { it == SEPARATOR }.length == 1) { "Path cannot be nested." }
    }

    companion object {
        internal const val SEPARATOR = '/'

        val root = Path("$SEPARATOR")
    }
}
