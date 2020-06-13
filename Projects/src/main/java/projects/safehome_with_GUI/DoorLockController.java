package projects.safehome_with_GUI;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daniel.silas
 */

public class DoorLockController implements ControllerInterface {

    private static final String ADD_TENANT_OPERATION = "addTenant";
    private static final String ENTER_PIN_OPERATION = "enterPin";
    private Map<Tenant, AccessKey> validAccess;
    private List<AccessLog> accessLogs;
    private Door door = new Door();
    private int tries = 0;
    private boolean lockTheDoorPermanently = false;

    public DoorLockController() {
        this.validAccess = new HashMap<>();
        this.validAccess.put(new Tenant(MASTER_TENANT_NAME), new AccessKey(MASTER_KEY));
        this.accessLogs = new ArrayList<>();
    }

    public DoorLockController(Map<Tenant, AccessKey> validAccess, List<AccessLog> accessLogs, Door door) {
        this.validAccess = validAccess;
        this.accessLogs = accessLogs;
        this.door = door;
    }

    /**
     * This method should:
     * Lock / unlock door based on pin input
     * If pin is wrong, {@link InvalidPinException} exception will be thrown
     * If 3 consecutive entered pins are wrong, {@link TooManyAttemptsException} exception will be thrown
     * If {@link ControllerInterface#MASTER_KEY} is used as input value, number of retries will be reset to 0 and door will be locked / unlocked based on its current state
     *
     * @param pin - pin value
     * @return The status of the door after pin is entered
     * @throws InvalidPinException      Exception thrown if the entered pin is invalid
     * @throws TooManyAttemptsException Exception thrown if the pin entered too many times wrongly
     */
    @Override
    public DoorStatus enterPin(String pin) throws InvalidPinException, TooManyAttemptsException {
        if (validAccess != null) {
            Tenant tenant = new Tenant();
            AccessKey accessKey = new AccessKey(pin);
            for (Map.Entry<Tenant, AccessKey> entry : validAccess.entrySet()) {
                if (entry.getValue().equals(accessKey)) {
                    tenant = entry.getKey();
                    break;
                }
            }

            if (tenant.getName() == null) {
                tries++;
                System.out.println("Attempt with wrong pin " + tries + ":");
                if (tries < 3) {
                    createAccessLog("NoName", ENTER_PIN_OPERATION, "Operation failed");
                    throw new InvalidPinException("Wrong pin entered.");
                }
                //here we permanently lock the door
                lockTheDoorPermanently = true;
                createAccessLog("NoName", ENTER_PIN_OPERATION, "Operation failed");
                throw new TooManyAttemptsException("Wrong pin entered too many times.");
            } else if (tenant.getName().equals(MASTER_TENANT_NAME)) {
                tries = 0;
                door.unlockDoor();
                lockTheDoorPermanently = false;
                createAccessLog(MASTER_TENANT_NAME, ENTER_PIN_OPERATION, "Operation finished successfully");
                System.out.println("Entry unlocked");
            } else {
                if (!lockTheDoorPermanently) {
                    if (door.getStatus() == DoorStatus.CLOSE) {
                        door.unlockDoor();
                    } else {
                        door.lockDoor();
                    }
                    createAccessLog(tenant.getName(), ENTER_PIN_OPERATION, "Operation finished successfully");
                } else {
                    tries++;
                    System.out.println("Attempt with good pin " + tries + ":");
                    throw new TooManyAttemptsException("Good pin entered too many times");
                }
            }
        } else {
            System.out.println("Cannot find 'validAccess'");
        }
        return door.getStatus();
    }

    /**
     * This method should add a new tenant with given name and pin to the validAccess map
     *
     * @param pin  - pin to be added in the system
     * @param name - tenant name to be added in the system
     * @throws TenantAlreadyExistsException Exception thrown if the tenant already exists
     */
    @Override
    public void addTenant(String pin, String name) throws TenantAlreadyExistsException {

        Tenant tenant = new Tenant(name);
        AccessKey accessKey = new AccessKey(pin);
        if (validAccess.containsKey(tenant)) {
            createAccessLog(name, ADD_TENANT_OPERATION, "Operation failed");
            throw new TenantAlreadyExistsException("Already existing tenant " + name);
        }

        validAccess.put(tenant, accessKey);
        System.out.println("Tenant " + name + " successfully added");
        createAccessLog(name, ADD_TENANT_OPERATION, "Operation finished successfully");
    }

    /**
     * This method should remove a tenant from the validAccess map
     *
     * @param name - tenant name to be removed
     * @throws TenantNotFoundException Exception thrown if the given tenant does not exist in validAccess map
     */
    @Override
    public void removeTenant(String name) throws TenantNotFoundException {
        Tenant tenant = new Tenant(name);
        if (!this.validAccess.containsKey(tenant)) {
            throw new TenantNotFoundException("Tenant not found");
        } else {
            this.validAccess.remove(tenant);
            System.out.println("Tenant " + name + " removed successfully");
        }
    }

    /**
     * This method should create an access log and write it in a .dat file
     */
    private void createAccessLog(final String name, final String operation, final String errMessage) {
        AccessLog accessLog = new AccessLog(name, LocalDateTime.now(), operation, door.getStatus(), errMessage);
        Timestamp tamp = Timestamp.valueOf(LocalDateTime.now());
        String file = String.format("logs/AccessLog-%d-%d-%d-%d.dat", tamp.getHours(), tamp.getMinutes(), tamp.getSeconds(), tamp.getNanos());

        try (final FileOutputStream fileOutputStream = new FileOutputStream(file);
             final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(accessLog);
        } catch (IOException e) {
            System.out.println("IO exception thrown");
        }
        this.accessLogs.add(accessLog);
    }

    /**
     * This method should print the keys and the values from the validAccess map
     */
    public void print() {
        validAccess.forEach((tenant, accessKey) -> System.out.println("Tenant: " + tenant.getName() + " pin: " + accessKey.getPin()));
    }
}
