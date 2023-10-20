package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OptionsParserTest {
    @Test
    public void ValidDirections() {
        String[] validArgs = { "f", "b", "r", "l", "f", "b" };
        MoveDirection[] expectedDirections = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD
        };
        assertArrayEquals(expectedDirections, OptionsParser.parse(validArgs));
    }

    @Test
    public void InvalidDirections() {
        String[] invalidArgs = { "a", "x", "y", "z" };
        MoveDirection[] expectedDirections = {};
        assertArrayEquals(expectedDirections, OptionsParser.parse(invalidArgs));
    }

    @Test
    public void ValidAndInvalidDirections() {
        String[] mixedArgs = { "f", "a", "b", "x", "r", "y", "l", "z" };
        MoveDirection[] expectedDirections = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        };
        assertArrayEquals(expectedDirections, OptionsParser.parse(mixedArgs));
    }

    @Test
    public void testParseEmptyArgs() {
        String[] emptyArgs = {};
        MoveDirection[] expectedDirections = {};
        assertArrayEquals(expectedDirections, OptionsParser.parse(emptyArgs));
    }

}








