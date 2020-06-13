package projects.airTrafficControlSystem;

/**
 * @author Silas Daniel
 */

public class Main {

    public static void main(String[] args) {

        ATC airTrafficController = new ATC();

        try {
            airTrafficController.addAircraft("Airplane");
        } catch (AlreadyExistsAirCraft alreadyExistsAirCraft) {
            alreadyExistsAirCraft.getMessage();
        }

        try {
            airTrafficController.addAircraft("Helicopter");
        } catch (AlreadyExistsAirCraft alreadyExistsAirCraft) {
            alreadyExistsAirCraft.getMessage();
        }
        try {
            airTrafficController.addAircraft("Drone");
        } catch (AlreadyExistsAirCraft alreadyExistsAirCraft) {
            alreadyExistsAirCraft.getMessage();
        }


        UI_ATC ui_atc = new UI_ATC(airTrafficController);
        ui_atc.setVisible(true);
        airTrafficController.userMenu();
    }
}
