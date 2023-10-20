package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import java.util.Arrays;


public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] T = new MoveDirection[args.length];
        int i = 0;
        for(String arg : args){
            switch(arg) {
                case "f":
                    T[i] = MoveDirection.FORWARD;
                    i += 1;
                    break;
                case "b":
                    T[i] = MoveDirection.BACKWARD;
                    i += 1;
                    break;
                case "r":
                    T[i] = MoveDirection.RIGHT;
                    i += 1;
                    break;
                case "l":
                    T[i] = MoveDirection.LEFT;
                    i += 1;
                    break;
                default:
                    break;
            }
        }
        return Arrays.copyOfRange(T, 0, i);
    }
}


