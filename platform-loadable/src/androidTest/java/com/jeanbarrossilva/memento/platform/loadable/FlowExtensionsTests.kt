package com.jeanbarrossilva.memento.platform.loadable

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.junit4.createComposeRule
import com.jeanbarrossilva.memento.base.extensions.test.assertTypeEquals
import com.jeanbarrossilva.memento.platform.loadable.utils.collectAsState
import com.jeanbarrossilva.memento.platform.loadable.utils.loadable
import org.junit.Rule
import org.junit.Test

internal class FlowExtensionsTests {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun emitsLoadingAsAnInitialValueWhenCollectingEmptyLoadableFlowAsState() {
        composeRule.setContent {
            val message by loadable<String>().collectAsState()

            SideEffect {
                assertTypeEquals<Loadable.Loading<String>>(message)
            }
        }
    }

    @Test
    fun emitsLoadedAsAnInitialValueWhenCollectingNonEmptyLoadableFlowAsState() {
        composeRule.setContent {
            val message by loadable("Hello, world!")
                .collectAsState()

            SideEffect {
                assertTypeEquals<Loadable.Loaded<String>>(message)
            }
        }
    }
}
