package com.jeanbarrossilva.memento.feature.editor

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.scaffold.Scaffold
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.isScrolling
import com.jeanbarrossilva.memento.feature.editor.domain.Note
import com.jeanbarrossilva.memento.feature.editor.ui.DeletionConfirmationDialog
import com.jeanbarrossilva.memento.feature.editor.ui.component.notebody.NoteBody
import com.jeanbarrossilva.memento.feature.editor.ui.component.scaffold.FloatingActionButton
import com.jeanbarrossilva.memento.feature.editor.ui.component.scaffold.topappbar.TopAppBar
import com.jeanbarrossilva.memento.feature.editor.ui.focusmode.FocusModeFactory

@Composable
internal fun Editor(
    viewModel: EditorViewModel,
    onNavigationRequest: () -> Unit,
    onDeletionRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val note by viewModel.getEditedNote().collectAsState()
    val isEditing by viewModel.isEditing.collectAsState()

    Editor(
        note,
        isEditing,
        onNavigationRequest,
        onTitleChange = viewModel::setTitle,
        onDeletionRequest,
        onBodyChange = viewModel::setBody,
        onEditRequest = { viewModel.isEditing.value = true },
        onSaveRequest = viewModel::save,
        modifier
    )
}

@Composable
private fun Editor(
    note: Note,
    isEditing: Boolean,
    onNavigationRequest: () -> Unit,
    onTitleChange: (title: String) -> Unit,
    onDeletionRequest: () -> Unit,
    onBodyChange: (body: String) -> Unit,
    onEditRequest: () -> Unit,
    onSaveRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val onFabClick = if (isEditing) onSaveRequest else onEditRequest
    var isDeletionConfirmationDialogVisible by remember { mutableStateOf(false) }
    val focusMode = remember(note) { FocusModeFactory.create(context, lazyListState, note) }

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
        note,
        focusMode,
        isCompact = lazyListState.isScrolling,
        isEditing,
        onNavigationRequest,
        onTitleChange,
        onDeletionRequest = { isDeletionConfirmationDialogVisible = true },
        modifier
    ) {
        Scaffold(
            floatingActionButton = { FloatingActionButton(note.colors, isEditing, onFabClick) }
        ) { padding ->
            NoteBody(
                note,
                focusMode,
                onBodyChange,
                lazyListState,
                isEditing,
                Modifier.padding(padding)
            )
        }
    }
}

@Composable
private fun Editor(isEditing: Boolean, modifier: Modifier = Modifier) {
    Editor(
        Note.sample,
        isEditing,
        onNavigationRequest = { },
        onTitleChange = { },
        onDeletionRequest = { },
        onBodyChange = { },
        onEditRequest = { },
        onSaveRequest = { },
        modifier
    )
}

@Composable
@Preview
private fun NonEditingEditorPreview() {
    AureliusTheme {
        Editor(isEditing = false)
    }
}

@Composable
@Preview
private fun EditingEditorPreview() {
    AureliusTheme {
        Editor(isEditing = true)
    }
}
