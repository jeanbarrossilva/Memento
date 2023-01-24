package com.jeanbarrossilva.memento.app

import android.app.Application
import com.jeanbarrossilva.memento.app.boundary.boundariesModule
import com.jeanbarrossilva.memento.feature.notes.notesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MementoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        startKoin {
            androidContext(this@MementoApplication)
            modules(boundariesModule, notesModule)
        }
    }
}
