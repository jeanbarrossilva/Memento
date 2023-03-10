package com.jeanbarrossilva.memento.feature.notes.ui.layout.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.layout.scaffold.topappbar.ActionButton
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.notes.R

@Composable
internal fun SearchAction(onClick: () -> Unit, modifier: Modifier = Modifier) {
    ActionButton(onClick, modifier) {
        Icon(
            Icons.Rounded.Search,
            contentDescription = stringResource(R.string.feature_notes_search)
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SearchActionPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            SearchAction(onClick = { })
        }
    }
}
