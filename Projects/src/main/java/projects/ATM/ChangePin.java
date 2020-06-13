package projects.ATM;

/**
 * @author daniel.silas
 */

public class ChangePin extends Transaction {

    private String oldPin;
    private String newPin;

    public ChangePin(Account account, String oldPin, String newPin) {
        super(account);
        this.oldPin = oldPin;
        this.newPin = newPin;
    }

    /**
     * This method should set a new pin and return a message according with the specifications
     *
     * @return The appropriate message
     */
    @Override
    public String execute() {
        if (oldPin == null) {
            return "Old pin not found";
        } else if (newPin == null) {
            return "New pin not found";
        } else if (!newPin.equals(oldPin) && this.account.getCard().getPin().equals(oldPin)) {
            super.account.getCard().setPin(newPin);
            return "Pin changed successfully";
        }
        return "The pin was not changed";

    }
}
