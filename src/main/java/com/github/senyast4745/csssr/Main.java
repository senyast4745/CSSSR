package com.github.senyast4745.csssr;

public class Main {

    public static void main(String[] args) {
        SortClass sort = new SortClass();

        sort.splitString("сапог сарай арбуз болт бокс биржа бой барин").forEach((k, v) -> {
            if (v.size() > 1) {
                System.out.printf("key: %s, value: %s\n", k, v);
            }
        });

    }
}
