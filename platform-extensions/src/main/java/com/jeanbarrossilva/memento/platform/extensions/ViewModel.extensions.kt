package com.jeanbarrossilva.memento.platform.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

/**
 * Creates a [MutableStateFlow] with the given [initialValue] as the initial one and collects
 * [origin] in the [viewModelScope] afterwards.
 *
 * @param initialValue Initial value for the [MutableStateFlow] to hold.
 * @param origin Operation to be run on the created [MutableStateFlow]'s [FlowCollector].
 **/
fun <T> ViewModel.flowOf(initialValue: T, origin: suspend () -> Flow<T>): MutableStateFlow<T> {
    return MutableStateFlow(initialValue).apply {
        viewModelScope.launch {
            emitAll(origin())
        }
    }
}
