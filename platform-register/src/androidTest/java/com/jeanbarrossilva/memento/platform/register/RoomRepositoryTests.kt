package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.platform.register.test.RegisterTestRule
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
            val folder = Folder.titled("Folder")
            val firstNoteID = rule.register.register("1st title", body = "1st body")
            val firstNote = rule.repository.getNoteByID(firstNoteID)
            val secondNoteID =
                rule.register.register("2nd title", folder, body = "2nd body")
            val secondNote = rule.repository.getNoteByID(secondNoteID)
            assertEquals(
                mapOf(null to listOf(firstNote), folder to listOf(secondNote)),
                rule.repository.getNotes()
            )
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
