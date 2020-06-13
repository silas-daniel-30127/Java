package projects.sensor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SensorCluster {
    private List<Sensor> sensors = new ArrayList<>();

    public SensorCluster(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public SensorCluster() {
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    /**
     * This method should search for a sensor by Id in sensors list
     *
     * @param sensorId The sensor Id to find
     * @return The found sensor or null if the sensor is not found
     */
    public Sensor getSensorById(String sensorId) {
        for (Sensor sensor : sensors) {
            if (sensor.getId().equals(sensorId)) {
                return sensor;
            }
        }
        return null;
    }

    /**
     * This method should search in the sensors list by given sensorId and add a new value to it
     *
     * @param sensorId The sensor Id to find
     * @param value    The value to add
     * @param dateTime The LocalDateTime to add
     * @return true if the given sensor is found and updated or false if not found
     */
    public boolean writeSensorReading(String sensorId, double value, LocalDateTime dateTime) {

        for (Sensor sensor : this.sensors) {
            if (sensor.getId().equals(sensorId)) {
                SensorReading sensorReading = new SensorReading(dateTime, value);
                sensor.addSensorReading(sensorReading);
                return true;
            }
        }
        return false;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    /**
     * This method should add a new sensor
     *
     * @param sensorId   The Id or the sensor to add
     * @param sensorType The type od the sensor to add
     * @return The instance to a newly created sensor object or null if a sensor with the given id already exists
     */
    public Sensor addSensor(String sensorId, SENSOR_TYPE sensorType) {

        List<Sensor> sensorList = this.sensors;
        for (Sensor sensor : sensorList) {
            if (sensor.getId().equals(sensorId)) {
                return null;
            }
        }
        Sensor sensor = new Sensor(sensorId, sensorType);
        this.sensors.add(sensor);
        return sensor;
    }

    @Override
    public String toString() {
        return "SensorCluster{" +
                "sensors=" + sensors +
                '}';
    }
}
