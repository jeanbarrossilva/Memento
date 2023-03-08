package com.jeanbarrossilva.memento.feature.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.aurelius.utils.plus
import com.jeanbarrossilva.memento.feature.notes.domain.Selection
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.ui.actionable.NoteCard

internal const val LOADED_NOTES_TAG = "loaded_notes"

@Composable
internal fun LoadedNotes(
    state: LazyListState,
    notes: List<Note>,
    selection: Selection,
    onSelectionToggle: (Selection) -> Unit,
    onNoteClick: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier.testTag(LOADED_NOTES_TAG),
        state,
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
                onClick = { onNoteClick(note) },
                Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
private fun LoadedNotesPreview() {
    AureliusTheme {
        LoadedNotes(
            rememberLazyListState(),
            Note.samples,
            Selection.Off,
            onSelectionToggle = { },
            onNoteClick = { }
        )
    }
}
