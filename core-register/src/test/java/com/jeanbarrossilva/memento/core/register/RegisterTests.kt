package com.jeanbarrossilva.memento.core.register

import app.cash.turbine.test
import com.jeanbarrossilva.memento.core.register.repository.TestRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class RegisterTests {
    private val repository = TestRepository()
    private val register = TestRegister(repository)

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
                body = "'You don't have to turn it into something.'"
            )
            repository.getNoteByID(noteID).test { assertNotNull(awaitItem()) }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a note WHEN unregistering it THEN it's unregistered`() {
        runTest {
            val noteID = register.register(title = "Seneca", body = "'He who is brave is free.'")
            register.unregister(noteID)
            repository.getNoteByID(noteID).test { assertNull(awaitItem()) }
        }
    }
}