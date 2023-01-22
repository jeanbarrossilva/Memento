package com.jeanbarrossilva.memento.app.boundary

import com.jeanbarrossilva.memento.feature.notes.NotesBoundary
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val boundariesModule = module {
    singleOf<NotesBoundary>(::DefaultNotesBoundary)
}
