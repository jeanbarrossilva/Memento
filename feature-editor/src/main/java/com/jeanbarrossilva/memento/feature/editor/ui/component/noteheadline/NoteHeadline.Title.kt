package com.jeanbarrossilva.memento.feature.editor.ui.component.noteheadline

import android.content.res.Configuration
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.editabletext.EditableText
import com.jeanbarrossilva.aurelius.component.editabletext.EditableTextDefaults
import com.jeanbarrossilva.aurelius.layout.background.Background
import com.jeanbarrossilva.aurelius.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.memento.editor.R
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.ui.focusmode.FocusMode
import com.jeanbarrossilva.memento.feature.editor.utils.without

@Composable
internal fun Title(
    note: Note,
    focusMode: FocusMode,
    isEditing: Boolean,
    onChange: (title: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var textFieldValue by remember { mutableStateOf(TextFieldValue(note.title)) }
    val onTextFieldValueChange: (TextFieldValue) -> Unit = remember {
        {
            textFieldValue = it.copy(it.text without "\n")
            onChange(textFieldValue.text)
        }
    }
    val focusRequester = remember(::FocusRequester)
    val isNoteEmpty = remember { note.isEmpty(context) }

    focusMode.FocusEffect(
        coroutineScope,
        focusRequester,
        textFieldValue,
        onTextFieldValueChange,
        isNoteEmpty,
        isFocused = isEditing && isNoteEmpty
    )

    EditableText(
        textFieldValue,
        onTextFieldValueChange,
        isActive = isEditing,
        modifier.focusRequester(focusRequester),
        ImeAction.Next,
        EditableTextDefaults.colors(text = note.colors.content)
    ) {
        Text(stringResource(R.string.feature_editor_title))
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TitlePreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            Title(Note.sample, FocusMode.Default, isEditing = false, onChange = { })
        }
    }
}
