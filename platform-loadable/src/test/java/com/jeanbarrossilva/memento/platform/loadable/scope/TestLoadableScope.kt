package com.jeanbarrossilva.memento.platform.loadable.scope

import com.jeanbarrossilva.memento.platform.loadable.Loadable
import com.jeanbarrossilva.memento.platform.loadable.LoadableScope
import java.io.Serializable

internal class TestLoadableScope<T : Serializable?>(private val onSend: (Loadable<T>) -> Unit) :
    LoadableScope<T>() {
    override suspend fun send(loadable: Loadable<T>) {
        onSend(loadable)
    }
}
