package com.jeanbarrossilva.memento.ui.actionable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.feature.notes.test.utils.onNoteCard
import com.jeanbarrossilva.memento.feature.notes.domain.Selection
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.ui.actionable.NoteCard
import org.junit.Rule
import org.junit.Test

internal class NoteCardTests {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun onNoteCard() {
        val note = Note.sample
        composeRule.setContent {
            AureliusTheme {
                NoteCard(note, Selection.Off, onSelectionToggle = { }, onClick = { })
            }
        }
        composeRule.onNoteCard(note).assertIsDisplayed()
    }
}
