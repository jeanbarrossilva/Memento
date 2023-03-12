package com.jeanbarrossilva.memento.feature.notes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.utils.serialize
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import org.junit.Rule
import org.junit.Test

internal class NotesTests {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun showsLoadingSpinnerWhileLoading() {
        composeRule.setContent {
            AureliusTheme {
                Notes(Loadable.Loading())
            }
        }
        composeRule.onNodeWithTag(LOADING_NOTES_TAG).assertIsDisplayed()
    }

    @Test
    fun showsNotesAfterLoaded() {
        composeRule.setContent {
            AureliusTheme {
                Notes(Loadable.Loaded(Note.samples.serialize()))
            }
        }
        composeRule.onNodeWithTag(LOADED_NOTES_TAG).assertIsDisplayed()
    }

    @Test
    fun showsErrorOnFail() {
        composeRule.setContent {
            AureliusTheme {
                Notes(Loadable.Failed(Throwable()))
            }
        }
        composeRule.onNodeWithTag(FAILED_NOTES_TAG).assertIsDisplayed()
    }
}
