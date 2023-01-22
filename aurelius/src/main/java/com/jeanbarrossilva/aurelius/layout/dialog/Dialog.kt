package com.jeanbarrossilva.aurelius.layout.dialog

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.jeanbarrossilva.aurelius.theme.AureliusTheme

@Composable
fun Dialog(
    title: @Composable () -> Unit,
    body: @Composable () -> Unit,
    onDismissalRequest: () -> Unit,
    modifier: Modifier = Modifier,
    buttons: @Composable (RowScope.() -> Unit)? = null
) {
    Dialog(onDismissalRequest) {
        Column(
            modifier
                .clip(MaterialTheme.shapes.large)
                .background(AureliusTheme.colors.background)
        ) {
            Split(Modifier.padding(AureliusTheme.sizes.spacing.large)) {
                Headline(title, body)
            }

            buttons?.let {
                Split(
                    Modifier
                        .fillMaxWidth()
                        .background(AureliusTheme.colors.container.tertiary)
                        .padding(AureliusTheme.sizes.spacing.medium),
                    it
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DialogPreview() {
    AureliusTheme {
        Dialog(
            title = { Text("Attention!") },
            body = { Text("This is a very important dialog. Tap \"OK\" to proceed.") },
            onDismissalRequest = { }
        ) {
            NeutralButton(onClick = { }) {
                Text("Nah, I'm good")
            }

            ConfirmationButton(onClick = { })
        }
    }
}
