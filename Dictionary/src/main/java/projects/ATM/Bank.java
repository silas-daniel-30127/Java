package projects.ATM;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Bank {
    private List<Account> accounts;

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;

    }

    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + Arrays.toString(new List[]{accounts}) +
                '}';
    }


    public String executeTransaction(Transaction transaction) {
        return transaction.execute();
    }

    public Account getAccountByCardid(String cardId) {
        for (Account account : this.accounts) {
            if (Objects.equals(account.getCard().getCardId(), cardId)) return (Account) this.accounts;
        }
        return null;
    }

    public Account getAccountByCardId(String cardId) {
        for (Account account : this.accounts) {
            if (account.getCard().getCardId().equals(cardId))
                return account;
        }
        return null;
    }
}