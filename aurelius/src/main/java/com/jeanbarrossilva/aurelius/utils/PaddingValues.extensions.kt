package com.jeanbarrossilva.aurelius.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp

/** End padding calculated through the [LocalLayoutDirection]. **/
internal val PaddingValues.end
    @Composable get() = calculateEndPadding(LocalLayoutDirection.current)

/** Whether or not the given [PaddingValues] have a negative value. **/
internal val PaddingValues.isNegative
    @Composable get() = start < 0.dp ||
        calculateTopPadding() < 0.dp ||
        end < 0.dp ||
        calculateBottomPadding() < 0.dp

/** Start padding calculated through the [LocalLayoutDirection]. **/
internal val PaddingValues.start
    @Composable get() = calculateStartPadding(LocalLayoutDirection.current)

/**
 * Adds the [PaddingValues].
 *
 * @param other [PaddingValues] to add to the receiver one.
 **/
@Composable
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
    return PaddingValues(
        start + other.start,
        calculateTopPadding() + other.calculateTopPadding(),
        end + other.end,
        calculateBottomPadding() + other.calculateBottomPadding()
    )
}

/**
 * Subtracts the [PaddingValues].
 *
 * @param other [PaddingValues] to subtract from the receiver one.
 **/
@Composable
operator fun PaddingValues.minus(other: PaddingValues): PaddingValues {
    return PaddingValues(
        start - other.start,
        calculateTopPadding() - other.calculateTopPadding(),
        end - other.end,
        calculateBottomPadding() - other.calculateBottomPadding()
    )
}
