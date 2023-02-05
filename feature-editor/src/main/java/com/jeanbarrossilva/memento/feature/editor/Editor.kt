package com.jeanbarrossilva.memento.feature.editor

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.Scaffold
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.isScrolling
import com.jeanbarrossilva.memento.feature.editor.domain.EditorMode
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.domain.colors.NoteColors
import com.jeanbarrossilva.memento.feature.editor.domain.isEditing
import com.jeanbarrossilva.memento.feature.editor.ui.dialog.DeletionConfirmationDialog
import com.jeanbarrossilva.memento.feature.editor.ui.focus.FocusModeFactory
import com.jeanbarrossilva.memento.feature.editor.ui.input.notebody.NoteBody
import com.jeanbarrossilva.memento.feature.editor.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.memento.feature.editor.ui.layout.scaffold.topappbar.TopAppBar

@Composable
internal fun Editor(
    viewModel: EditorViewModel,
    onNavigationRequest: () -> Unit,
    onDeletionRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val note by viewModel.getEditedNote().collectAsState()
    val mode by viewModel.getMode().collectAsState()

    Editor(
        mode,
        note,
        onNavigationRequest,
        onTitleChange = viewModel::setTitle,
        onDeletionRequest,
        onColorPickerVisibilityChange = viewModel::setColorPickerVisible,
        onColorsChange = viewModel::setColors,
        onBodyChange = viewModel::setBody,
        onEditRequest = viewModel::edit,
        onSaveRequest = viewModel::save,
        modifier
    )
}

@Composable
private fun Editor(
    editorMode: EditorMode,
    note: Note,
    onNavigationRequest: () -> Unit,
    onTitleChange: (title: String) -> Unit,
    onDeletionRequest: () -> Unit,
    onColorPickerVisibilityChange: (isColorPickerVisible: Boolean) -> Unit,
    onColorsChange: (colors: NoteColors) -> Unit,
    onBodyChange: (body: String) -> Unit,
    onEditRequest: () -> Unit,
    onSaveRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val isToolbarCompact = lazyListState.isScrolling
    val isEditing = editorMode.isEditing()
    val onFabClick = when (editorMode) {
        is EditorMode.Reading -> onEditRequest
        is EditorMode.Editing -> onSaveRequest
    }
    var isDeletionConfirmationDialogVisible by remember { mutableStateOf(false) }
    val focusMode = remember(note) { FocusModeFactory.create(lazyListState, note) }

    DisposableEffect(isToolbarCompact) {
        onColorPickerVisibilityChange(!isToolbarCompact)
        onDispose { }
    }

    if (isDeletionConfirmationDialogVisible) {
        DeletionConfirmationDialog(
            onConfirmationRequest = {
                onDeletionRequest()
                onNavigationRequest()
            },
            onDismissalRequest = { isDeletionConfirmationDialogVisible = false }
        )
    }

    TopAppBar(
        editorMode,
        note,
        focusMode,
        isToolbarCompact,
        onNavigationRequest,
        onTitleChange,
        onDeletionRequest = { isDeletionConfirmationDialogVisible = true },
        onColorsChange,
        modifier
    ) {
        Scaffold(
            floatingActionButton = { FloatingActionButton(note.colors, isEditing, onFabClick) }
        ) { padding ->
            Background(
                Modifier.padding(padding),
                color = note.colors.container.primary
            ) {
                CompositionLocalProvider(LocalContentColor provides note.colors.content) {
                    NoteBody(
                        note,
                        focusMode,
                        onBodyChange,
                        lazyListState,
                        isEditing
                    )
                }
            }
        }
    }
}

@Composable
private fun Editor(mode: EditorMode, modifier: Modifier = Modifier) {
    Editor(
        mode,
        Note.sample,
        onNavigationRequest = { },
        onTitleChange = { },
        onDeletionRequest = { },
        onColorPickerVisibilityChange = { },
        onColorsChange = { },
        onBodyChange = { },
        onEditRequest = { },
        onSaveRequest = { },
        modifier
    )
}

@Composable
@Preview
private fun ReadingEditorPreview() {
    AureliusTheme {
        Editor(EditorMode.Reading)
    }
}

@Composable
@Preview
private fun EditingEditorPreview() {
    AureliusTheme {
        Editor(EditorMode.Editing(isColorPickerVisible = true))
    }
}
