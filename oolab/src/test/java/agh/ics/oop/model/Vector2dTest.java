package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(3, 4);

        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }

    @Test
    public void testToString() {
        Vector2d v = new Vector2d(1, 2);
        assertEquals("(1,2)", v.toString());
    }

    @Test
    public void testPrecedesAndFollows() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 4);

        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v1));

        assertTrue(v2.follows(v1));
        assertFalse(v1.follows(v2));
    }

    @Test
    public void testUpperRightAndLowerLeft() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 4);

        Vector2d upperRight = v1.upperRight(v2);
        Vector2d lowerLeft = v1.lowerLeft(v2);

        assertEquals(new Vector2d(3, 4), upperRight);
        assertEquals(new Vector2d(1, 2), lowerLeft);
    }

    @Test
    public void testAddAndSubtract() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 4);

        Vector2d sum = v1.add(v2);
        Vector2d difference = v1.substract(v2);

        assertEquals(new Vector2d(4, 6), sum);
        assertEquals(new Vector2d(-2, -2), difference);
    }

    @Test
    public void testOpposite() {
        Vector2d v = new Vector2d(1, 2);
        Vector2d opposite = v.opposite();

        assertEquals(new Vector2d(-1, -2), opposite);
    }
}
