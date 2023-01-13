package com.jeanbarrossilva.memento.feature.notes

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.scaffold.Scaffold
import com.jeanbarrossilva.aurelius.layout.background.Background
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.isScrolling
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.memento.feature.notes.component.NoteCard
import com.jeanbarrossilva.memento.feature.notes.component.scaffold.FloatingActionButton
import com.jeanbarrossilva.memento.feature.notes.component.scaffold.menudrawer.MenuDrawer
import com.jeanbarrossilva.memento.feature.notes.component.scaffold.topappbar.TopAppBar
import com.jeanbarrossilva.memento.feature.notes.domain.Selection
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import kotlinx.coroutines.launch

@Composable
internal fun Notes(viewModel: NotesViewModel, modifier: Modifier = Modifier) {
    val folders by viewModel.getFolders().collectAsState()
    val defaultFolder by viewModel.getDefaultFolder().collectAsState()
    val currentFolder by viewModel.currentFolder.collectAsState()
    val notes by viewModel.getNotes().collectAsState()
    val selection by viewModel.selection.collectAsState()

    Notes(
        folders,
        defaultFolder,
        currentFolder,
        onCurrentFolderChange = { viewModel.currentFolder.value = it },
        notes,
        selection,
        onSelectionToggle = { viewModel.selection.value = it },
        onSearchRequest = { },
        onDeleteRequest = { },
        onAddRequest = { },
        modifier
    )
}

@Composable
private fun Notes(
    folders: List<NoteFolder>,
    defaultFolder: NoteFolder,
    currentFolder: NoteFolder?,
    onCurrentFolderChange: (folder: NoteFolder) -> Unit,
    notes: List<Note>,
    selection: Selection,
    onSelectionToggle: (Selection) -> Unit,
    onSearchRequest: () -> Unit,
    onDeleteRequest: (notes: List<Note>) -> Unit,
    onAddRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    MenuDrawer(folders, defaultFolder, currentFolder, onCurrentFolderChange, modifier) {
        TopAppBar(
            isCompact = lazyListState.isScrolling,
            currentFolder,
            notes.size,
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
                    LazyColumn(
                        state = lazyListState,
                        verticalArrangement = Arrangement.spacedBy(
                            AureliusTheme.sizes.spacing.medium
                        ),
                        contentPadding = PaddingValues(AureliusTheme.sizes.spacing.large) +
                            AureliusTheme.sizes.margin.fab
                    ) {
                        items(notes) { note ->
                            NoteCard(
                                note,
                                selection,
                                onSelectionToggle,
                                onClick = { },
                                Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NotesPreview() {
    AureliusTheme {
        Notes(
            NoteFolder.samples,
            defaultFolder = NoteFolder.sample,
            currentFolder = NoteFolder.sample,
            onCurrentFolderChange = { },
            Note.samples,
            Selection.Off,
            onSelectionToggle = { },
            onSearchRequest = { },
            onDeleteRequest = { },
            onAddRequest = { }
        )
    }
}
