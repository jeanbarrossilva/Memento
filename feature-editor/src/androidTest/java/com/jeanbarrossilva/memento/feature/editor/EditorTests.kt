package com.jeanbarrossilva.memento.feature.editor

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FLOATING_ACTION_BUTTON_TAG
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.core.register.domain.Color
import com.jeanbarrossilva.memento.core.register.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.test.EditorGatewayTestRule
import com.jeanbarrossilva.memento.feature.editor.ui.input.notebody.NOTE_BODY_TAG
import com.jeanbarrossilva.memento.feature.editor.ui.input.noteheadline.NOTE_HEADLINE_TITLE_TAG
import com.jeanbarrossilva.memento.feature.editor.ui.input.noteheadline.colors.noteHeadlineColorBubbleTagFor
import com.jeanbarrossilva.memento.feature.editor.utils.getLastNote
import com.jeanbarrossilva.memento.platform.register.test.RegisterTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

internal class EditorTests {
    private val registerRule = RegisterTestRule()
    private val gatewayRule = EditorGatewayTestRule(registerRule)
    private val composeRule = createComposeRule()

    @get:Rule
    val ruleChain: RuleChain? =
        RuleChain.outerRule(registerRule).around(gatewayRule).around(composeRule)

    @Before
    fun setUp() {
        val viewModel = EditorViewModel(gatewayRule.gateway, noteID = null)
        composeRule.setContent {
            AureliusTheme {
                Editor(viewModel, onNavigationRequest = { }, onDeletionRequest = { })
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setNoteTitle() {
        composeRule.onNodeWithTag(NOTE_HEADLINE_TITLE_TAG).performTextInput("Title")
        composeRule.onNodeWithTag(FLOATING_ACTION_BUTTON_TAG).performClick()
        runTest { assertEquals("Title", getNote()?.title) }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setNoteBody() {
        composeRule.onNodeWithTag(NOTE_BODY_TAG).performTextInput("Body")
        composeRule.onNodeWithTag(FLOATING_ACTION_BUTTON_TAG).performClick()
        runTest { assertEquals("Body", getNote()?.body) }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setNoteColor() {
        composeRule.onNodeWithTag(noteHeadlineColorBubbleTagFor(NoteColors.YELLOW)).performClick()
        composeRule.onNodeWithTag(FLOATING_ACTION_BUTTON_TAG).performClick()
        runTest { assertEquals(Color.YELLOW, getNote()?.color) }
    }

    private suspend fun getNote(): Note? {
        return registerRule.repository.getLastNote()
    }
}
