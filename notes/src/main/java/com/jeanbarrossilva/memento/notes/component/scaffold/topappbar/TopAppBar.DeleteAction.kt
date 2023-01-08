package com.jeanbarrossilva.memento.notes.component.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.memento.notes.R
import com.jeanbarrossilva.memento.ui.component.scaffold.topappbar.ActionButton
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
internal fun DeleteAction(onClick: () -> Unit, modifier: Modifier = Modifier) {
    ActionButton(onClick, modifier) {
        Icon(
            Icons.Rounded.Delete,
            contentDescription = stringResource(R.string.feature_notes_delete)
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DeleteActionPreview() {
    MementoTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            DeleteAction(onClick = { })
        }
    }
}
