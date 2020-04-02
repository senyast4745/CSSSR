package com.github.senyast4745.csssr;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class SortClassTest {

    SortClass sortClass;

    @BeforeEach
    void setUp() {
        sortClass = new SortClass();
    }

    @Test
    public void splitString() {

        Assertions.assertTrue(assertDeepMapEquals(Map.of('a', List.of("a"), 'b', List.of("b")),
                sortClass.splitString("a b")));

        Assertions.assertTrue(assertDeepMapEquals(Map.of('a', List.of("a"), 'b', List.of("bb", "b")),
                sortClass.splitString("a b bb")));

        Assertions.assertTrue(assertDeepMapEquals(Map.of('a', List.of("a"), 'b', List.of("bb", "b")),
                sortClass.splitString("b a bb")));

        Assertions.assertTrue(assertDeepMapEquals(Map.of('a', List.of("a"), 'b', List.of("bb", "b")),
                sortClass.splitString("  b a  bb ")));

        Assertions.assertFalse(assertDeepMapEquals(Map.of('a', List.of("a"), 'b', List.of("b", "bb")),
                sortClass.splitString("a b bb")));

        Assertions.assertFalse(assertDeepMapEquals(Map.of('a', List.of("a"), 'b', List.of("bb")),
                sortClass.splitString("a b bb")));

        Assertions.assertFalse(assertDeepMapEquals(Map.of('a', List.of("aa", "a"), 'b', List.of("bb")),
                sortClass.splitString("a aa b bb")));

        Assertions.assertTrue(assertDeepMapEquals(Map.of('с', List.of("сапог", "сарай"),
                'а', List.of("арбуз"), 'б', List.of("барин", "биржа", "бокс", "болт", "бой")),
                sortClass.splitString("сапог бой сарай арбуз болт бокс барин биржа ")));

    }

    @Test
    public void checkSortOfMap() {
        Assertions.assertArrayEquals(sortClass.splitString("сапог бой сарай арбуз болт бокс барин биржа ")
                .navigableKeySet().toArray(), new Character[]{'а', 'б', 'с'});

        Assertions.assertNotEquals(sortClass.splitString("сапог бой сарай арбуз болт бокс барин биржа ")
                .navigableKeySet().toArray(), new Character[]{'б', 'с'});
    }

    private boolean assertDeepMapEquals(Map<Character, List<String>> expected,
                                        Map<Character, List<String>> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }

        return expected.entrySet().stream()
                .allMatch(e -> e.getValue().equals(actual.get(e.getKey())));
    }
}
