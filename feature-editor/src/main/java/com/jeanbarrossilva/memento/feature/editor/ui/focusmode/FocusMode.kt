package com.jeanbarrossilva.memento.feature.editor.ui.focusmode

import android.app.Activity
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.view.WindowInsetsCompat
import com.jeanbarrossilva.aurelius.utils.insetsControllerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal sealed class FocusMode {
    object Default : FocusMode()

    data class Scrolling(private val lazyListState: LazyListState) : FocusMode() {
        override fun onFocus(coroutineScope: CoroutineScope, isEmptyNote: Boolean) {
            if (!isEmptyNote) {
                coroutineScope.launch {
                    // Unnoticeable scroll just for compacting the top app bar.
                    lazyListState.animateScrollBy(.1f)

                    lazyListState.animateScrollToItem(1)
                }
            }
        }
    }

    @Composable
    fun FocusEffect(
        coroutineScope: CoroutineScope,
        focusRequester: FocusRequester,
        value: TextFieldValue,
        onValueChange: (value: TextFieldValue) -> Unit,
        isEmptyNote: Boolean,
        isFocused: Boolean
    ) {
        val context = LocalContext.current
        val windowInsetsController = (context as? Activity)?.window?.insetsControllerCompat
        val keyboardMaskType = WindowInsetsCompat.Type.ime()

        DisposableEffect(isFocused) {
            if (isFocused) {
                windowInsetsController?.show(keyboardMaskType)
                focusRequester.requestFocus()
                onFocus(coroutineScope, isEmptyNote)
                onValueChange(value.copy(selection = TextRange(value.text.length)))
            } else {
                windowInsetsController?.hide(keyboardMaskType)
            }
            onDispose { }
        }
    }

    protected open fun onFocus(coroutineScope: CoroutineScope, isEmptyNote: Boolean) {
    }
}
