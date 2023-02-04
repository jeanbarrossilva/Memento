package com.jeanbarrossilva.memento.core.register.folder

import com.jeanbarrossilva.memento.core.register.domain.Folder
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FolderTests {
    @Test
    fun `GIVEN a title WHEN creating a folder with it THEN its path is the encoded title`() {
        val folder = Folder.titled("Administração fiduciária")
        assertEquals("Administra%C3%A7%C3%A3o+fiduci%C3%A1ria", folder.path)
    }

    @Test
    fun `GIVEN a path WHEN creating a folder with it THEN its title is the decoded path`() {
        val folder = Folder.at("Administra%C3%A7%C3%A3o+fiduci%C3%A1ria")
        assertEquals("Administração fiduciária", folder.title)
    }

    @Test
    fun `GIVEN a nested path WHEN creating a folder with it and getting its children THEN they're paths are relative`() {
        val root = Folder.at("1/2/3")
        val first = root.child
        val second = first?.child
        assertEquals("2/3", first?.path)
        assertEquals("3", second?.path)
    }
}
