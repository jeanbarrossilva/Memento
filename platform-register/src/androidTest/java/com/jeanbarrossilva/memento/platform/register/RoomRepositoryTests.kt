package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.platform.register.rule.RegisterTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

internal class RoomRepositoryTests {
    @get:Rule
    val rule = RegisterTestRule()

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getNotes() {
        runTest {
            val firstNoteID = rule.register.register("1st title", body = "1st body")
            val firstNote = rule.repository.getNoteByID(firstNoteID)
            val secondNoteID = rule.register.register("2nd title", body = "2nd body")
            val secondNote = rule.repository.getNoteByID(secondNoteID)
            assertEquals(listOfNotNull(firstNote, secondNote), rule.repository.getNotes())
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getNoteByID() {
        runTest {
            val noteID = rule.register.register("Title", body = "Body")
            assertNotNull(rule.repository.getNoteByID(noteID))
        }
    }
}
