package io.hexlet.xo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    public void testGetName() throws Exception {
        final String inputValue = "Slava";
        final String expectedValue = inputValue;

        final Player player = new Player(inputValue, null);

        final String actualValue = player.getName();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetFigure() throws Exception {
        final Figure inputValue = Figure.X;
        final Figure expectedValue = inputValue;

        final Player player = new Player(null, inputValue);

        final Figure actualValue = player.getFigure();

        assertEquals(expectedValue, actualValue);
    }

}
