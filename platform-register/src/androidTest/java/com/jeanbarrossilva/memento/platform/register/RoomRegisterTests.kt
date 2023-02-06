package com.jeanbarrossilva.memento.platform.register

import app.cash.turbine.test
import com.jeanbarrossilva.memento.platform.register.test.RegisterTestRule
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
            rule.repository.getNoteByID(noteID).test { assertNotNull(awaitItem()) }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun unregister() {
        runTest {
            val noteID = rule.register.register("A title", body = "A body")
            rule.register.unregister(noteID)
            rule.repository.getNoteByID(noteID).test { assertNull(awaitItem()) }
        }
    }
}
