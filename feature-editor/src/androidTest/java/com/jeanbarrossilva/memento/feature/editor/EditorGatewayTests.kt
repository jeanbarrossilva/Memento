package com.jeanbarrossilva.memento.feature.editor

import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.test.EditorGatewayTestRule
import com.jeanbarrossilva.memento.feature.editor.utils.getLastNote
import com.jeanbarrossilva.memento.platform.register.test.RegisterTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

internal class EditorGatewayTests {
    private val registerRule = RegisterTestRule()
    private val gatewayRule = EditorGatewayTestRule(registerRule)

    @get:Rule
    val ruleChain: RuleChain? = RuleChain.outerRule(registerRule).around(gatewayRule)

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun addNewNote() {
        runTest {
            gatewayRule.gateway.save(noteID = null, "Title", "Body", NoteColors.YELLOW)
            assertEquals("Title", getNote()?.title)
            assertEquals("Body", getNote()?.body)
            assertEquals(Color.YELLOW, getNote()?.color)
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun editExistingNote() {
        runTest {
            val noteID = registerRule.register.register("Title", body = "Body", color = Color.BLUE)
            gatewayRule.gateway.save(noteID, "New title", "New body", NoteColors.PURPLE)
            assertEquals("New title", getNote()?.title)
            assertEquals("New body", getNote()?.body)
            assertEquals(Color.PURPLE, getNote()?.color)
        }
    }

    private suspend fun getNote(): Note? {
        return registerRule.repository.getLastNote()
    }
}
