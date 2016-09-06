package javacode.demo2.state;
import javacode.demo2.DoorState;
import javacode.demo2.Door2;
/**
 * Created by ChenC on 2016/9/4 0004.
 */
public class DoorStayOpen extends DoorState {
    public DoorStayOpen(Door2 door) {
        super(door);
    }

    public DoorState  touch() {

        //return DoorState.CLOSING;
        // 以前是 door.setState(door.CLOSING);
        //door.setState(door.CLOSING);
        return new DoorClosing(door);
    }
}
