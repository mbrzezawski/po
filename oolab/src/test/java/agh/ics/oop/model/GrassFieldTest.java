package agh.ics.oop.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrassFieldTest {
    private GrassField grassfield;
    @Before
    public GrassField setUpOnGrass(){
        return new GrassField(10);
    }
    @Test
    void testCanMoveToOnGrass() {
        grassfield = setUpOnGrass();
        System.out.println(grassfield.toString());
        Vector2d testPosition = new Vector2d(2,2);
        assertFalse(grassfield.isOccupied(testPosition));
    }

    @Test
    void testPlaceOnGrass() {
        grassfield = setUpOnGrass();
        Vector2d testPosition = new Vector2d(2,2);
        Animal testAnimal = new Animal(testPosition);
        assertTrue(grassfield.place(testAnimal));
    }

    @Test
    void objectAtOnGrass() {
        grassfield = setUpOnGrass();
        Vector2d testPosition1 = new Vector2d(2,2);
        Vector2d testPosition2 = new Vector2d(2,3);
        WorldElement testAnimal = new Animal(testPosition1);
        grassfield.place((Animal) testAnimal);
        assertTrue(testAnimal.equals(grassfield.objectAt(testPosition1)));
        assertNull(grassfield.objectAt(testPosition2));
    }
    @Test
    void moveOnGrass() {
        grassfield = setUpOnGrass();
        Animal animal1 =  new Animal(new Vector2d(2,2));
        Animal animal2 =  new Animal(new Vector2d(2,3));
        grassfield.place(animal1);
        grassfield.place(animal2);
        grassfield.move(animal1,MoveDirection.FORWARD);
        grassfield.move(animal1,MoveDirection.LEFT);
        grassfield.move(animal1,MoveDirection.BACKWARD);
        grassfield.move(animal2,MoveDirection.RIGHT);
        grassfield.move(animal2,MoveDirection.FORWARD);
        grassfield.move(animal2,MoveDirection.FORWARD);
        grassfield.move(animal2,MoveDirection.FORWARD);
        assertEquals(animal1.getOrientation(),MapDirection.WEST);
        assertEquals(animal1.getPosition(),new Vector2d(3,2));
        assertEquals(animal2.getPosition(),new Vector2d(5,3));
    }
}
