package com.jeanbarrossilva.memento.feature.editor.domain // ktlint-disable filename

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/** Whether or not the given [EditorMode] is an [EditorMode.Editing]. **/
@OptIn(ExperimentalContracts::class)
internal fun EditorMode.isEditing(): Boolean {
    contract { returns(true) implies (this@isEditing is EditorMode.Editing) }
    return this is EditorMode.Editing
}
