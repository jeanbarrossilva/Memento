package com.jeanbarrossilva.memento.feature.notes.component.scaffold.menudrawer // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.component.scaffold.menudrawer.MenuDrawerItem
import com.jeanbarrossilva.aurelius.layout.background.Background
import com.jeanbarrossilva.aurelius.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.notes.domain.note.NoteFolder
import com.jeanbarrossilva.memento.notes.R

@Composable
internal fun MenuDrawerItem(
    folder: NoteFolder,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    MenuDrawerItem(
        Icons.Rounded.Folder,
        contentDescription = stringResource(R.string.feature_notes_folder, folder.title),
        isSelected,
        onClick,
        modifier
    ) {
        Text(folder.title)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MenuDrawerItemPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            MenuDrawerItem(NoteFolder.sample, isSelected = false, onClick = { })
        }
    }
}
