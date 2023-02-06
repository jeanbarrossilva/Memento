package com.jeanbarrossilva.memento.platform.register

import app.cash.turbine.test
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
            rule.register.register("1st title", body = "1st body")
            rule.register.register("2nd title", Folder.titled("Folder"), body = "2nd body")
            rule.repository.getNotes().test { assertEquals(2, awaitItem().size) }
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
