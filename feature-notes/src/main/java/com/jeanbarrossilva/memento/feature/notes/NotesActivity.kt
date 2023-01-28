package com.jeanbarrossilva.memento.feature.notes

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.aurelius.core.ComposableActivity
import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import org.koin.android.ext.android.inject

internal class NotesActivity : ComposableActivity() {
    private val gateway by inject<NotesGateway>()
    private val viewModel by viewModels<NotesViewModel> {
        NotesViewModel.createFactory(application, gateway)
    }
    private val boundary by inject<NotesBoundary>()

    @Composable
    override fun Content() {
        Notes(viewModel, boundary)
    }
}
