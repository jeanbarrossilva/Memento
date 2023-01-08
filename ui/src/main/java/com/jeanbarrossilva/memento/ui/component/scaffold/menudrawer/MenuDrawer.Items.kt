package com.jeanbarrossilva.memento.ui.component.scaffold.menudrawer // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
internal fun MenuDrawerItems(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier, Arrangement.spacedBy(MementoTheme.sizes.spacing.small), content = content)
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MenuDrawerItemsPreview() {
    MementoTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            MenuDrawerItems {
                MenuDrawerItem(
                    Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    isSelected = true,
                    onClick = { }
                ) {
                    Text("Settings")
                }

                MenuDrawerItem(
                    Icons.Rounded.ThumbUp,
                    contentDescription = "Rate",
                    isSelected = false,
                    onClick = { }
                ) {
                    Text("Rate")
                }
            }
        }
    }
}
