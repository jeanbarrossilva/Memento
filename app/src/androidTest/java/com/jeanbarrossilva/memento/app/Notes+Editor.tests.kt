package com.jeanbarrossilva.memento.app

import androidx.compose.ui.test.TouchInjectionScope
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import com.jeanbarrossilva.feature.notes.test.utils.onNoteCard
import com.jeanbarrossilva.memento.app.fixtures.createAndroidComposeRule
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class NotesEditorTests {
    private val note = Note.samples.first()

    @get:Rule
    val composeRule = createAndroidComposeRule()

    @Before
    fun setUp() {
        registerNote()
    }

    @After
    fun tearDown() {
        unregisterNote()
    }

    @Test
    fun loadsNotesDetailsAfterClickingItsCard() {
        composeRule.onNoteCard(note).performClick()
        composeRule.onNoteHeadlineTitle().assertTextEquals(note.title)
        composeRule.onNoteBody().assertTextEquals(note.body)
        composeRule.onNavigationButton().performClick()
    }

    private fun registerNote() {
        composeRule.onFab().performClick()
        composeRule.onNoteHeadlineTitle().performTextInput(note.title)
        composeRule.onNoteHeadlineColorBubble(note.gradient).performClick()
        composeRule.onNoteBody().performTextInput(note.body)
        composeRule.onFab().performClick()
        composeRule.onNavigationButton().performClick()
    }

    private fun unregisterNote() {
        composeRule.onNoteCard(note).performTouchInput(TouchInjectionScope::longClick)
        composeRule.onDeleteAction().performClick()
        composeRule.onConfirmationButton().performClick()
    }
}
