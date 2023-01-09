package com.jeanbarrossilva.memento.notes.component.scaffold.menudrawer

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.memento.notes.R
import com.jeanbarrossilva.memento.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.ui.component.scaffold.menudrawer.MenuDrawer
import com.jeanbarrossilva.memento.ui.component.scaffold.menudrawer.MenuDrawerScope
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
internal fun MenuDrawer(
    folders: List<NoteFolder>,
    currentFolder: NoteFolder,
    onCurrentFolderChange: (folder: NoteFolder) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable MenuDrawerScope.() -> Unit
) {
    MenuDrawer(
        title = { Text(stringResource(R.string.feature_notes_folders)) },
        items = {
            MenuDrawerItem(
                NoteFolder.All,
                isSelected = currentFolder == NoteFolder.All,
                onClick = { onCurrentFolderChange(NoteFolder.All) }
            )

            folders.forEach {
                MenuDrawerItem(
                    it,
                    isSelected = it == currentFolder,
                    onClick = { onCurrentFolderChange(it) }
                )
            }
        },
        modifier,
        content
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MenuDrawerPreview() {
    MementoTheme {
        MenuDrawer(
            NoteFolder.Custom.samples,
            currentFolder = NoteFolder.All,
            onCurrentFolderChange = { }
        ) {
            Background {
            }
        }
    }
}
