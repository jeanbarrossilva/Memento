package com.jeanbarrossilva.memento.ui.utils

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import androidx.annotation.Px
import androidx.recyclerview.widget.DividerItemDecoration

/**
 * Creates a [DividerItemDecoration] with the given size.
 *
 * @param context [Context] through which the [DividerItemDecoration] will be created.
 * @param orientation Either a [horizontal][DividerItemDecoration.HORIZONTAL] or a
 * [vertical][DividerItemDecoration.VERTICAL] orientation.
 * @param width Width of the [DividerItemDecoration].
 * @param height Height of the [DividerItemDecoration].
 **/
internal fun DividerItemDecoration(
    context: Context,
    orientation: Int,
    @Px width: Int,
    @Px height: Int
): DividerItemDecoration {
    val drawable = ShapeDrawable().apply {
        intrinsicWidth = width
        intrinsicHeight = height
    }
    return DividerItemDecoration(context, orientation).apply { setDrawable(drawable) }
}
