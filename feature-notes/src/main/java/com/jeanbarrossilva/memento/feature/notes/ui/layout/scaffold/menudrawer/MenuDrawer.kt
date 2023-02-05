package com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold.menudrawer

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.menudrawer.MenuDrawer
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.menudrawer.MenuDrawerScope
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.menudrawer.rememberMenuDrawerScope
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder as _Folder
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.notes.R
import kotlinx.coroutines.launch

@Composable
internal fun MenuDrawer(
    notes: List<Note>,
    currentFolder: _Folder,
    onCurrentFolderChange: (currentFolder: _Folder) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable MenuDrawerScope.() -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val menuDrawerScope = rememberMenuDrawerScope()
    val defaultFolder = remember { _Folder.getDefault(context) }

    @Suppress("NAME_SHADOWING")
    val notes = remember(notes) {
        notes.map { note ->
            note.folder?.let { note } ?: note.copy(folder = defaultFolder)
        }
    }

    val folders = remember(notes) { notes.mapNotNull(Note::folder).toHashSet() }
    val changeCurrentFolderAndClose: (_Folder) -> Unit = remember {
        {
            onCurrentFolderChange(it)
            coroutineScope.launch { menuDrawerScope.close() }
        }
    }

    MenuDrawer(
        title = { Text(stringResource(R.string.feature_notes_folders)) },
        items = {
            folders.forEach {
                MenuDrawerItem(
                    it,
                    isSelected = it == currentFolder,
                    onClick = { changeCurrentFolderAndClose(it) }
                )
            }
        },
        modifier,
        menuDrawerScope,
        content
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MenuDrawerPreview() {
    AureliusTheme {
        MenuDrawer(
            Note.samples,
            currentFolder = _Folder.sample,
            onCurrentFolderChange = { }
        ) {
            Background {
            }
        }
    }
}
