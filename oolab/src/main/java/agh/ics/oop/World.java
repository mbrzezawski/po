package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;


public class World {

    public static void main(String[] arg) {
        System.out.println("System wystartował");
        run(OptionsParser.parse(arg));
        System.out.println("System zakończył działanie");
    }

    public static void run(MoveDirection[] arguments) {
        for (MoveDirection arg : arguments) {
            switch(arg){
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak idzie w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak idzie w lewo");
                    break;
                default:
                    break;
            }
        }
    }
}

