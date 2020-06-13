package projects.safehome_with_GUI;

/**
 * @author daniel.silas
 */

public class SafeHome {

    public static void main(String[] args) {
        DoorLockController doorLockController = new DoorLockController();

        //..Adding Tenants

        try {
            doorLockController.addTenant("123564", "Daniel");
            doorLockController.addTenant("1234", "Daniel");
        } catch (TenantAlreadyExistsException e) {
            System.out.println("TenantAlreadyExistsException caught");
        }
        try {
            doorLockController.addTenant("23455", "John");
        } catch (TenantAlreadyExistsException e) {
            System.out.println("TenantAlreadyExistsException caught");
        }
        try {
            doorLockController.addTenant("23455", "Robert");
        } catch (TenantAlreadyExistsException e) {
            System.out.println("TenantAlreadyExistsException caught");
        }

        //..Pin entering
        try {
            doorLockController.enterPin("2345");
        } catch (TooManyAttemptsException f) {
            System.out.println("TooManyAttemptsException caught");
        } catch (InvalidPinException e) {
            System.out.println("InvalidPinException caught");
        }
        try {
            doorLockController.enterPin("23");
        } catch (TooManyAttemptsException f) {
            System.out.println("TooManyAttemptsException caught");
        } catch (InvalidPinException e) {
            System.out.println("InvalidPinException caught");
        }
        try {
            doorLockController.enterPin("42");
        } catch (TooManyAttemptsException f) {
            System.out.println("TooManyAttemptsException caught");
        } catch (InvalidPinException e) {
            System.out.println("InvalidPinException caught");
        }

        try {
            doorLockController.enterPin("123564");
        } catch (TooManyAttemptsException f) {
            System.out.println("TooManyAttemptsException caught");
        } catch (InvalidPinException e) {
            System.out.println("InvalidPinException caught");
        }

        //..Master key entering
        try {
            doorLockController.enterPin(ControllerInterface.MASTER_KEY);
        } catch (TooManyAttemptsException f) {
            System.out.println("TooManyAttemptsException caught");
        } catch (InvalidPinException e) {
            System.out.println("InvalidPinException caught");
        }
        try {
            doorLockController.enterPin("123564");
        } catch (TooManyAttemptsException f) {
            System.out.println("TooManyAttemptsException caught");
        } catch (InvalidPinException e) {
            System.out.println("InvalidPinException caught");
        }

        //..Removing tenants
        try {
            doorLockController.removeTenant("Dani");
        } catch (TenantNotFoundException e) {
            System.out.println("TenantNotFoundException caught");
        }
        try {
            doorLockController.removeTenant("Daniel");
        } catch (TenantNotFoundException e) {
            System.out.println("TenantNotFoundException caught");
        }

        //..Displaying tenants
        doorLockController.print();

        //..Displaying access logs
        System.out.println("Access logs:");
        //System.out.println(Arrays.toString(doorLockController.getAccessLogs().toArray()));

        //deserialize
        AccessLogsReader accessLogsReader = new AccessLogsReader();

        accessLogsReader.init();
        accessLogsReader.printAccessLogs();

        SafeHomeUI safeHomeUI = new SafeHomeUI(doorLockController);
        safeHomeUI.setVisible(true);
    }
}

