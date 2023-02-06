package com.jeanbarrossilva.memento.platform.register

import app.cash.turbine.test
import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.platform.register.test.RegisterTestRule
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
            noteID = rule.register.register("Title", body = "Body", color = Color.BLUE)
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setTitle() {
        runTest {
            rule.editor.setTitle(noteID, "New title")
            rule.repository.getNoteByID(noteID).test {
                assertEquals("New title", awaitItem()?.title)
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setBody() {
        runTest {
            rule.editor.setBody(noteID, "New body")
            rule.repository.getNoteByID(noteID).test { assertEquals("New body", awaitItem()?.body) }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setColors() {
        runTest {
            rule.editor.setColor(noteID, Color.YELLOW)
            rule.repository.getNoteByID(noteID).test {
                assertEquals(Color.YELLOW, awaitItem()?.color)
            }
        }
    }
}
