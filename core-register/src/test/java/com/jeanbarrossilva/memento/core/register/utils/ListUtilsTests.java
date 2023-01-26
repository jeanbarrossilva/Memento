package com.jeanbarrossilva.memento.core.register.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.jeanbarrossilva.memento.core.register.utils.list.ListUtils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ListUtilsTests {
    @Test
    void replace() {
        final ArrayList<Character> expected = new ArrayList<>();
        expected.add('A');
        expected.add('B');
        expected.add('C');
        final ArrayList<Character> actual = new ArrayList<>();
        actual.add('A');
        actual.add('D');
        actual.add('C');
        ListUtils.replace(actual, element -> element == 'D', element -> 'C');
        assertThat(actual).isEqualTo(expected);
    }
}
