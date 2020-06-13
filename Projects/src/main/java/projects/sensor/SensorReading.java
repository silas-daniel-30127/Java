package projects.sensor;

import java.time.LocalDateTime;
import java.util.Comparator;

public class SensorReading implements Comparable<SensorReading> {
    private LocalDateTime dateAndTime;
    private double value;

    public SensorReading(LocalDateTime dateAndTime, double value) {
        this.dateAndTime = dateAndTime;
        this.value = value;
    }
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * This method should compare the current object's date and time with the given date and time by SensorReading o
     *
     * @param o The sensorReading to compare with the date and time
     * @return -1 if lower, 0 if equal, 1 if greater: depending on comparision result
     */
    public int compareTo(SensorReading o) {
        return this.getDateAndTime().compareTo(o.getDateAndTime());
    }

    /**
     * This class should implement an Override method compare
     */
    static class ValueComparator implements Comparator<SensorReading> {

        @Override
        public int compare(SensorReading o1, SensorReading o2) {
            if (o1.getValue() - o2.getValue() > 0.01) {
                return 1;
            } else if (o1.getValue() - o2.getValue() < 0.01) {
                return -1;
            }
            return 0;
        }
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "dateAndTime=" + dateAndTime +
                ", value=" + value +
                '}'+'\n';
    }
}
