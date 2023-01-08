package com.jeanbarrossilva.memento.notes

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
import com.jeanbarrossilva.memento.notes.component.NoteCard
import com.jeanbarrossilva.memento.notes.component.scaffold.FloatingActionButton
import com.jeanbarrossilva.memento.notes.component.scaffold.MenuDrawer
import com.jeanbarrossilva.memento.notes.component.scaffold.topappbar.TopAppBar
import com.jeanbarrossilva.memento.notes.domain.Selection
import com.jeanbarrossilva.memento.notes.domain.note.Note
import com.jeanbarrossilva.memento.ui.component.scaffold.Scaffold
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.theme.MementoTheme
import com.jeanbarrossilva.memento.ui.utils.isScrolling
import com.jeanbarrossilva.memento.ui.utils.plus
import kotlinx.coroutines.launch

@Composable
internal fun Notes(viewModel: NotesViewModel, modifier: Modifier = Modifier) {
    val notes by viewModel.notes.collectAsState()
    val selection by viewModel.selection.collectAsState()

    Notes(
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

    MenuDrawer(modifier) {
        TopAppBar(
            isCompact = lazyListState.isScrolling,
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
                            MementoTheme.sizes.spacing.medium
                        ),
                        contentPadding = PaddingValues(MementoTheme.sizes.spacing.large) +
                            MementoTheme.sizes.margin.fab
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
    MementoTheme {
        Notes(
            Note.samples,
            Selection.Off,
            onSelectionToggle = { },
            onSearchRequest = { },
            onDeleteRequest = { },
            onAddRequest = { }
        )
    }
}
