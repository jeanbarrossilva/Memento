package com.jeanbarrossilva.memento.feature.notes.utils

import com.jeanbarrossilva.memento.feature.notes.infra.NotesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

internal val Scope.database
    get() = NotesDatabase.getInstance(androidContext())
