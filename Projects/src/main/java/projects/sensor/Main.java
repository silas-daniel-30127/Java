package projects.sensor;

import java.time.LocalDateTime;
import java.util.Arrays;

import static projects.sensor.SENSOR_TYPE.*;

public class Main {

    public static void main(String[] args) {
        Sensor[] sensors = new Sensor[10];
        sensors[0] = new Sensor("1", TEMPERATURE);
        sensors[1] = new Sensor("2", PRESSURE);
        sensors[2] = new Sensor("3", HUMIDITY);
        System.out.println(Arrays.toString(sensors));
        SensorCluster sensorCluster = new SensorCluster();
        sensors[4] = sensorCluster.addSensor("1", TEMPERATURE);
        sensors[5] = sensorCluster.addSensor("2", PRESSURE);
        sensors[6] = sensorCluster.addSensor("3", HUMIDITY);
        System.out.println(sensorCluster);

        SensorReading sensorReading1 = new SensorReading(LocalDateTime.of(2020, 2, 10, 16, 45, 33), 100);
        SensorReading sensorReading2 = new SensorReading(LocalDateTime.of(2011, 8, 10, 0, 45, 11), 122);
        SensorReading sensorReading3 = new SensorReading(LocalDateTime.of(2017, 12, 10, 2, 24, 42), 215);

        sensors[0].addSensorReading(sensorReading1);
        sensors[0].addSensorReading(sensorReading2);
        sensors[0].addSensorReading(sensorReading3);

        sensors[3] = sensorCluster.getSensorById("3");

        LocalDateTime localDateTime1 = LocalDateTime.of(1900, 11, 11, 1, 11, 11);
        SensorReading sensorReading4 = new SensorReading(localDateTime1, 111);

        LocalDateTime localDateTime2 = LocalDateTime.of(1867, 6, 2, 22, 12, 54);
        SensorReading sensorReading5 = new SensorReading(localDateTime2, 555);

        sensors[3].addSensorReading(sensorReading4);
        sensors[3].addSensorReading(sensorReading5);

        System.out.println(sensors[0]);
        System.out.println(sensors[1]);
        System.out.println(sensors[2]);

        System.out.println("List sort by date and time:");
        sensors[0].getSensorReadingSortedByDateAndTime();
        System.out.println(sensors[0]);

        System.out.println("List sort by value:");
        sensors[0].getSensorReadingSortedByValue();
        System.out.println(sensors[0]);

        LocalDateTime localDateTime3 = LocalDateTime.of(1402, 2, 3, 12, 22, 3);
        if (sensorCluster.writeSensorReading("1", 20, localDateTime3)) {
            System.out.println("New reading added " + sensors[0]);
        } else {
            System.out.println("New reading failed to add");
        }

    }
}
