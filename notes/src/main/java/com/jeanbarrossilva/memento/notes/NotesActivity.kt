package com.jeanbarrossilva.memento.notes

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.aurelius.core.ComposableActivity
import com.jeanbarrossilva.memento.notes.infra.inmemory.InMemoryNotesGateway

internal class NotesActivity : ComposableActivity() {
    private val gateway = InMemoryNotesGateway(this)
    private val viewModel by viewModels<NotesViewModel> { NotesViewModel.createFactory(gateway) }

    @Composable
    override fun Content() {
        Notes(viewModel)
    }
}
