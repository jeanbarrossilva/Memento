package com.jeanbarrossilva.aurelius.ui.input.editabletext

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

/** Tag that identifies the [EditableText] for testing purposes. **/
internal const val EDITABLE_TEXT_TAG = "editable_text"

/**
 * Text that has field-like behavior whenever it's [active][isActive].
 *
 * @param text Primary text that's displayed.
 * @param onTextChange Callback run whenever the text is changed.
 * @param isActive Whether or not it can be edited. If so, it acts as a text field and allows the
 * user to edit the content; otherwise, it's just a normal, uneditable text.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 * @param imeAction [ImeAction] to be used for the keyboard.
 * @param colors Colors of the overall content.
 * @param label Secondary text shown whenever the primary [text] is empty.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableText(
    text: String,
    onTextChange: (text: String) -> Unit,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Default,
    colors: EditableTextColors = EditableTextDefaults.colors(),
    label: (@Composable () -> Unit)? = null
) {
    val textStyle = LocalTextStyle.current.copy(colors.text)
    val isSingleLined = false
    val visualTransformation = VisualTransformation.None
    val interactionSource = remember(::MutableInteractionSource)
    val cursorBrush = remember(colors) { SolidColor(colors.cursor) }

    BasicTextField(
        text,
        onTextChange,
        modifier,
        enabled = isActive,
        textStyle = textStyle,
        keyboardOptions = remember(imeAction) { KeyboardOptions(imeAction = imeAction) },
        singleLine = isSingleLined,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            text,
            innerTextField = it,
            enabled = isActive,
            isSingleLined,
            visualTransformation,
            interactionSource,
            placeholder = label?.let {
                {
                    ProvideTextStyle(textStyle.copy(LocalContentColor.current), it)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                colors.text,
                disabledIndicatorColor = Color.Transparent,
                placeholderColor = colors.label
            ),
            contentPadding = PaddingValues(0.dp)
        )
    }
}

/**
 * Text that has field-like behavior whenever it's [active][isActive].
 *
 * @param value [TextFieldValue] containing the primary text that's displayed.
 * @param onValueChange Callback run whenever the [TextFieldValue] is changed.
 * @param isActive Whether or not it can be edited. If so, it acts as a text field and allows the
 * user to edit the content; otherwise, it's just a normal, uneditable text.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 * @param imeAction [ImeAction] to be used for the keyboard.
 * @param colors Colors of the overall content.
 * @param label Secondary text shown whenever the primary text is empty.
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableText(
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Default,
    colors: EditableTextColors = EditableTextDefaults.colors(),
    label: (@Composable () -> Unit)? = null
) {
    val textStyle = LocalTextStyle.current.copy(colors.text)
    val isSingleLined = false
    val visualTransformation = VisualTransformation.None
    val interactionSource = remember(::MutableInteractionSource)
    val cursorBrush = remember(colors) { SolidColor(colors.cursor) }

    BasicTextField(
        value,
        onValueChange,
        modifier,
        enabled = isActive,
        textStyle = textStyle,
        keyboardOptions = remember(imeAction) { KeyboardOptions(imeAction = imeAction) },
        singleLine = isSingleLined,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value.text,
            innerTextField = it,
            enabled = isActive,
            isSingleLined,
            visualTransformation,
            interactionSource,
            placeholder = label?.let {
                {
                    ProvideTextStyle(textStyle.copy(LocalContentColor.current), it)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                colors.text,
                disabledIndicatorColor = Color.Transparent,
                placeholderColor = colors.label
            ),
            contentPadding = PaddingValues(0.dp)
        )
    }
}

/**
 * Text that has field-like behavior whenever it's [active][isActive].
 *
 * @param isActive Whether or not it can be edited. If so, it acts as a text field and allows the
 * user to edit the content; otherwise, it's just a normal, uneditable text.
 * @param modifier [Modifier] to be applied to the underlying [Composable].
 * @param text Primary text that's displayed.
 * @param onTextChange Callback run whenever the text is changed.
 **/
@Composable
internal fun EditableText(
    modifier: Modifier = Modifier,
    text: String = "Text",
    onTextChange: (text: String) -> Unit = { },
    isActive: Boolean = true,
    colors: EditableTextColors = EditableTextDefaults.colors()
) {
    EditableText(
        text,
        onTextChange,
        isActive,
        modifier.testTag(EDITABLE_TEXT_TAG),
        colors = colors
    ) {
        Text("Label")
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun InactiveEmptyEditableTextPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            EditableText(text = "", isActive = false)
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun InactiveEditableTextPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            EditableText(isActive = false)
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ActiveEditableTextPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            EditableText()
        }
    }
}

@Composable
@Preview
private fun ColoredEditableTextPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            EditableText(colors = EditableTextDefaults.colors(text = Color.Blue))
        }
    }
}
