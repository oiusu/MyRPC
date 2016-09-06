package javacode.demo2.state;

import javacode.demo2.Door2;
import javacode.demo2.DoorState;

/**
 * Created by ChenC on 2016/9/4 0004.
 */
public class DoorOpen extends DoorState {

    public DoorOpen(Door2 door) {
        super(door);
    }

    public DoorState  touch() {

        return new DoorStayOpen(door);
        // 以前是 door.setState(door.STAYOPEN);
        //door.setState(door.STAYOPEN);
    }
}
