package com.jeanbarrossilva.memento.ui.utils // ktlint-disable filename

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalTypeInference

/**
 * Creates a [MutableStateFlow] that holds an [initialValue] and then receives the one that was
 * emitted to it most rapidly (either [origin]'s or its own, through the provided [block]); after
 * that, only values from the [block] are received.
 *
 * @param origin [Flow] that'll probably receive the value that's supposed to be the initial one of
 * the [Flow] that's being created.
 * @param initialValue
 * @param block Operation to be run on the created [Flow]'s [FlowCollector].
 **/
@OptIn(ExperimentalTypeInference::class)
fun <T> ViewModel.flowOf(
    origin: Flow<T>,
    initialValue: T,
    @BuilderInference block: suspend FlowCollector<T>.() -> Unit
): MutableStateFlow<T> {
    var isNewFlowConsumed = false
    return flowOf(initialValue) {
        flow(block).onFirst { isNewFlowConsumed = true }.collect(::emit)
        origin.takeWhile { !isNewFlowConsumed }.take(1).collect(::emit)
    }
}

/**
 * Creates a [MutableStateFlow] with the given [initialValue] as the initial one and runs the specified
 * [block] in the [viewModelScope].
 *
 * @param initialValue Initial value for the [MutableStateFlow] to hold.
 * @param block Operation to be run on the created [MutableStateFlow]'s [FlowCollector].
 **/
@OptIn(ExperimentalTypeInference::class)
fun <T> ViewModel.flowOf(
    initialValue: T,
    @BuilderInference block: suspend FlowCollector<T>.() -> Unit
): MutableStateFlow<T> {
    return MutableStateFlow(initialValue).apply {
        viewModelScope.launch {
            block()
        }
    }
}
