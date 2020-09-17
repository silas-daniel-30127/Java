package vechicle;

public class Audi extends AutoVehicle {
    protected final String type;
    protected final double fuelTankSize;
    protected final String fuelType;
    protected final double max_speed;

    public Audi(String type, double fuelTankSize, String fuelType, double max_speed) {
        this.type = type;
        this.fuelTankSize = fuelTankSize;
        this.fuelType = fuelType;
        this.max_speed = max_speed;
    }

    public static void main(String[] args) {
        Audi audi_RS6 = new Audi("RS6", 30., "Diesel", 360.);
        Person Daniel = new Person(audi_RS6);
        audi_RS6.refuel(20);
        audi_RS6.start();
        Daniel.accelerate();
        audi_RS6.drive(50);
        try {
            Daniel.break_speed();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        audi_RS6.stop();
    }
}
