package com.jeanbarrossilva.memento.feature.notes

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.loadable.utils.serialize
import com.jeanbarrossilva.memento.feature.notes.domain.Selection
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note

@Composable
internal fun LoadableNotes(
    state: LazyListState,
    notes: Loadable<SerializableList<Note>>,
    selection: Selection,
    onSelectionToggle: (Selection) -> Unit,
    onNoteClick: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    when (notes) {
        is Loadable.Loading -> LoadingNotes()
        is Loadable.Loaded ->
            LoadedNotes(state, notes.value, selection, onSelectionToggle, onNoteClick, modifier)
        is Loadable.Failed -> FailedNotes()
    }
}

@Composable
internal fun LoadableNotes(notes: Loadable<SerializableList<Note>>, modifier: Modifier = Modifier) {
    LoadableNotes(
        rememberLazyListState(),
        notes,
        Selection.Off,
        onSelectionToggle = { },
        onNoteClick = { },
        modifier
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingLoadableNotesPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            LoadableNotes(Loadable.Loading())
        }
    }
}

@Composable
@Preview
private fun LoadedLoadableNotesPreview() {
    AureliusTheme {
        LoadableNotes(Loadable.Loaded(Note.samples.serialize()))
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FailedLoadableNotesPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            LoadableNotes(Loadable.Failed(Throwable()))
        }
    }
}
