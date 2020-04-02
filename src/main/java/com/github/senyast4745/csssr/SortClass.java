package com.github.senyast4745.csssr;

import java.util.*;
import java.util.stream.Stream;

public class SortClass {

    private final Comparator<String> customComparator = (String a, String b) ->
            a.length() == b.length() ? a.compareTo(b) : b.length() - a.length();

    public NavigableMap<Character, List<String>> splitString(String input) {
        NavigableMap<Character, List<String>> struct = new TreeMap<>();
        String[] parsed = input.split("\\s+");
        Stream.of(parsed).filter(w -> w.length() > 0).forEach(w -> {
            Character key = w.charAt(0);
            struct.put(key, insertIntoList(struct.getOrDefault(key, new ArrayList<>()), w));
        });
        return struct;
    }

    private List<String> insertIntoList(List<String> list, String el) {
        int index = binarySearch(list, el);
        list.add(index, el);
        return list;
    }

    private int binarySearch(List<String> list, String el) {
        int l = -1, r = list.size();
        while (l < r - 1) {
            int m = (l + r) / 2;
            if (list.get(m).equals(el))
                return m;
            if (customComparator.compare(list.get(m), el) < 0)
                l = m;
            else
                r = m;
        }
        return r;
    }

}
