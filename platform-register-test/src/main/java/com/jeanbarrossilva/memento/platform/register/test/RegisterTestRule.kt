package com.jeanbarrossilva.memento.platform.register.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jeanbarrossilva.memento.platform.register.RegisterDatabase
import com.jeanbarrossilva.memento.platform.register.RoomEditor
import com.jeanbarrossilva.memento.platform.register.RoomRegister
import com.jeanbarrossilva.memento.platform.register.RoomRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.rules.ExternalResource

class RegisterTestRule : ExternalResource() {
    private lateinit var database: RegisterDatabase

    lateinit var repository: RoomRepository
        private set
    lateinit var register: RoomRegister
        private set
    lateinit var editor: RoomEditor
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
