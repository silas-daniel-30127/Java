package projects.sensor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Sensor {
    private List<SensorReading> sensorReadings = new ArrayList<>();
    private SENSOR_TYPE type;
    private String id;

    public Sensor(List<SensorReading> sensorReadings, SENSOR_TYPE type, String id) {
        this.sensorReadings = sensorReadings;
        this.type = type;
        this.id = id;
    }

    public void setSensorReadings(List<SensorReading> sensorReadings) {
        this.sensorReadings = sensorReadings;
    }

    public SENSOR_TYPE getType() {
        return type;
    }

    public void setType(SENSOR_TYPE type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sensor(String id, SENSOR_TYPE type) {
        this.type = type;
        this.id = id;
    }

    /**
     * This method should sort the sensorReading list by value
     *
     * @return The sorted list of sensor readings
     */
    public List<SensorReading> getSensorReadingSortedByValue() {
        Collections.sort(this.sensorReadings, new SensorReading.ValueComparator());
        return this.sensorReadings;
    }

    /**
     * This method should sort the sensorReading list by date and time
     *
     * @return The sorted list of sensor readings
     */
    public List<SensorReading> getSensorReadingSortedByDateAndTime() {
        Collections.sort(sensorReadings);
        return this.sensorReadings;
    }

    public List<SensorReading> getSensorReadings() {
        return sensorReadings;
    }

    public String getId() {
        return this.id;
    }

    /**
     * This method should add a new sensor
     *
     * @param sensorReading The sensor to add
     * @return true if the given sensor is added or false if not
     */
    public boolean addSensorReading(SensorReading sensorReading) {
        if (getSensorReadings() != null) {
            this.sensorReadings.add(sensorReading);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(sensorReadings, sensor.sensorReadings) &&
                type == sensor.type &&
                Objects.equals(id, sensor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorReadings, type, id);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorReadings=" + sensorReadings +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}