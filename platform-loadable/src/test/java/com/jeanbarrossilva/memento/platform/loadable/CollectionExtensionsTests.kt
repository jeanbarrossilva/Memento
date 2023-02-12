package com.jeanbarrossilva.memento.platform.loadable

import com.jeanbarrossilva.memento.platform.loadable.utils.serializableListOf
import com.jeanbarrossilva.memento.platform.loadable.utils.serialize
import org.junit.Assert.assertEquals
import org.junit.Test

internal class CollectionExtensionsTests {
    @Test
    fun `GIVEN a Collection WHEN serializing it THEN it's a SerializableList with all of its previous elements`() {
        assertEquals(serializableListOf(1, 2, 3), listOf(1, 2, 3).serialize())
    }
}
