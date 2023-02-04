package com.jeanbarrossilva.memento.platform.register

import com.jeanbarrossilva.memento.core.register.infra.Editor
import com.jeanbarrossilva.memento.core.register.infra.Register
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.platform.register.utils.database
import org.koin.dsl.module

val registerModule = module {
    single<Repository> { RoomRepository(database.dao) }
    single<Register> { RoomRegister(database.dao) }
    single<Editor> { RoomEditor(database.dao) }
}
