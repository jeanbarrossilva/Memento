package com.jeanbarrossilva.memento.core.register.utils

internal fun <T> MutableList<T>.replaceBy(replacement: T.() -> T, predicate: (T) -> Boolean) {
    val iterator = iterator()
    while (iterator.hasNext()) {
        val candidate = iterator.next()
        if (predicate(candidate)) {
            this[indexOf(candidate)] = candidate.replacement()
            break
        }
    }
}
