package com.jeanbarrossilva.memento.notes.domain

import com.jeanbarrossilva.memento.notes.domain.note.Note
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Whether or not the given [note] is in the [Selection].
 *
 * @param note [ContextualActivity] whose presence will be checked.
 **/
internal operator fun Selection.contains(note: Note): Boolean {
    return ifOn { isSelected(note) } ?: false
}

/**
 * Returns the result of the given [transform] if this is [off][Selection.Off].
 *
 * @param transform Operation to be made on [Selection.Off].
 **/
@OptIn(ExperimentalContracts::class)
internal inline fun <T> Selection.ifOff(transform: Selection.Off.() -> T): T? {
    contract { returnsNotNull() implies (this@ifOff is Selection.Off) }
    return when (this) {
        is Selection.Off -> transform()
        is Selection.On -> null
    }
}

/**
 * Returns the result of the given [transform] if this is [on][Selection.On].
 *
 * @param transform Operation to be made on [Selection.On].
 **/
@OptIn(ExperimentalContracts::class)
internal inline fun <T> Selection.ifOn(transform: Selection.On.() -> T): T? {
    contract { returnsNotNull() implies (this@ifOn is Selection.On) }
    return when (val selection = this) {
        is Selection.Off -> null
        is Selection.On -> selection.transform()
    }
}
