package com.jeanbarrossilva.platform.extensions.viewmodel

import app.cash.turbine.test
import com.jeanbarrossilva.memento.platform.extensions.flowOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class ViewModelExtensionsTests {
    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a Flow with an origin WHEN collecting it THEN the initial value is emitted first`() {
        runTest {
            EmptyViewModel().flowOf(0) { flowOf(1) }.test {
                assertEquals(0, awaitItem())
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a Flow with an origin WHEN collecting it THEN origin's emissions are transferred to it after the initial value`() {
        runTest {
            EmptyViewModel().flowOf(6) { flowOf(3) }.test {
                awaitItem()
                assertEquals(3, awaitItem())
            }
        }
    }
}