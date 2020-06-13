package projects.design.pattern.observer;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        Alarm fireAlarm = new Alarm();
        AlarmMonitor monitor = new AlarmMonitor();
        fireAlarm.register(monitor);
        AlarmController controller = new AlarmController();
        fireAlarm.register(controller);

        fireAlarm.startAlarm();
        Thread.sleep(500);
        fireAlarm.stopAlarm();
    }
}
