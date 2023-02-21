package com.jeanbarrossilva.memento.feature.editor.ui.dialog

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.aurelius.ui.layout.dialog.ConfirmationButton
import com.jeanbarrossilva.aurelius.ui.layout.dialog.Dialog
import com.jeanbarrossilva.aurelius.ui.layout.dialog.NeutralButton
import com.jeanbarrossilva.aurelius.ui.theme.AureliusTheme
import com.jeanbarrossilva.memento.feature.editor.R

@Composable
internal fun DeletionConfirmationDialog(
    onConfirmationRequest: () -> Unit,
    onDismissalRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        title = { Text(stringResource(R.string.feature_editor_deletion_confirmation_title)) },
        body = { Text(stringResource(R.string.feature_editor_deletion_confirmation_body)) },
        onDismissalRequest,
        modifier
    ) {
        NeutralButton(onClick = onDismissalRequest) {
            Text(stringResource(android.R.string.cancel))
        }

        ConfirmationButton(onClick = {
            onConfirmationRequest()
            onDismissalRequest()
        })
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DeletionConfirmationDialogPreview() {
    AureliusTheme {
        DeletionConfirmationDialog(onConfirmationRequest = { }, onDismissalRequest = { })
    }
}
