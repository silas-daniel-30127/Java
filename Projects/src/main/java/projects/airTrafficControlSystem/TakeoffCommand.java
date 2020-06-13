package projects.airTrafficControlSystem;

/**
 * @author Silas Daniel
 */

public class TakeoffCommand extends AtcCommand {

    private int altitude;

    public TakeoffCommand(int altitude) {
        this.altitude = altitude;
    }

    public int getAltitude() {
        return altitude;
    }

    @Override
    public void initialize() {
        System.out.println("Taking of at " + this.altitude * 1000 + " meters!");
    }
}
