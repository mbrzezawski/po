package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void ValidDirections() {
        String[] validArgs = { "f", "b", "r", "l", "f", "b" };
        List<MoveDirection> expectedDirections = Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD
        );
        List<MoveDirection> parsedDirections = OptionsParser.parse(validArgs);
        assertEquals(expectedDirections, parsedDirections);
    }


    @Test
    public void InvalidDirections() {
        String[] invalidArgs = { "a", "x", "y", "z" };
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(invalidArgs));
    }

    @Test
    public void ValidAndInvalidDirections() {
        String[] mixedArgs = { "f", "a", "b", "x", "r", "y", "l", "z" };
        List<MoveDirection> expectedDirections = Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        );
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(mixedArgs));
    }

    @Test
    public void EmptyDirections() {
        String[] emptyArgs = {};
        List<MoveDirection> parsedDirections = OptionsParser.parse(emptyArgs);
        assertTrue(parsedDirections.isEmpty());
    }
}








