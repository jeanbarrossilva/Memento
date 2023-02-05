package com.jeanbarrossilva.memento.app

import android.app.Application
import com.jeanbarrossilva.memento.app.boundary.boundariesModule
import com.jeanbarrossilva.memento.core.register.domain.Folder
import com.jeanbarrossilva.memento.core.register.infra.Register
import com.jeanbarrossilva.memento.feature.editor.editorModule
import com.jeanbarrossilva.memento.feature.notes.domain.note.Note
import com.jeanbarrossilva.memento.feature.notes.notesModule
import com.jeanbarrossilva.memento.platform.register.registerModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MementoApplication : Application() {
    private val lifecycleScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        inject()
        registerSampleNotes()
    }

    override fun onTerminate() {
        super.onTerminate()
        lifecycleScope.cancel()
    }

    private fun inject() {
        startKoin {
            androidContext(this@MementoApplication)
            modules(boundariesModule, editorModule, notesModule, registerModule)
        }
    }

    private fun registerSampleNotes() {
        val register = get<Register>()
        Note.samples.forEach {
            lifecycleScope.launch {
                register.register(it.title, Folder.titled("Stoicism"), body = it.body)
            }
        }
    }
}
