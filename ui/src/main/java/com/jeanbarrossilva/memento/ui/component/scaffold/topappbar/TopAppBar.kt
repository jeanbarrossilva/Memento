package com.jeanbarrossilva.memento.ui.component.scaffold.topappbar

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.isUnspecified
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeanbarrossilva.memento.ui.theme.MementoTheme
import com.jeanbarrossilva.memento.ui.utils.Horizontal
import com.jeanbarrossilva.memento.ui.utils.`if`
import com.jeanbarrossilva.memento.ui.utils.toDpSize

internal val topAppBarBackgroundColor
    @Composable get() = MementoTheme.colors.container.secondary

@Composable
fun TopAppBar(
    isCompact: Boolean,
    navigationButton: @Composable () -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { },
    content: @Composable () -> Unit
) {
    ConstraintLayout(modifier.fillMaxSize()) {
        val (topAppBarRef, contentRef) = createRefs()

        TopAppBar(
            isCompact,
            navigationButton,
            title,
            Modifier.constrainAs(topAppBarRef) {
                width = Dimension.fillToConstraints
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            },
            subtitle,
            actions
        )

        Box(
            Modifier.constrainAs(contentRef) {
                width = Dimension.fillToConstraints
                this.height = Dimension.fillToConstraints
                top.linkTo(topAppBarRef.bottom)
                bottom.linkTo(parent.bottom)
                centerHorizontallyTo(parent)
            }
        ) {
            content()
        }
    }
}

@Composable
fun TopAppBar(
    isCompact: Boolean,
    navigationButton: @Composable () -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { }
) {
    val density = LocalDensity.current
    val spacing by animateDpAsState(
        if (isCompact) MementoTheme.sizes.spacing.medium else MementoTheme.sizes.spacing.large,
        MementoTheme.animation.spec()
    )
    var fullHeight by remember { mutableStateOf(Dp.Unspecified) }
    val currentHeight by animateDpAsState(
        if (isCompact) 64.dp else fullHeight,
        MementoTheme.animation.spec()
    )

    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .`if`(currentHeight.isSpecified) { height(currentHeight) }
            .onPlaced {
                if (currentHeight.isUnspecified) {
                    fullHeight = it.size.toDpSize(density).height
                }
            }
            .background(
                Brush.linearGradient(
                    listOf(topAppBarBackgroundColor, MementoTheme.colors.container.tertiary),
                    start = Offset.Horizontal
                )
            )
            .padding(spacing)
    ) {
        val (leadingRef, trailingRef) = createRefs()

        Row(
            Modifier
                .constrainAs(leadingRef) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(trailingRef.end)
                    centerVerticallyTo(parent)
                },
            Arrangement.spacedBy(spacing),
            Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides MementoTheme.colors.content.secondary,
                content = navigationButton
            )

            Headline(isCompact, title, subtitle)
        }

        Row(
            Modifier.constrainAs(trailingRef) {
                end.linkTo(parent.end)
                centerVerticallyTo(parent)
            },
            Arrangement.spacedBy(MementoTheme.sizes.spacing.small),
            Alignment.CenterVertically,
            content = actions
        )
    }
}

@Composable
private fun TopAppBar(isCompact: Boolean, modifier: Modifier = Modifier) {
    TopAppBar(
        isCompact,
        navigationButton = { MenuButton(onClick = { }) },
        title = { Text("Title") },
        modifier,
        subtitle = { Text("Subtitle") }
    )
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ExpandedTopAppBarPreview() {
    MementoTheme {
        TopAppBar(isCompact = false)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CompactTopAppBarPreview() {
    MementoTheme {
        TopAppBar(isCompact = true)
    }
}
