package projects.ATM;

import java.util.Scanner;

/**
 * @author daniel.silas
 */

public class ATM {
    Card card;
    Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public ATM() {

    }

    /**
     * This method should execute transaction which displays the balance
     */
    public void checkMoney() {
        if (card == null) {
            System.out.println("Card not entered!");
        } else if (bank == null) {
            System.out.println("Bank not found!");
        } else {
            Transaction transaction = new CheckMoney(this.bank.getAccountByCardId(this.card.getCardId()));
            System.out.println(this.bank.executeTransaction(transaction));
        }
    }

    /**
     * This method should execute transaction which changes the pin of entered card and print a message according to transaction
     *
     * @param oldPin The pin to change
     * @param newPin The pin to replace
     */
    public void changePin(String oldPin, String newPin) {
        if (card == null) {
            System.out.println("Card not entered!");
        } else if (bank == null) {
            System.out.println("Bank not found!");
        } else {
            Transaction transaction = new ChangePin(this.bank.getAccountByCardId(this.card.getCardId()), oldPin, newPin);
            System.out.println(this.bank.executeTransaction(transaction));
        }
    }

    /**
     * This method should execute transaction which withdraws given amount and print a message according to transaction
     *
     * @param amount The amount to withdraw
     */
    public void withdraw(double amount) {
        if (card == null) {
            System.out.println("Card not entered!");
        } else if (bank == null) {
            System.out.println("Bank not found!");
        } else {
            Account account = this.bank.getAccountByCardId(this.card.getCardId());
            Transaction transaction = new WithdrawMoney(account, amount);
            System.out.println(this.bank.executeTransaction(transaction));
        }
    }


    /**
     * This method should insert a card in the atm and test it's pin
     *
     * @param card the card inserted by user
     * @param pin  the pin entered by user
     * @return boolean according to equality of pins
     */
    public boolean insertCard(Card card, String pin) {
        if (card.getPin().equals(pin)) {
            System.out.println("Pin ok");
            this.card = card;
            return true;
        } else {
            System.out.println("Wrong pin");
            return false;
        }
    }

    /**
     * This method should remove the inserted card from the atm
     */
    public void removeCard() {
        this.card = null;
    }

    /**
     * This method should print a message according with the specifications
     */
    public void userMenu(Card card) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert your pin");
        String pin = scanner.next();
        if (this.insertCard(card, pin)) {
            boolean loop = true;
            while (loop) {
                System.out.print("Menu: \n" +
                        "1: Withdraw Money\n" +
                        "2: Check Money\n" +
                        "3: Change Pin\n" +
                        "4: Remove Card\n"
                );
                System.out.print("Chosen option: ");
                int chosenOption = scanner.nextInt();
                switch (chosenOption) {
                    case 1:
                        System.out.println("amount to withdraw: ");
                        double amount = scanner.nextDouble();
                        this.withdraw(amount);
                        break;
                    case 2:
                        this.checkMoney();
                        break;
                    case 3:
                        System.out.print("Enter new pin: ");
                        String newPin = scanner.next();
                        this.changePin(this.card.getPin(), newPin);
                        break;
                    case 4:
                        this.removeCard();
                        System.out.println("Thanks");
                        loop = false;
                        break;
                }
            }
        }
    }
}
