package com.jeanbarrossilva.memento.ui.component.scaffold.menudrawer

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeanbarrossilva.memento.ui.component.scaffold.topappbar.topAppBarBackgroundColor
import com.jeanbarrossilva.memento.ui.layout.background.Background
import com.jeanbarrossilva.memento.ui.theme.MementoTheme
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

internal val backgroundColor
    @Composable get() = MementoTheme.colors.background

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MenuDrawer(
    title: @Composable () -> Unit,
    items: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable MenuDrawerScope.() -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val coroutineScope = rememberCoroutineScope()

    @Suppress("SpellCheckingInspection")
    val swipeableState =
        rememberSwipeableState(initialValue = DrawerValue.Closed, MementoTheme.animation.spec())

    var menuDrawerWidth by remember { mutableStateOf<MenuDrawerWidth>(MenuDrawerWidth.Unspecified) }
    val menuDrawerScope = SwipeableMenuDrawerScope(swipeableState)
    val isMenuDrawerOpen = swipeableState.currentValue == DrawerValue.Open
    val scrimColor = MementoTheme.colors.scrim
    val statusBarColor by animateColorAsState(
        if (isMenuDrawerOpen) topAppBarBackgroundColor else scrimColor,
        MementoTheme.animation.spec()
    )
    val navigationBarColor by animateColorAsState(
        if (isMenuDrawerOpen) MementoTheme.colors.background else scrimColor,
        MementoTheme.animation.spec()
    )

    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor)
        systemUiController.setNavigationBarColor(navigationBarColor)
    }

    Box(
        modifier.swipeable(
            swipeableState,
            anchors = mapOf(-menuDrawerWidth.value to DrawerValue.Closed, 0f to DrawerValue.Open),
            Orientation.Horizontal
        )
    ) {
        menuDrawerScope.content()

        if (isMenuDrawerOpen) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(MementoTheme.colors.scrim)
                    .alpha(swipeableState.overflow.value)
                    .clickable(MutableInteractionSource(), indication = null) {
                        coroutineScope.launch {
                            menuDrawerScope.close()
                        }
                    }
            )
        }

        MenuDrawer(
            title,
            Modifier
                .onPlaced {
                    if (menuDrawerWidth is MenuDrawerWidth.Unspecified) {
                        menuDrawerWidth = MenuDrawerWidth.Specified(it.size.width.toFloat())
                    }
                }
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), y = 0) },
            items
        )
    }
}

@Composable
fun MenuDrawer(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollableState = rememberScrollState()
    val shape = MaterialTheme.shapes.extraLarge.copy(
        topStart = ZeroCornerSize,
        bottomStart = ZeroCornerSize
    )
    val spacing = MementoTheme.sizes.spacing.huge

    Column(
        modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight()
            .clip(shape)
            .background(backgroundColor)
            .scrollable(scrollableState, Orientation.Vertical)
            .padding(spacing),
        Arrangement.spacedBy(spacing)
    ) {
        ProvideTextStyle(MementoTheme.text.headline, title)
        MenuDrawerItems(content = content)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun MenuDrawerPreview() {
    MementoTheme {
        MenuDrawer(
            title = { Text("Title") },
            items = {
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
        ) {
            Background {
            }
        }
    }
}
