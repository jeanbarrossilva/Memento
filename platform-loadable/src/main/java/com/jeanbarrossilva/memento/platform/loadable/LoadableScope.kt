package com.jeanbarrossilva.memento.platform.loadable

import java.io.Serializable

/** Scope through which [Loadable]s are sent. **/
abstract class LoadableScope<T : Serializable?> internal constructor() {
    /** Sends a [Loadable.Loading]. **/
    suspend fun load() {
        send(Loadable.Loading())
    }

    /**
     * Sends a [Loadable.Loaded] with the given [value].
     *
     * @param value Value to be set as the [Loadable.Loaded.value].
     **/
    suspend fun load(value: T) {
        send(Loadable.Loaded(value))
    }

    /**
     * Sends a [Loadable.Failed] with the given [error].
     *
     * @param error [Throwable] to be set as the [Loadable.Failed.error].
     **/
    suspend fun fail(error: Throwable) {
        send(Loadable.Failed(error))
    }

    /**
     * Sends the given [loadable].
     *
     * @param loadable [Loadable] to be sent.
     **/
    protected abstract suspend fun send(loadable: Loadable<T>)
}
