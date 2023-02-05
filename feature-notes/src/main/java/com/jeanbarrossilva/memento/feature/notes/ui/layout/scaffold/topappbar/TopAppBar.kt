package com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold.topappbar

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar.DeleteAction
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar.MenuButton
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar.TopAppBar
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.notes.domain.Selection
import com.jeanbarrossilva.memento.feature.notes.domain.ifOn
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.notes.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun TopAppBar(
    isCompact: Boolean,
    currentFolder: Folder?,
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
        title = { Text(currentFolder?.title.orEmpty()) },
        modifier,
        subtitle = { Text(subtitle) },
        actions = {
            when (selection) {
                is Selection.Off -> SearchAction(onClick = onSearchRequest)
                is Selection.On -> DeleteAction(onClick = { onDeleteRequest(selection.selected) })
            }
        },
        content = content
    )
}

@Composable
private fun TopAppBar(selection: Selection, modifier: Modifier = Modifier) {
    AureliusTheme {
        TopAppBar(
            isCompact = true,
            currentFolder = Folder.sample,
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
    AureliusTheme {
        TopAppBar(Selection.Off)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun OnSelectionTopAppBarPreview() {
    AureliusTheme {
        TopAppBar(Selection.On(Note.samples.take(2)))
    }
}
