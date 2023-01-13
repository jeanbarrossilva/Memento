package com.jeanbarrossilva.memento.feature.notes.utils // ktlint-disable filename

/**
 * Adds the given [element] to the [MutableCollection] if it's not in it or removes it if it is.
 *
 * @param element Element to toggle.
 **/
internal fun <T> MutableCollection<T>.toggle(element: T) {
    if (element in this) remove(element) else add(element)
}
