package com.jeanbarrossilva.memento.notes.component.scaffold.topappbar

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.memento.notes.R
import com.jeanbarrossilva.memento.notes.domain.Selection
import com.jeanbarrossilva.memento.notes.domain.ifOn
import com.jeanbarrossilva.memento.notes.domain.note.Note
import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.notes.utils.orEmpty
import com.jeanbarrossilva.memento.ui.component.scaffold.topappbar.MenuButton
import com.jeanbarrossilva.memento.ui.component.scaffold.topappbar.TopAppBar
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun TopAppBar(
    isCompact: Boolean,
    currentFolder: NoteFolder?,
    noteCount: Int,
    selection: Selection,
    onNavigationRequest: () -> Unit,
    onSearchRequest: () -> Unit,
    onDeleteRequest: (notes: List<Note>) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val subtitle = selection
        .ifOn {
            pluralStringResource(
                R.plurals.feature_notes_selection_count,
                selected.size,
                selected.size
            )
        }
        ?: pluralStringResource(R.plurals.feature_notes_count, noteCount, noteCount)

    TopAppBar(
        isCompact,
        navigationButton = { MenuButton(onClick = onNavigationRequest) },
        title = { Text(currentFolder.orEmpty.title) },
        modifier,
        subtitle = { Text(subtitle) },
        actions = {
            when (selection) {
                is Selection.Off -> SearchAction(onClick = onSearchRequest)
                is Selection.On -> DeleteAction(onClick = { onDeleteRequest(selection.selected) })
            }
        },
        content
    )
}

@Composable
private fun TopAppBar(selection: Selection, modifier: Modifier = Modifier) {
    MementoTheme {
        TopAppBar(
            isCompact = true,
            currentFolder = NoteFolder.sample,
            noteCount = Note.samples.size,
            selection,
            onNavigationRequest = { },
            onSearchRequest = { },
            onDeleteRequest = { },
            modifier
        ) {
            Background {
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun OffSelectionTopAppBarPreview() {
    MementoTheme {
        TopAppBar(Selection.Off)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun OnSelectionTopAppBarPreview() {
    MementoTheme {
        TopAppBar(Selection.On(Note.samples.take(2)))
    }
}
