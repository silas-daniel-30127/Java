package projects.ATM;

/**
 * @author daniel.silas
 */

public class Card extends Account {
    private String cardId;
    private String pin;

    public Card(String owner, double balance, Card card, String cardId, String pin) {
        super(owner, balance, card);
        this.cardId = cardId;
        this.pin = pin;
    }

    public Card(String cardId, String pin) {
        this.cardId = cardId;
        this.pin = pin;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId='" + cardId + '\'' +
                ", pin='" + pin + '\'' +
                "} ";
    }
}
