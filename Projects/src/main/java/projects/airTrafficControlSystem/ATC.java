package projects.airTrafficControlSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Silas Daniel
 */

public class ATC {
    private List<Aircraft> aircraftList = new ArrayList<>();

    public ATC() {

    }

    /**
     * This method should add an aircraft to the ATC with the given attributes
     *
     * @param id The id of the aircraft
     * @throws AlreadyExistsAirCraft The exception thrown if the aircraft already exists
     */
    public void addAircraft(String id) throws AlreadyExistsAirCraft {
        Aircraft aircraft = new Aircraft(id);

        if (aircraftList.contains(aircraft)) {
            throw new AlreadyExistsAirCraft("Aircraft " + id + " already exists!");
        }

        this.aircraftList.add(aircraft);
        Thread thread = new Thread(aircraft);
        thread.start();
    }

    /**
     * This method should send to the aircraft given aircraftId an atc command thru cmd parameter
     *
     * @param aircraftId The id which refers at aircraft
     * @param cmd        The command to execute
     * @throws NotFoundAirCraft The exception thrown if the aircraft does not exists
     */
    public void sendCommand(String aircraftId, AtcCommand cmd) throws NotFoundAirCraft {

        Aircraft aircraft;
        boolean flag = false;
        for (Aircraft value : aircraftList) {
            if (value.getId().equals(aircraftId)) {
                aircraft = value;
                aircraft.receiveAtcMessage(cmd);
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new NotFoundAirCraft("Aircraft " + aircraftId + " not found!");
        }
    }

    public void showAircraft() {
        System.out.println(aircraftList);
    }

    /**
     * This method should let the user manipulate the Air Traffic Control System
     */
    public void userMenu() {
        System.out.println("Welcome to Air Traffic Control System !");

        Scanner scanner = new Scanner(System.in);
        byte userCommand;
        while (true) {
            showAircraft();
            System.out.println("1: Add Aircraft 2: Send Command 3: Terminate process");
            System.out.print("user: ");
            userCommand = scanner.nextByte();

            switch (userCommand) {
                case 1: {
                    System.out.print("Type aircraft id: ");
                    String id = scanner.next();
                    try {
                        addAircraft(id);
                    } catch (AlreadyExistsAirCraft alreadyExistsAirCraft) {
                        System.out.println(alreadyExistsAirCraft.getMessage());
                    }
                    break;
                }
                case 2: {
                    System.out.println("TAKEOFF {aircraftId} {altitude} / LAND {aircraftId})");
                    String command = scanner.next();
                    if (command.equals("TAKEOFF")) {
                        try {
                            sendCommand(scanner.next(), new TakeoffCommand(scanner.nextInt()));
                        } catch (NotFoundAirCraft notFoundAirCraft) {
                            System.out.println(notFoundAirCraft.getMessage());
                        }
                    } else if (command.equals("LAND")) {
                        try {
                            sendCommand(scanner.next(), new LandCommand());
                        } catch (NotFoundAirCraft notFoundAirCraft) {
                            System.out.println(notFoundAirCraft.getMessage());
                        }
                    } else {
                        System.out.println("Bad command!");
                    }
                    break;
                }
                case 3:
                    return;
                default: {
                    System.out.println("Wrong command!");
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "" + aircraftList;
    }
}