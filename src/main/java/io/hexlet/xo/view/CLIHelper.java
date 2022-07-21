package io.hexlet.xo.view;

public class CLIHelper {

    static final int SCREEN_LINES_COUNT = 100;

    public static void cleanScreenDefault() {

        for (int i = 0; i < SCREEN_LINES_COUNT; ++i) {
            System.out.println();
        }
    }
}


