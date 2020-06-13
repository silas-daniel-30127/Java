package projects.ATM;

/**
 * @author daniel.silas
 */

public abstract class Transaction {

    protected Account account;

    public Transaction(Account account) {
        this.account = account;
    }

    abstract String execute();
}
