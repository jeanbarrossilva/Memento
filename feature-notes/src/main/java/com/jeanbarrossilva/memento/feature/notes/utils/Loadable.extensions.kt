package com.jeanbarrossilva.memento.feature.notes.utils

import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.loadable.utils.map
import com.jeanbarrossilva.loadable.utils.serialize

internal inline fun <I, reified O> Loadable<SerializableList<I>>.flatMap(transform: (I) -> O):
    Loadable<SerializableList<O>> {
    return map {
        it.map(transform).serialize()
    }
}

internal inline fun <I, reified O : Any> Loadable<SerializableList<I>>.flatMapNotNull(
    transform: (I) -> O?
) : Loadable<SerializableList<O>> {
    return map {
        it.mapNotNull(transform).serialize()
    }
}
