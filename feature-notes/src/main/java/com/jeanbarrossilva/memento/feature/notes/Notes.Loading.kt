package com.jeanbarrossilva.memento.feature.notes

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

internal const val LOADING_NOTES_TAG = "notes_loadable_loading"

@Composable
internal fun LoadingNotes(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxSize()
            .testTag(LOADING_NOTES_TAG),
        Alignment.Center
    ) {
        CircularProgressIndicator(modifier, AureliusTheme.colors.content.secondary)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingNotesPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            LoadingNotes()
        }
    }
}
