package com.jeanbarrossilva.memento.app.fixtures

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection

/**
 * Converts the given [SemanticsNodeInteractionCollection] into a [List] of
 * [SemanticsNodeInteraction]s.
 **/
internal fun SemanticsNodeInteractionCollection.toList(): List<SemanticsNodeInteraction> {
    return (0..fetchSemanticsNodes().size).map(::get)
}
