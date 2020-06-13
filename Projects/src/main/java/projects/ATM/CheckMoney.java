package projects.ATM;

/**
 * @author daniel.silas
 */

public class CheckMoney extends Transaction {
    public CheckMoney(Account account) {
        super(account);
    }

    /**
     * This method should return a message with the balance of the account
     *
     * @return The appropriate message
     */

    @Override
    public String execute() {
        return "" + this.account.getBalance();
    }
}
