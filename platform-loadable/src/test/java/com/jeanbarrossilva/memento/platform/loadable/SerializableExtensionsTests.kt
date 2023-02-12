package com.jeanbarrossilva.memento.platform.loadable

import com.jeanbarrossilva.memento.base.extensions.test.assertTypeEquals
import com.jeanbarrossilva.memento.platform.loadable.utils.toLoadable
import java.io.Serializable
import org.junit.Assert.assertEquals
import org.junit.Test

internal class SerializableExtensionsTests {
    @Test
    fun `GIVEN a null Serializable WHEN converting it into a Loadable THEN it's a Loading one`() {
        assertTypeEquals<Loadable.Loading<Int>>((null as Serializable?).toLoadable())
    }

    @Test
    fun `GIVEN a non-null Serializable WHEN converting it into a Loadable THEN it's a Loaded one`() {
        assertEquals(Loadable.Loaded(0), 0.toLoadable())
    }
}
