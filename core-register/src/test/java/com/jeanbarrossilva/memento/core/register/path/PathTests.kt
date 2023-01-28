package com.jeanbarrossilva.memento.core.register.path

import com.jeanbarrossilva.memento.core.register.domain.Path
import kotlin.test.Test
import kotlin.test.assertEquals
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

    @Test
    fun `GIVEN an non-encoded value WHEN creating a path with it THEN it's encoded`() {
        val path = Path to "/Administração fiduciária"
        assertEquals("/Administra%C3%A7%C3%A3o+fiduci%C3%A1ria", path.value)
    }

    @Test
    fun `GIVEN a path WHEN getting its name THEN it's the decoded value`() {
        val path = Path to "/Rio de Janeiro + São Paulo"
        assertEquals("Rio de Janeiro + São Paulo", path.name)
    }
}
