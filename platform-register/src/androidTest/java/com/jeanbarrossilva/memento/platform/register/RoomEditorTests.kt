package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.platform.register.rule.RegisterTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RoomEditorTests {
    private lateinit var noteID: String

    @get:Rule
    val rule = RegisterTestRule()

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        runTest {
            noteID = rule.register.register("Title", "Body", Color.BLUE)
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setTitle() {
        runTest {
            rule.editor.setTitle(noteID, "New title")
            assertEquals("New title", rule.repository.getNoteByID(noteID)?.title)
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setBody() {
        runTest {
            rule.editor.setBody(noteID, "New body")
            assertEquals("New body", rule.repository.getNoteByID(noteID)?.body)
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setColors() {
        runTest {
            rule.editor.setColor(noteID, Color.YELLOW)
            assertEquals(Color.YELLOW, rule.repository.getNoteByID(noteID)?.color)
        }
    }
}
