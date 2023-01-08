package com.jeanbarrossilva.memento.ui.utils

import androidx.compose.foundation.lazy.LazyListState

/** Whether or not the user is scrolling. **/
val LazyListState.isScrolling
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0
