package projects.safehome;

/**
 * @author daniel.silas
 */

public class Door {
    private DoorStatus status = DoorStatus.CLOSE;

    public Door() {
    }

    public Door(DoorStatus status) {
        this.status = status;
    }

    public DoorStatus getStatus() {
        return status;
    }

    /**
     * This method should set the status of the door to CLOSE
     */
    public void lockDoor() {
        this.status = DoorStatus.CLOSE;
    }

    /**
     * This method should set the status of the door to OPEN
     */
    public void unlockDoor() {
        this.status = DoorStatus.OPEN;
    }
}
