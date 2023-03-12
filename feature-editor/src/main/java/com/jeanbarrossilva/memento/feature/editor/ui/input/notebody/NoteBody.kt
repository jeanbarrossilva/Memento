package com.jeanbarrossilva.memento.feature.editor.ui.input.notebody

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.ui.input.editabletext.EditableText
import com.jeanbarrossilva.aurelius.ui.input.editabletext.EditableTextDefaults
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.system.keyboard.Keyboard
import com.jeanbarrossilva.aurelius.ui.system.keyboard.KeyboardEffect
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.memento.feature.editor.R
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.ui.focus.FocusMode
import com.jeanbarrossilva.memento.platform.extensions.`if`

const val NOTE_BODY_TAG = "note_body"

@Composable
internal fun NoteBody(
    note: Note,
    focusMode: FocusMode,
    onChange: (body: String) -> Unit,
    lazyListState: LazyListState,
    isEditing: Boolean,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var textFieldValue by remember(note) { mutableStateOf(TextFieldValue(note.body)) }
    val onTextFieldValueChange: (TextFieldValue) -> Unit = remember {
        {
            textFieldValue = it
            onChange(textFieldValue.text)
        }
    }
    val focusRequester = remember(::FocusRequester)
    var bottomPadding by remember { mutableStateOf(PaddingValues(0.dp)) }
    val contentColor = remember(note) { note.colors.content }

    focusMode.FocusEffect(
        coroutineScope,
        focusRequester,
        textFieldValue,
        onTextFieldValueChange,
        note.isEmpty,
        isFocused = isEditing && !note.isEmpty
    )

    KeyboardEffect { keyboard ->
        bottomPadding = when (keyboard) {
            is Keyboard.Idle, is Keyboard.Closed -> PaddingValues(0.dp)
            is Keyboard.Open -> PaddingValues(bottom = keyboard.height)
        }
    }

    LazyColumn(
        modifier,
        lazyListState,
        contentPadding = PaddingValues(AureliusTheme.sizes.spacing.large) +
            bottomPadding +
            AureliusTheme.sizes.margin.fab
    ) {
        items(1) {
            EditableText(
                textFieldValue,
                onTextFieldValueChange,
                isActive = isEditing,
                Modifier
                    .testTag(NOTE_BODY_TAG)
                    .fillMaxWidth()
                    .`if`(note.body.isBlank(), Modifier::fillMaxHeight)
                    .focusRequester(focusRequester),
                colors = EditableTextDefaults.colors(
                    text = contentColor.copy(AureliusTheme.visibility.medium),
                    label = contentColor.copy(AureliusTheme.visibility.low),
                    cursor = note.colors.container.secondary
                )
            ) {
                Text(stringResource(R.string.feature_editor_note_label))
            }
        }
    }
}

@Composable
private fun NoteBody(note: Note, modifier: Modifier = Modifier) {
    NoteBody(
        note,
        FocusMode.Default,
        onChange = { },
        rememberLazyListState(),
        isEditing = false,
        modifier
    )
}

@Composable
@Preview
private fun EmptyNoteBodyPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            NoteBody(Note.sample.copy(body = ""))
        }
    }
}

@Composable
@Preview
private fun NoteBodyPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            NoteBody(Note.sample)
        }
    }
}
