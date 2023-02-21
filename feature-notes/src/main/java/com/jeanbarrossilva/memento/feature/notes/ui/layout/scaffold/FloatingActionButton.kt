package com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.FloatingActionButton
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.notes.R

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
    AureliusTheme {
        FloatingActionButton(isVisible = true, onClick = { })
    }
}
