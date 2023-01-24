package com.jeanbarrossilva.memento.feature.editor.utils // ktlint-disable filename

/**
 * Removes the given [value] from this [String].
 *
 * @param value Occurrence to be removed.
 **/
internal infix fun String.without(value: String): String {
    return replace(value, "")
}
