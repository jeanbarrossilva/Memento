package com.jeanbarrossilva.memento.feature.notes

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
        onDeleteRequest = { },
        onAddRequest = { boundary.navigateToEditor(context, noteID = null) },
        modifier
    )
}

@Composable
private fun Notes(notes: Loadable<SerializableList<Note>>, modifier: Modifier = Modifier) {
    Notes(
        currentFolder = Folder.sample,
        onCurrentFolderChange = { },
        notes,
        onNoteClick = { },
        Selection.Off,
        onSelectionToggle = { },
        onSearchRequest = { },
        onDeleteRequest = { },
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
    onDeleteRequest: (notes: List<Note>) -> Unit,
    onAddRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

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
            onDeleteRequest
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
