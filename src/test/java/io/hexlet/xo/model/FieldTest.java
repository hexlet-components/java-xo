package io.hexlet.xo.model;

import io.hexlet.xo.model.exceptions.InvalidBoardSizeException;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FieldTest {

    @Test
    public void testGetSize() throws Exception {
        final Field field = new Field(3);

        assertEquals(3, field.getSize());
    }

    @Test
    public void testSetFigure() throws Exception {
        final Field field = new Field(3);
        final Point inputPoint = new Point(0, 0);
        final Figure inputFigure = Figure.O;

        field.setFigure(inputPoint, inputFigure);
        final Figure actualFigure = field.getFigure(inputPoint);

        assertEquals(inputFigure, actualFigure);
    }

    @Test
    public void testGetFigureWhenFigureIsNotSet() throws Exception {
        final Field field = new Field(3);
        final Point inputPoint = new Point(0, 0);

        final Figure actualFigure = field.getFigure(inputPoint);

        assertNull(actualFigure);
    }

    @Test
    public void testGetFigureWhenXIsLessThenZero() throws Exception {
        final Field field = new Field(3);
        final Point inputPoint = new Point(-1, 0);

        try {
            field.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) { }
    }

    @Test
    public void testGetFigureWhenYIsLessThenZero() throws Exception {
        final Field field = new Field(3);
        final Point inputPoint = new Point(0, -1);

        try {
            field.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) { }
    }

    @Test
    public void testGetFigureWhenXIsMoreThenSize() throws Exception {
        final Field field = new Field(3);
        final Point inputPoint = new Point(field.getSize() + 1, 0);

        try {
            field.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) { }
    }

    @Test
    public void testGetFigureWhenYIsMoreThenSize() throws Exception {
        final Field field = new Field(3);
        final Point inputPoint = new Point(0, field.getSize() + 1);

        try {
            field.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) { }
    }

    @Test
    public void testCustomField() throws Exception {
        final int defaultBoardSize = 3;
        Random randomGenerator = new Random();
        for (int i = 100; i > 0; i--) {
            final int fieldSize = randomGenerator.nextInt(50) + defaultBoardSize;
            final Field field = new Field(fieldSize);
            assertEquals(fieldSize, field.getSize());
        }
    }

    @Test
    public void testCustomFieldError() throws Exception {
        assertThrows(InvalidBoardSizeException.class, () -> {
            Random randomGenerator = new Random();
            for (int i = 100; i > 0; i--) {
                final int fieldSize = randomGenerator.nextInt(50) - 50;
                final Field field = new Field(fieldSize);
            }
        });
    }
}
