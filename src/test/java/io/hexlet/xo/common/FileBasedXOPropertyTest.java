package io.hexlet.xo.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBasedXOPropertyTest {

    @Test
    public void testGenerateInstance() throws Exception {
        final IXOProperty testInstance = FileBasedXOProperty.generateInstance();
        assertNotNull(testInstance);
    }

    @Test
    public void testGetSeparatorCharacter() throws Exception {
        final Character expectedResult = '~';
        final IXOProperty testInstance = FileBasedXOProperty.generateInstance();

        final Character actualResult = testInstance.getSeparatorCharacter();

        assertEquals(expectedResult, actualResult);
    }

}
