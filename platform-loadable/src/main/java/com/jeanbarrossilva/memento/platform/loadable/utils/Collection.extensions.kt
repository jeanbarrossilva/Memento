package com.jeanbarrossilva.memento.platform.loadable.utils

import com.jeanbarrossilva.memento.platform.loadable.type.SerializableList
import java.io.Serializable

/**
 * Serializes the given [Collection] by converting it into a [SerializableList].
 *
 * @see Serializable
 **/
inline fun <reified T> Collection<T>.serialize(): SerializableList<T> {
    return serializableListOf(*toTypedArray())
}
