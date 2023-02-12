package com.jeanbarrossilva.memento.base.extensions.test

import kotlin.test.Test
import kotlin.test.assertFailsWith

internal class AssertExtensionsTests {
    @Test
    fun `GIVEN an object WHEN asserting its type against one that it's equal to THEN ut succeeds`() {
        assertTypeEquals<String>("Hello, world!")
    }

    @Test
    fun `GIVEN an object WHEN asserting its type against a different one THEN it fails`() {
        assertFailsWith<AssertionError> {
            assertTypeEquals<Int>(0.0)
        }
    }
}
