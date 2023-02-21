package com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold.menudrawer // ktlint-disable filename

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.menudrawer.Item
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.menudrawer.MenuDrawerScope
import com.jeanbarrossilva.memento.feature.notes.R
import com.jeanbarrossilva.memento.feature.notes.domain.note.Folder

@Composable
internal fun MenuDrawerScope.Item(
    folder: Folder,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Item(
        Icons.Rounded.Folder,
        contentDescription = stringResource(R.string.feature_notes_folder, folder.title),
        isSelected,
        onClick,
        modifier
    ) {
        Text(folder.title)
    }
}
