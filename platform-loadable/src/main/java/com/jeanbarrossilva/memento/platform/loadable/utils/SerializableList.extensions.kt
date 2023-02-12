package com.jeanbarrossilva.memento.platform.loadable.utils

import com.jeanbarrossilva.memento.platform.loadable.type.SerializableList

/**
 * Creates a [SerializableList] without elements.
 *
 * @see serializableListOf
 **/
fun <T> emptySerializableList(): SerializableList<T> {
    return serializableListOf()
}

/**
 * Creates a new [SerializableList] with the given [elements].
 *
 * @param elements Elements to be added to the [SerializableList].
 **/
fun <T> serializableListOf(vararg elements: T): SerializableList<T> {
    val elementsAsList = elements.toList()
    return SerializableList(elementsAsList)
}
