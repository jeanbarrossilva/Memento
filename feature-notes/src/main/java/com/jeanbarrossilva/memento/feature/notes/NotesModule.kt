package com.jeanbarrossilva.memento.feature.notes

import com.jeanbarrossilva.memento.feature.notes.infra.NotesGateway
import com.jeanbarrossilva.memento.feature.notes.infra.main.MainNotesGateway
import com.jeanbarrossilva.memento.feature.notes.utils.database
import org.koin.dsl.module

val notesModule = module {
    single<NotesGateway> {
        MainNotesGateway(register = get(), repository = get(), database.dao)
    }
}
