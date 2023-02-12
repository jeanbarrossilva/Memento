package com.jeanbarrossilva.memento.platform.loadable.scope

import com.jeanbarrossilva.memento.base.extensions.test.assertTypeEquals
import com.jeanbarrossilva.memento.platform.loadable.Loadable
import com.jeanbarrossilva.memento.platform.loadable.LoadableScope
import java.io.Serializable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

internal class LoadableScopeTests {
    private val sent = mutableListOf<Loadable<Serializable?>>()
    private val scope: LoadableScope<Serializable?> = TestLoadableScope(onSend = sent::add)

    @After
    fun tearDown() {
        sent.clear()
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a Loading Loadable WHEN sending it to the scope THEN it's received`() {
        runTest { scope.load() }
        assertTypeEquals<Loadable.Loading<Serializable?>>(sent.single())
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a Loaded Loadable WHEN sending it to the scope THEN it's received`() {
        runTest { scope.load(null) }
        assertEquals(Loadable.Loaded(null), sent.single())
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a Failed Loadable WHEN sending it to the scope THEN it's received`() {
        runTest { scope.fail(NullPointerException()) }
        sent.single().let {
            assertTypeEquals<Loadable.Failed<Serializable?>>(it)
            assertTypeEquals<NullPointerException>(it.error)
        }
    }
}
