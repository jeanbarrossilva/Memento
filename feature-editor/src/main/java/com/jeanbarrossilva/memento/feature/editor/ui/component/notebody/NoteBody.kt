package com.jeanbarrossilva.memento.feature.editor.ui.component.notebody

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.aurelius.component.editabletext.EditableText
import com.jeanbarrossilva.aurelius.component.editabletext.EditableTextDefaults
import com.jeanbarrossilva.aurelius.effect.keyboard.Keyboard
import com.jeanbarrossilva.aurelius.effect.keyboard.KeyboardEffect
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.`if`
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.memento.editor.R
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.ui.focusmode.FocusMode

@Composable
internal fun NoteBody(
    note: Note,
    focusMode: FocusMode,
    onChange: (body: String) -> Unit,
    lazyListState: LazyListState,
    isEditing: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var textFieldValue by remember { mutableStateOf(TextFieldValue(note.body)) }
    val onTextFieldValueChange: (TextFieldValue) -> Unit = remember {
        {
            textFieldValue = it
            onChange(textFieldValue.text)
        }
    }
    val focusRequester = remember(::FocusRequester)
    var bottomPadding by remember { mutableStateOf(0.dp) }
    val isNoteEmpty = remember { note.isEmpty(context) }

    focusMode.FocusEffect(
        coroutineScope,
        focusRequester,
        textFieldValue,
        onTextFieldValueChange,
        isNoteEmpty,
        isFocused = isEditing && !isNoteEmpty
    )

    KeyboardEffect { keyboard ->
        bottomPadding = when (keyboard) {
            is Keyboard.Idle, is Keyboard.Closed -> 0.dp
            is Keyboard.Open -> keyboard.height
        }
    }

    LazyColumn(
        modifier.background(note.colors.container.primary),
        lazyListState,
        contentPadding = PaddingValues(AureliusTheme.sizes.spacing.large) +
            AureliusTheme.sizes.margin.fab
    ) {
        items(1) {
            EditableText(
                textFieldValue,
                onTextFieldValueChange,
                isActive = isEditing,
                Modifier
                    .fillMaxWidth()
                    .`if`(note.body.isBlank(), Modifier::fillMaxHeight)
                    .padding(bottom = bottomPadding)
                    .focusRequester(focusRequester),
                colors = EditableTextDefaults.colors(
                    text = Color.Black.copy(AureliusTheme.visibility.medium),
                    label = Color.Black.copy(AureliusTheme.visibility.low),
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
        NoteBody(Note.sample.copy(body = ""))
    }
}

@Composable
@Preview
private fun NoteBodyPreview() {
    AureliusTheme {
        NoteBody(Note.sample)
    }
}
