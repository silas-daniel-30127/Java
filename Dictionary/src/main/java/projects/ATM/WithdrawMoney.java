package projects.ATM;

/**
 * @author daniel.silas
 */

public class WithdrawMoney extends Transaction {
    private double amount;

    public WithdrawMoney(Account account, double amount) {
        super(account);
        this.amount = amount;
    }

    /**
     * This method should execute withdraw and return message according with the specifications
     *
     * @return The appropriate message
     */
    @Override
    public String execute() {
        if (this.account.getBalance() > this.amount) {
            double balance = this.account.getBalance();
            this.account.setBalance(balance - this.amount);
            return "Withdraw successfully";
        } else {
            return "Not enough money!";
        }
    }
}
