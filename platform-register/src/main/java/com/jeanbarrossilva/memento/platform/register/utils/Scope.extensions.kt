package com.jeanbarrossilva.memento.platform.register.utils

import com.jeanbarrossilva.memento.platform.register.RegisterDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

internal val Scope.database
    get() = RegisterDatabase.getInstance(androidContext())