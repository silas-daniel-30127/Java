package projects.airTrafficControlSystem;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Silas Daniel
 */

public class Aircraft implements Runnable {
    private final String id;
    private int altitude;
    private States state = States.On_Stand;
    private long cruisingTime = 0;

    public Aircraft(String id) {
        this.id = id;
        this.altitude = 0;
    }

    public String getId() {
        return id;
    }

    /**
     * This method should set the current thread to sleep for 10 seconds
     */
    public void taxing() {
        System.out.println("Taxing: waiting for 10 seconds");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Taxing successfully ended");
    }

    /**
     * This method should set the current thread to sleep for 10 * altitude seconds
     */
    private void ascending() {
        System.out.println("Ascending " + this.id + " at " + this.altitude * 1000 + " m");
        for (int i = 1; i <= altitude; i++) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i * 1000);
        }
    }

    /**
     * This method should set the current thread to sleep for 10 * altitude seconds
     * Descending will be done in 1.0000 meters decrements
     */
    private void descending() {
        System.out.println("Aircraft " + this.id + " is descending from " + this.altitude * 1000 + " m");
        int maxAltitudeDescending = 10;
        while (this.altitude > 0) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.altitude--;
            maxAltitudeDescending--;
            if (maxAltitudeDescending == 0) {
                break;
            }
            System.out.println(altitude * 1000);
        }
        if (this.altitude == 0) {
            System.out.println("Aircraft " + this.id + " has successfully landed!");
        } else if (this.altitude > 0) {
            System.out.println("Aircraft " + this.id + " is 10 000 m lower now, but is still cruising!");
            this.state = States.Cruising;
        }
    }

    /**
     * This method should execute the instance of received atc command given by @param: msg
     *
     * @param msg The command to execute
     */
    public void receiveAtcMessage(AtcCommand msg) {
        synchronized (this.id) {
            if (msg instanceof TakeoffCommand) {
                this.altitude += msg.getAltitude();
                new TakeoffCommand(this.altitude).initialize();
                id.notify();
            } else if (msg instanceof LandCommand && this.state == States.Cruising) {
                new LandCommand().initialize();
                id.notify();
            } else {
                System.out.println("Aircraft " + this.id + " cannot land because it's not in cruising state!");
            }
        }
    }

    /**
     * This method should change the state of Aircraft depending on current state
     */
    @Override
    public void run() {
        long currentTime1 = 0;
        long currentTime2;
        while (this.state != States.Landed) {
            switch (this.state) {
                case On_Stand: {
                    System.out.println("Aircraft " + this.id + " is on stand!");
                    synchronized (this.id) {
                        try {
                            id.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    this.state = States.Taxing;
                    break;
                }
                case Taxing: {
                    this.taxing();
                    this.state = States.Taking_Off;
                    break;
                }
                case Taking_Off: {
                    System.out.println("Taking off " + this.id + " aircraft!");

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    this.state = States.Ascending;
                    break;
                }
                case Ascending: {
                    this.ascending();
                    this.state = States.Cruising;
                    break;
                }
                case Cruising: {
                    currentTime1 = System.currentTimeMillis();
                    System.out.println("Aircraft " + this.id + " is cruising!");
                    synchronized (this.id) {
                        try {
                            id.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    this.state = States.Descending;
                    break;
                }
                case Descending: {
                    this.descending();
                    currentTime2 = System.currentTimeMillis();
                    this.cruisingTime += (currentTime2 - currentTime1) / 1000;
                    if (this.altitude == 0) {
                        this.state = States.Landed;
                        System.out.println("Aircraft " + this.id + " cruising time: " + this.cruisingTime + " sec");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "    id = " + id + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return altitude == aircraft.altitude &&
                cruisingTime == aircraft.cruisingTime &&
                Objects.equals(id, aircraft.id) &&
                state == aircraft.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, altitude, state, cruisingTime);
    }
}
