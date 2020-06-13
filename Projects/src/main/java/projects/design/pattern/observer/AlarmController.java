package projects.design.pattern.observer;

public class AlarmController implements Observer {
    @Override
    public void update(Object event) {
        System.out.println("Sending SMS message to owner.");
        System.out.println("Received event: Event class:" + event.getClass() + ":" + event.toString());

    }
}
