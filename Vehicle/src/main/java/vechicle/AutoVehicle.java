package vechicle;

public class AutoVehicle extends Vehicle implements DisplayOnBoard {
    private final double averageConsumptionPerMile = 9.2;
    private double remainingFuel = 0;
    private int currentGear = 1;

    public double getRemainingFuel() {
        return remainingFuel;
    }

    public void setRemainingFuel(double remainingFuel) {
        this.remainingFuel = remainingFuel;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

    public void refuel(double liters) {
        this.remainingFuel += liters;
        System.out.println("AutoVehicle " + super.getClass().getSimpleName() + " is refueled!");
        double distanceToGo = liters / averageConsumptionPerMile * 100;
        System.out.printf("Distance to go: %.1f miles\n", distanceToGo);
    }

    @Override
    public void accelerate() {
        if (isStarted) {
            if (remainingFuel > 0) {
                try {
                    super.accelerate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("AutoVehicle " + super.getClass().getSimpleName() + " is accelerating.");
            } else {
                System.out.println("AutoVehicle " + super.getClass().getSimpleName() + " needs to be refueled.");
            }
        } else System.out.println("AutoVehicle not started");
    }

    @Override
    public void break_speed() {
        try {
            super.break_speed();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void drive(double distance) {
        super.drive(distance);
        double remainingDistanceToCover = remainingFuel / averageConsumptionPerMile * 100;
        if (remainingDistanceToCover >= distance) {
            remainingFuel -= distance / 100 * averageConsumptionPerMile;
            System.out.println("Successfully arrived after: " + distance + " miles");
            remainingDistanceToCover -= distance;
            System.out.printf("Distance to go: %.1f miles\n", remainingDistanceToCover);
        } else {
            System.out.println("Not enough fuel");
        }
    }

    @Override
    public void turn(String direction, double degrees) {
        super.turn(direction, degrees);
        System.out.println("AutoVehicle changed direction to " + direction + " at " + degrees + " degrees");
    }

    @Override
    public void stop() {
        super.stop();
        for (int i = 0; i < this.speed / 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.speed = 0;
        System.out.println("AutoVehicle has stopped");
    }

    public void displayCurrentFuel() {
        System.out.println("Remaining fuel: " + this.remainingFuel);
    }

    public void displayCurrentGear() {
        System.out.println("Current gear: " + this.currentGear);
    }
}
