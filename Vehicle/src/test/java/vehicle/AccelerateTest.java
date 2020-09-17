package vehicle;


import org.junit.Test;
import vechicle.Audi;
import vechicle.AutoVehicle;

import static org.junit.Assert.assertEquals;

public class AccelerateTest {
    @Test
    public void accelerateTest() {
        AutoVehicle audi = new Audi("RS6", 30, "Diesel", 350);
        audi.refuel(20);
        audi.start();
        audi.accelerate();
    }
}
