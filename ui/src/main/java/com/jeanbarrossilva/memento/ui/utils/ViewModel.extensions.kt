package com.jeanbarrossilva.memento.ui.utils // ktlint-disable filename

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.experimental.ExperimentalTypeInference
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Creates a [MutableStateFlow] with the given [value] as the initial one and runs the specified
 * [block] in the [viewModelScope].
 *
 * @param value Initial value for the [MutableStateFlow] to hold.
 * @param block Operation to be run on the created [MutableStateFlow]'s [FlowCollector].
 **/
@OptIn(ExperimentalTypeInference::class)
fun <T> ViewModel.flowOf(
    value: T,
    @BuilderInference block: suspend FlowCollector<T>.() -> Unit
): MutableStateFlow<T> {
    return MutableStateFlow(value).apply {
        viewModelScope.launch {
            block()
        }
    }
}
