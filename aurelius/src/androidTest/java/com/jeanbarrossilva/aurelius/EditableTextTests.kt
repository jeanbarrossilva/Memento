package com.jeanbarrossilva.aurelius

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextInputSelection
import androidx.compose.ui.text.TextRange
import com.jeanbarrossilva.aurelius.ui.system.keyboard.Keyboard
import com.jeanbarrossilva.aurelius.ui.system.keyboard.KeyboardEffect
import com.jeanbarrossilva.aurelius.ui.input.editabletext.EDITABLE_TEXT_TAG
import com.jeanbarrossilva.aurelius.ui.input.editabletext.EditableText
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

internal class EditableTextTests {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun opensKeyboardWhenActive() {
        var keyboard: Keyboard = Keyboard.Idle.instance
        composeRule.setContent {
            KeyboardEffect {
                keyboard = it
            }

            EditableText(isActive = true)
        }
        composeRule.onNodeWithTag(EDITABLE_TEXT_TAG).performClick()
        composeRule.waitUntil(timeoutMillis = 2_000) { keyboard is Keyboard.Open }
        assertEquals(Keyboard.Open::class, keyboard::class)
    }

    @Test
    fun doesNotOpenKeyboardWhenInactive() {
        var keyboard: Keyboard = Keyboard.Idle.instance
        composeRule.setContent {
            KeyboardEffect {
                keyboard = it
            }

            EditableText(isActive = false)
        }
        composeRule.onNodeWithTag(EDITABLE_TEXT_TAG).performClick()
        assertEquals(Keyboard.Closed.instance, keyboard)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test(expected = IllegalStateException::class)
    fun doesNotSelectTextWhenInactive() {
        composeRule.setContent { EditableText(isActive = false, text = "Hello...?") }
        composeRule.onNodeWithTag(EDITABLE_TEXT_TAG).performTextInputSelection(TextRange(0, 2))
    }

    @Test
    fun editsTextWhenActive() {
        composeRule.setContent {
            var text by remember { mutableStateOf("") }
            EditableText(isActive = true, text = text, onTextChange = { text = it })
        }
        composeRule.onNodeWithTag(EDITABLE_TEXT_TAG).performTextInput("Editing!")
        composeRule.onNodeWithTag(EDITABLE_TEXT_TAG).assertTextEquals("Editing!")
    }
}
