package business.models;

public class Card {
    public Card(String cardNumber, String cardholderName, String expiryDate, String cvv, boolean isBlocked, CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.isBlocked = isBlocked;
        this.cardType = cardType;
    }

    private String cardNumber;
    private String cardholderName;
    private String expiryDate;
    private String cvv;
    private boolean isBlocked;
    private CardType cardType; // e.g., DEBIT, CREDIT


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Card(String cardNumber, String cardholderName, String expiryDate, String cvv, CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.isBlocked = false; // Cards are usually active by default
        this.cardType = cardType;
    }

    // Getters and Setters

    public void block() {
        this.isBlocked = true;
    }

    public void unblock() {
        this.isBlocked = false;
    }


    // ... other methods as needed (e.g., isValid(), getCardType())
}

