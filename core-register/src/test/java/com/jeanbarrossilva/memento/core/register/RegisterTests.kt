package com.jeanbarrossilva.memento.core.register

import com.jeanbarrossilva.memento.core.register.domain.Color
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class RegisterTests {
    private val register = TestRegister()

    @AfterTest
    @OptIn(ExperimentalCoroutinesApi::class)
    fun tearDown() {
        runTest {
            register.unregisterAll()
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a note WHEN registering it THEN it's registered`() {
        runTest {
            val noteID = register.register(
                title = "Marcus Aurelius",
                body = "'You don't have to turn it into something.'",
                Color.BLUE
            )
            assertNotNull(register.getNoteByID(noteID))
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a note WHEN unregistering it THEN it's unregistered`() {
        runTest {
            val noteID = register.register(
                title = "Seneca",
                body = "'He who is brave is free.'",
                Color.PURPLE
            )
            register.unregister(noteID)
            assertNull(register.getNoteByID(noteID))
        }
    }
}