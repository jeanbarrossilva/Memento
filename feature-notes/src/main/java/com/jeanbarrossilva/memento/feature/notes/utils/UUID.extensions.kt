package com.jeanbarrossilva.memento.feature.notes.utils // ktlint-disable filename

import java.util.UUID

/** Generates a random [UUID] and converts it into a [String]. **/
internal fun uuid(): String {
    return UUID.randomUUID().toString()
}
