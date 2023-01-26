package com.jeanbarrossilva.memento.core.register.editor

import com.jeanbarrossilva.memento.core.register.TestRegister
import com.jeanbarrossilva.memento.core.register.domain.Color
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class EditorTests {
    private val register = TestRegister()
    private val editor = TestEditor(register)
    private lateinit var noteID: String

    @BeforeTest
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        runTest {
            noteID = register.register("Title", "Body", Color.BLUE)
        }
    }

    @AfterTest
    @OptIn(ExperimentalCoroutinesApi::class)
    fun tearDown() {
        runTest {
            register
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a note WHEN setting its title THEN it's set`() {
        runTest {
            editor.setTitle(noteID, "New title")
            assertEquals(register.getNoteByID(noteID)?.title, "New title")
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a note WHEN setting its body THEN it's set`() {
        runTest {
            editor.setBody(noteID, "New body")
            assertEquals(register.getNoteByID(noteID)?.body, "New body")
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a note WHEN setting its color THEN it's set`() {
        runTest {
            editor.setColor(noteID, Color.YELLOW)
            assertEquals(register.getNoteByID(noteID)?.color, Color.YELLOW)
        }
    }
}