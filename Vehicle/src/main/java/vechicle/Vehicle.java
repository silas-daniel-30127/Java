package vechicle;

public abstract class Vehicle implements Drivable, Accelerates, Breaks {
    protected double speed = 0.;
    protected double totalDistanceCovered = 0.;
    protected boolean isStarted = false;

    public void accelerate() throws InterruptedException {
        speed += 20;
        Thread.sleep(2000);
    }

    public void break_speed() throws InterruptedException {
        if (speed != 0) {
            speed -= 20;
            Thread.sleep(2000);
            System.out.println("Speed decreased 20 Miles/h");
        }
        if (speed < 0) {
            speed = 0;
            System.out.println("Vehicle stops.");
        }
    }

    public void start() {
        if (isStarted) {
            System.out.println("Vehicle already started.");
        }
        this.isStarted = true;
        System.out.println("Vehicle started");
    }

    public void drive(double distance) {
        if (isStarted) {
            System.out.println("Vehicle is driven");
            this.totalDistanceCovered += distance;
        }
    }

    public void turn(String direction, double degrees) {

    }

    public void stop() {

    }
}
