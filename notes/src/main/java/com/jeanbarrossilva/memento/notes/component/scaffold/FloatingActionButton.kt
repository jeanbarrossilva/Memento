package com.jeanbarrossilva.memento.notes.component.scaffold

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.memento.notes.R
import com.jeanbarrossilva.memento.ui.component.FloatingActionButton
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
internal fun FloatingActionButton(
    isVisible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(onClick, modifier, isVisible) {
        Icon(
            Icons.Rounded.Add,
            contentDescription = stringResource(R.string.feature_notes_add)
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FloatingActionButtonPreview() {
    MementoTheme {
        FloatingActionButton(isVisible = true, onClick = { })
    }
}
