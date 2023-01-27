package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.platform.register.rule.RegisterTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

internal class RoomRegisterTests {
    @get:Rule
    val rule = RegisterTestRule()

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun register() {
        runTest {
            val noteID = rule.register.register("Title", body = "Body")
            assertNotNull(rule.repository.getNoteByID(noteID))
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun unregister() {
        runTest {
            val noteID = rule.register.register("A title", body = "A body")
            rule.register.unregister(noteID)
            assertNull(rule.repository.getNoteByID(noteID))
        }
    }
}
