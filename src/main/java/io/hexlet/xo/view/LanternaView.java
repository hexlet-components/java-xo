package io.hexlet.xo.view;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class LanternaView {

    private static final Screen SCREEN = TerminalFacade.createScreen();

    public static void uiStart() {
        SCREEN.startScreen();
    }

    public static void uiStop() {
        SCREEN.stopScreen();
    }

    public static void uiClearScreen() {
        SCREEN.clear();
    }

    public static void uiPrint(final String out, final int screenColumn, final int screenRow) {
        SCREEN.putString(screenColumn, screenRow, out, Terminal.Color.WHITE, Terminal.Color.BLACK);
        SCREEN.refresh();
    }

    public static void uiShowGameName() {
        final int screenRow = 4;
        uiPrint("XO", SCREEN.getTerminalSize().getColumns() / 2, screenRow);
    }
}
