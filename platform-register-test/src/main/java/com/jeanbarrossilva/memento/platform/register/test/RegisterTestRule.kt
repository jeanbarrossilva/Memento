package com.jeanbarrossilva.memento.platform.register.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jeanbarrossilva.memento.core.register.infra.Editor
import com.jeanbarrossilva.memento.core.register.infra.Register
import com.jeanbarrossilva.memento.core.register.infra.Repository
import com.jeanbarrossilva.memento.platform.register.RegisterDatabase
import com.jeanbarrossilva.memento.platform.register.RoomEditor
import com.jeanbarrossilva.memento.platform.register.RoomRegister
import com.jeanbarrossilva.memento.platform.register.RoomRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.rules.ExternalResource

class RegisterTestRule : ExternalResource() {
    private lateinit var database: RegisterDatabase

    lateinit var repository: Repository
        private set
    lateinit var register: Register
        private set
    lateinit var editor: Editor
        private set

    override fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, RegisterDatabase::class.java).build()
        repository = RoomRepository(database.dao)
        register = RoomRegister(database.dao)
        editor = RoomEditor(database.dao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun after() {
        runTest { register.unregisterAll() }
        database.close()
    }
}
