package io.hexlet.xo.view;

import io.hexlet.xo.common.IXOProperty;
import io.hexlet.xo.controllers.CurrentMoveController;
import io.hexlet.xo.controllers.MoveController;
import io.hexlet.xo.controllers.WinnerController;
import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.Player;
import io.hexlet.xo.model.Point;
import io.hexlet.xo.model.exceptions.AlreadyOccupiedException;
import io.hexlet.xo.model.exceptions.InvalidPointException;
import io.hexlet.xo.view.reader.ConsoleCoordinateReader;


public final class ConsoleView {

    private static final Character SEPARATOR = IXOProperty.getDefaultProperties().getSeparatorCharacter();

    private final CurrentMoveController currentMoveController = new CurrentMoveController();

    private final WinnerController winnerController = new WinnerController();

    private final MoveController moveController = new MoveController();

    private final ConsoleCoordinateReader coordinateReader = new ConsoleCoordinateReader();

    private final int separatorLength = 11;

    private final int indentLength = 50;

    private final int halfIndentLength = indentLength / 2;

    private final String hyperView = " -- ";

    public void show(final Game game) {
        final Player player1 = game.getPlayers()[0];
        final Player player2 = game.getPlayers()[1];

        System.out.format("%" + indentLength + "s\n", "Game name:" + game.getName());

        System.out.format("%"
                + (halfIndentLength
                - separatorLength
                + player2.getName().length()
                + hyperView.length()
                + player2.getFigure().toString().length())
                + "s %"
                + (halfIndentLength
                + separatorLength
                + halfIndentLength
                - player2.getName().length()
                - hyperView.length()
                - player2.getFigure().toString().length())
                + "s",
                player1.getName() + hyperView + player1.getFigure(),
                player2.getName() + hyperView + player2.getFigure() + "\n");

        final Field field = game.getField();
        for (int y = 0; y < field.getSize(); y++) {
            if (y != 0) {
                System.out.format("%" + indentLength + "s\n", generateSeparator(SEPARATOR, separatorLength));
            }
            System.out.format("%" + indentLength + "s\n", generateLine(field, y));
        }
    }

    public boolean move(final Game game) {
        final Field field = game.getField();
        final Figure winner = winnerController.getWinner(field);
        if (winner != null) {
            System.out.format("Winner is: %s\n", winner);
            return false;
        }
        final Figure currentFigure = currentMoveController.currentMove(field);
        if (currentFigure == null) {
            System.out.println("No winner and no moves left!");
            return false;
        }
        System.out.format("Please enter move point for: %s\n", currentFigure);
        final Point point = askPoint();
        try {
            moveController.applyFigure(field, point, currentFigure);
        } catch (final InvalidPointException | AlreadyOccupiedException e) {
            System.out.println("Point is invalid!");
        }
        return true;
    }

    private Point askPoint() {
        return new Point(coordinateReader.askCoordinate("X") - 1, coordinateReader.askCoordinate("Y") - 1);
    }

    protected String generateLine(final Field field,
                                final int y) {
        String resultLine = "";
        try {
            for (int x = 0; x < field.getSize(); x++) {
                Figure figure = null;
                try {
                    figure = field.getFigure(new Point(x, y));
                } catch (final InvalidPointException e) {
                    e.printStackTrace();
                }
                String leftFigureWall = (x != 0 ? "|" : "");
                String figureSymbol = String.format("%s", figure != null ? figure : " ");
                String figureCell = String.format("%s%2s ", leftFigureWall, figureSymbol);
                resultLine = resultLine.concat(figureCell);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return resultLine;
    }

    private String generateSeparator(final Character piece, final int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result = result + piece;
        }
        return result;
    }

}
