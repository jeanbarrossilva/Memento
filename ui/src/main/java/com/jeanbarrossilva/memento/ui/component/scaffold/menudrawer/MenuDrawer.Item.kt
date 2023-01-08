package com.jeanbarrossilva.memento.ui.component.scaffold.menudrawer // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.layout.background.BackgroundContentSizing
import com.jeanbarrossilva.memento.ui.theme.MementoTheme

@Composable
fun MenuDrawerItem(
    vector: ImageVector,
    contentDescription: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit
) {
    val spacing = MementoTheme.sizes.spacing.medium
    val backgroundColor by animateColorAsState(
        if (isSelected) MementoTheme.colors.container.secondary else Color.Transparent
    )
    val contentColor = MementoTheme.colors.content.secondary

    Row(
        modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .padding(spacing),
        Arrangement.spacedBy(spacing),
        Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Icon(vector, contentDescription, Modifier.size(18.dp))
            ProvideTextStyle(MementoTheme.text.caption.copy(contentColor), label)
        }
    }
}

@Composable
private fun MenuDrawerItem(isSelected: Boolean, modifier: Modifier = Modifier) {
    MenuDrawerItem(
        Icons.Rounded.Settings,
        contentDescription = "Settings",
        isSelected,
        onClick = { },
        modifier
    ) {
        Text("Settings")
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun UnselectedMenuDrawerItemPreview() {
    MementoTheme {
        Background(contentSizing = BackgroundContentSizing.WRAP) {
            MenuDrawerItem(isSelected = false)
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SelectedMenuDrawerItemPreview() {
    MementoTheme {
        MenuDrawerItem(isSelected = true)
    }
}
