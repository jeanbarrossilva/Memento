package com.jeanbarrossilva.memento.core.register.path

import com.jeanbarrossilva.memento.core.register.domain.Path
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class PathTests {
    @Test
    fun `GIVEN a non-UTF-8-encoded value WHEN creating a path THEN it throws`() {
        assertFailsWith<AssertionError> {
            Path("Administração fiduciária")
        }
    }

    @Test
    fun `GIVEN a blank value WHEN creating a path with it THEN it throws`() {
        assertFailsWith<AssertionError> {
            Path to ""
        }
    }

    @Test
    fun `GIVEN a value that doesn't start with the separator WHEN creating a path with it THEN it throws`() {
        assertFailsWith<AssertionError> {
            Path to "${Path.SEPARATOR.code.plus(1).toChar()}"
        }
    }

    @Test
    fun `GIVEN a root path WHEN checking if it's root THEN it is`() {
        assertTrue(Path.root.isRoot)
    }

    @Test
    fun `GIVEN a non-root path WHEN checking if it's root THEN it isn't`() {
        val path = Path to "/content"
        assertFalse(path.isRoot)
    }
}
