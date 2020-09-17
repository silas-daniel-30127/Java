package vechicle;

public class Person implements Accelerates, Breaks {
    private final AutoVehicle car;

    public Person(AutoVehicle car) {
        this.car = car;
    }

    public void accelerate() {
        this.car.accelerate();
    }

    public void break_speed() throws InterruptedException {
        this.car.break_speed();
    }
}
