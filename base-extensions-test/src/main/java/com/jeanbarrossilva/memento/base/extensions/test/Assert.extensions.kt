package com.jeanbarrossilva.memento.base.extensions.test

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.test.assertIs

/**
 * Asserts that the given object's type equals [T].
 *
 * @param actual Object whose type will be compared to and asserted against [T].
 **/
@OptIn(ExperimentalContracts::class)
inline fun <reified T> assertTypeEquals(actual: Any?) {
    contract { returns() implies (actual is T) }
    assertIs<T>(actual)
}
