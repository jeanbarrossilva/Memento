package com.jeanbarrossilva.memento.feature.editor

import com.jeanbarrossilva.memento.feature.editor.infra.EditorGateway
import com.jeanbarrossilva.memento.feature.editor.infra.main.MainEditorGateway
import org.koin.dsl.module

val editorModule = module {
    single<EditorGateway> {
        MainEditorGateway(repository = get(), register = get(), editor = get())
    }
}
