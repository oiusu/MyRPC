package javacode.demo2.state;

import javacode.demo2.Door2;
import javacode.demo2.DoorState;

/**
 * Created by ChenC on 2016/9/4 0004.
 */
public class DoorOpening extends DoorState {

    public DoorOpening(Door2 door) {
        super(door);
    }

    public DoorState  touch() {

        //return DoorState.CLOSING;
        // 以前是 door.setState(door.CLOSING);
        //door.setState(door.CLOSING);
        return new DoorClosing(door);
    }
}
