package agh.ics.oop.model;

import agh.ics.oop.World;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    private RectangularMap rectangularmap;
    @Before
    public RectangularMap setUp(){
        RectangularMap rectangularMap = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(3, 3));
        rectangularMap.place(animal1);
        rectangularMap.place(animal2);
        return rectangularMap;
    }

    @Test
    public void testRectangularMap() {
        rectangularmap = setUp();
        String expectedMapState = " y\\x  0 1 2 3 4 5\n" +
                "  6: -------------\n" +
                "  5: | | | | | | |\n" +
                "  4: | | | | | | |\n" +
                "  3: | | | |^| | |\n" +
                "  2: | | |^| | | |\n" +
                "  1: | | | | | | |\n" +
                "  0: | | | | | | |\n" +
                " -1: -------------\n";
        expectedMapState = expectedMapState.replaceAll("\n", "\r\n");

        assertEquals(expectedMapState, rectangularmap.toString());
    }
    @Test
    void testCanMoveTo() {
        rectangularmap = setUp();
        Vector2d testPosition = new Vector2d(2,2);
        assertTrue(rectangularmap.isOccupied(testPosition));
    }

    @Test
    void testPlace() {
        rectangularmap = setUp();
        Vector2d testPosition = new Vector2d(2,2);
        Animal testAnimal = new Animal(testPosition);
        assertFalse(rectangularmap.place(testAnimal));
    }

    @Test
    void objectAt() {
        rectangularmap = setUp();
        Vector2d testPosition1 = new Vector2d(2,2);
        Vector2d testPosition2 = new Vector2d(2,3);
        WorldElement testAnimal = new Animal(testPosition1);
        assertTrue(testAnimal.equals(rectangularmap.objectAt(testPosition1)));
        assertNull(rectangularmap.objectAt(testPosition2));
    }
    @Test
    void move() {

        rectangularmap = setUp();
        Animal animal1 =  (Animal) rectangularmap.objectAt(new Vector2d(2,2));
        Animal animal2 =  (Animal) rectangularmap.objectAt(new Vector2d(3,3));
        rectangularmap.move(animal1,MoveDirection.FORWARD);
        rectangularmap.move(animal1,MoveDirection.LEFT);
        rectangularmap.move(animal1,MoveDirection.BACKWARD);
        rectangularmap.move(animal2,MoveDirection.RIGHT);
        rectangularmap.move(animal2,MoveDirection.FORWARD);
        rectangularmap.move(animal2,MoveDirection.FORWARD);
        rectangularmap.move(animal2,MoveDirection.FORWARD);
        assertEquals(animal1.getOrientation(),MapDirection.WEST);
        assertEquals(animal1.getPosition(),new Vector2d(2,3));
        assertEquals(animal2.getPosition(),new Vector2d(5,3));
    }
}
