package com.jeanbarrossilva.memento.feature.editor

import android.content.Context
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.aurelius.core.ComposableActivity
import com.jeanbarrossilva.memento.feature.editor.domain.EditorMode
import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway
import com.jeanbarrossilva.memento.platform.extensions.argumentOf
import com.jeanbarrossilva.memento.platform.extensions.startActivity
import org.koin.android.ext.android.inject

class EditorActivity internal constructor() : ComposableActivity() {
    private val gateway by inject<EditorGateway>()
    private val noteID by argumentOf<String?>(NOTE_ID_KEY)
    private val viewModel by viewModels<EditorViewModel> {
        EditorViewModel.createFactory(gateway, noteID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveOnBackPress()
    }

    @Composable
    override fun Content() {
        Editor(
            viewModel,
            onNavigationRequest = onBackPressedDispatcher::onBackPressed,
            onDeletionRequest = { }
        )
    }

    private fun saveOnBackPress() {
        onBackPressedDispatcher.addCallback {
            when (viewModel.getMode().value) {
                is EditorMode.Reading -> finish()
                is EditorMode.Editing -> viewModel.save()
            }
        }
    }

    companion object {
        private const val NOTE_ID_KEY = "note_id"

        fun start(context: Context, noteID: String?) {
            context.startActivity<EditorActivity>(NOTE_ID_KEY to noteID)
        }
    }
}
