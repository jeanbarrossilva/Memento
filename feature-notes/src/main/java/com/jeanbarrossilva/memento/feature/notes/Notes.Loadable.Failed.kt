package com.jeanbarrossilva.memento.feature.notes

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.background.Background
import com.jeanbarrossilva.aurelius.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme

@Composable
internal fun FailedNotes(modifier: Modifier = Modifier) {
    val textStyle = AureliusTheme.text.title.small.copy(textAlign = TextAlign.Center)

    Column(
        modifier
            .fillMaxSize()
            .padding(AureliusTheme.sizes.spacing.huge),
        Arrangement.spacedBy(AureliusTheme.sizes.spacing.medium, Alignment.CenterVertically),
        Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.feature_notes_failed_body),
            textAlign = TextAlign.Center,
            style = textStyle
        )

        Text(
            stringResource(R.string.feature_notes_failed_title),
            style = textStyle
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FailedNotesPreview() {
    AureliusTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            FailedNotes()
        }
    }
}
