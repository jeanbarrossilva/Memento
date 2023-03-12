package com.jeanbarrossilva.memento.feature.notes

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.Scaffold
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.isScrolling
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.loadable.utils.ifLoaded
import com.jeanbarrossilva.loadable.utils.serialize
import com.jeanbarrossilva.memento.feature.notes.domain.Selection
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.ui.layout.dialog.DeletionConfirmationDialog
import com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold.menudrawer.MenuDrawer
import com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold.topappbar.TopAppBar
import kotlinx.coroutines.launch

@Composable
internal fun Notes(
    viewModel: NotesViewModel,
    boundary: NotesBoundary,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentFolder by viewModel.currentFolder.collectAsState()
    val notes by viewModel.getNotes().collectAsState()
    val selection by viewModel.selection.collectAsState()

    Notes(
        currentFolder,
        onCurrentFolderChange = { viewModel.currentFolder.value = it },
        notes,
        onNoteClick = { boundary.navigateToEditor(context, it.id) },
        selection,
        onSelectionToggle = { viewModel.selection.value = it },
        onSearchRequest = { },
        onDeletionRequest = viewModel::delete,
        onAddRequest = { boundary.navigateToEditor(context, noteID = null) },
        modifier
    )
}

@Composable
internal fun Notes(notes: Loadable<SerializableList<Note>>, modifier: Modifier = Modifier) {
    Notes(
        currentFolder = Folder.sample,
        onCurrentFolderChange = { },
        notes,
        onNoteClick = { },
        Selection.Off,
        onSelectionToggle = { },
        onSearchRequest = { },
        onDeletionRequest = { },
        onAddRequest = { },
        modifier
    )
}

@Composable
private fun Notes(
    currentFolder: Folder,
    onCurrentFolderChange: (currentFolder: Folder) -> Unit,
    notes: Loadable<SerializableList<Note>>,
    onNoteClick: (Note) -> Unit,
    selection: Selection,
    onSelectionToggle: (Selection) -> Unit,
    onSearchRequest: () -> Unit,
    onDeletionRequest: (notes: List<Note>) -> Unit,
    onAddRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    var isDeletionConfirmationDialogVisible by remember { mutableStateOf(false) }

    if (isDeletionConfirmationDialogVisible) {
        DeletionConfirmationDialog(
            onConfirmationRequest = { onDeletionRequest((selection as Selection.On).selected) },
            onDismissalRequest = { isDeletionConfirmationDialogVisible = false }
        )
    }

    MenuDrawer(notes, currentFolder, onCurrentFolderChange, modifier) {
        TopAppBar(
            isCompact = lazyListState.isScrolling,
            currentFolder,
            noteCount = notes.ifLoaded { size } ?: 0,
            selection,
            onNavigationRequest = {
                coroutineScope.launch {
                    open()
                }
            },
            onSearchRequest,
            onDeleteRequest = {
                isDeletionConfirmationDialogVisible = true
                onSelectionToggle(Selection.Off)
            }
        ) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        isVisible = selection is Selection.Off,
                        onClick = onAddRequest
                    )
                }
            ) { padding ->
                Background(Modifier.padding(padding)) {
                    LoadableNotes(lazyListState, notes, selection, onSelectionToggle, onNoteClick)
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingNotesPreview() {
    AureliusTheme {
        Notes(Loadable.Loading())
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedNotesPreview() {
    AureliusTheme {
        Notes(Loadable.Loaded(Note.samples.serialize()))
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FailedNotesPreview() {
    AureliusTheme {
        Notes(Loadable.Failed(Throwable()))
    }
}
