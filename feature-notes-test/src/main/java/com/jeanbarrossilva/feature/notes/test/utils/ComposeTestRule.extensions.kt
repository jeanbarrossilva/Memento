package com.jeanbarrossilva.feature.notes.test.utils

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.ui.actionable.NOTE_CARD_TAG

/**
 * [SemanticsNodeInteraction] of the given [note]'s card.
 *
 * @param note [Note] whose card's [SemanticsNodeInteraction] will be returned.
 **/
fun ComposeTestRule.onNoteCard(note: Note): SemanticsNodeInteraction {
    return onAllNodesWithTag(NOTE_CARD_TAG).filter(hasTextExactly(note.title, note.body)).onFirst()
}
