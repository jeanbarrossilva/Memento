package com.jeanbarrossilva.memento.feature.notes

import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import com.jeanbarrossilva.memento.feature.notes.infra.inmemory.InMemoryNotesGateway
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val notesModule = module {
    single<NotesGateway> {
        InMemoryNotesGateway(androidContext())
    }
}
