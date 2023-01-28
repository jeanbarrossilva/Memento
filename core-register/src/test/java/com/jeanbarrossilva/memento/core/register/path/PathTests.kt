package com.jeanbarrossilva.memento.core.register.path

import com.jeanbarrossilva.memento.core.register.domain.Path
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class PathTests {
    @Test
    fun `GIVEN a root path WHEN checking if it's root THEN it is`() {
        assertTrue(Path.root.isRoot)
    }

    @Test
    fun `GIVEN a non-root path WHEN checking if it's root THEN it isn't`() {
        val path = Path.createInstance("content")
        assertFalse(path.isRoot)
    }

    @Test
    fun `GIVEN an non-encoded value WHEN creating a path with it THEN it's encoded`() {
        val path = Path.createInstance("Administração fiduciária")
        assertEquals("/Administra%C3%A7%C3%A3o+fiduci%C3%A1ria", path.value)
    }

    @Test
    fun `GIVEN a path WHEN getting its name THEN it's the decoded value`() {
        val path = Path.createInstance("Rio de Janeiro + São Paulo")
        assertEquals("Rio de Janeiro + São Paulo", path.name)
    }
}
