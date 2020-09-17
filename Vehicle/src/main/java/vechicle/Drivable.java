package vechicle;

public interface Drivable {
    void start();
    void drive(double distance);
    void turn(String direction, double degrees);
    void stop();
}
