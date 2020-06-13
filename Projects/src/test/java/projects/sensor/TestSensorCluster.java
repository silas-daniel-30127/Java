package projects.sensor;

import org.junit.Test;

import static projects.sensor.SENSOR_TYPE.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

public class TestSensorCluster {

    @Test
    public void TestAddSensorShouldReturnNull() {
        SensorCluster sensorsCluster = new SensorCluster();
    }

    @Test
    public void TestAddSensorShouldReturnNewSensor() {
        SensorCluster sensorsCluster = new SensorCluster(new ArrayList<>());
        sensorsCluster.addSensor("someID", TEMPERATURE);
        assertEquals(1, sensorsCluster.getSensors().size());
        assertEquals("someID", sensorsCluster.getSensors().get(0).getId());
        assertEquals(TEMPERATURE, sensorsCluster.getSensors().get(0).getType());

    }

    @Test
    public void TestAddSensorShouldReturnNullBecauseIdAlreadyExists() {
        SensorCluster sensorsCluster = new SensorCluster(new ArrayList<>());
        sensorsCluster.addSensor("someID", TEMPERATURE);
        assertEquals(1, sensorsCluster.getSensors().size());
        assertEquals("someID", sensorsCluster.getSensors().get(0).getId());
        assertEquals(TEMPERATURE, sensorsCluster.getSensors().get(0).getType());
        assertNull(sensorsCluster.addSensor("someID", HUMIDITY));


    }

    @Test
    public void Test() {
        SensorCluster sensorsCluster = new SensorCluster(new ArrayList<>());
        sensorsCluster.addSensor("someID", TEMPERATURE);
        assertEquals(1, sensorsCluster.getSensors().size());
        assertEquals("someID", sensorsCluster.getSensors().get(0).getId());
        assertEquals(TEMPERATURE, sensorsCluster.getSensors().get(0).getType());
        sensorsCluster.addSensor("anotherID", HUMIDITY);
        assertEquals(2, sensorsCluster.getSensors().size());
        assertEquals("anotherID", sensorsCluster.getSensors().get(1).getId());
        assertEquals(HUMIDITY, sensorsCluster.getSensors().get(1).getType());


    }
}
