package com.jeanbarrossilva.memento.ui.component.scaffold.topappbar // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.memento.ui.R
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
fun MenuButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick, modifier) {
        Icon(
            Icons.Rounded.Menu,
            contentDescription = stringResource(R.string.top_app_bar_navigation_menu_button)
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MenuButtonPreview() {
    MementoTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            MenuButton(onClick = { })
        }
    }
}
