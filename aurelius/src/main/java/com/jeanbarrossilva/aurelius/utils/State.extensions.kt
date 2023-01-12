package com.jeanbarrossilva.aurelius.utils // ktlint-disable filename

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

/**
 * Fire-and-forget animation function for [TextUnit].
 *
 * @param targetValue Target value of the animation.
 * @param animationSpec [AnimationSpec] with which the [targetValue] will be animated.
 **/
@Composable
internal fun animateTextUnitAsState(
    targetValue: TextUnit,
    animationSpec: AnimationSpec<Dp> = spring(visibilityThreshold = Dp.VisibilityThreshold)
): State<TextUnit> {
    val density = LocalDensity.current
    val targetValueInDp = targetValue.toDp(density)
    val animatedDpAsState = animateDpAsState(targetValueInDp, animationSpec)
    return remember {
        derivedStateOf {
            animatedDpAsState.value.toSp(density)
        }
    }
}
