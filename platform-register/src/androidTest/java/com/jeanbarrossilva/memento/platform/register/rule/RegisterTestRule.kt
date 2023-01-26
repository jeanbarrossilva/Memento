package com.jeanbarrossilva.memento.platform.register.rule

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jeanbarrossilva.memento.platform.register.RegisterDatabase
import com.jeanbarrossilva.memento.platform.register.RoomEditor
import com.jeanbarrossilva.memento.platform.register.RoomRegister
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.rules.ExternalResource

internal class RegisterTestRule : ExternalResource() {
    private lateinit var database: RegisterDatabase

    lateinit var register: RoomRegister
        private set
    lateinit var editor: RoomEditor
        private set

    override fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, RegisterDatabase::class.java).build()
        register = RoomRegister(database.dao)
        editor = RoomEditor(database.dao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun after() {
        runTest { register.unregisterAll() }
        database.close()
    }
}
