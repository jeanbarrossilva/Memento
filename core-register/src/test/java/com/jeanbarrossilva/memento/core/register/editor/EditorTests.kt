package com.jeanbarrossilva.memento.core.register.editor

import app.cash.turbine.test
import com.jeanbarrossilva.memento.core.register.TestRegister
import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.repository.TestRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class EditorTests {
    private val repository = TestRepository()
    private val register = TestRegister(repository)
    private val editor = TestEditor(repository)
    private lateinit var noteID: String

    @BeforeTest
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        runTest {
            noteID = register.register("Title", body = "Body")
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
            repository.getNoteByID(noteID).test { assertEquals("New title", awaitItem()?.title) }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a note WHEN setting its body THEN it's set`() {
        runTest {
            editor.setBody(noteID, "New body")
            repository.getNoteByID(noteID).test { assertEquals("New body", awaitItem()?.body) }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a note WHEN setting its color THEN it's set`() {
        runTest {
            editor.setColor(noteID, Color.YELLOW)
            repository.getNoteByID(noteID).test { assertEquals(Color.YELLOW, awaitItem()?.color) }
        }
    }
}
