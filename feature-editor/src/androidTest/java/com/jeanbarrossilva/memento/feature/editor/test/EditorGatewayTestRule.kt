package com.jeanbarrossilva.memento.feature.editor.test

import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway
import com.jeanbarrossilva.memento.feature.editor.infra.main.MainEditorGateway
import com.jeanbarrossilva.memento.platform.register.test.RegisterTestRule
import org.junit.rules.ExternalResource

internal class EditorGatewayTestRule(private val registerRule: RegisterTestRule) :
    ExternalResource() {
    lateinit var gateway: EditorGateway
        private set

    override fun before() {
        gateway =
            MainEditorGateway(registerRule.repository, registerRule.register, registerRule.editor)
    }
}
