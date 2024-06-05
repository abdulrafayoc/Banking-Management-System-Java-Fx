package business.services;

import business.models.*;
import persistence.repository.CardRepositoryImpl;

public class CardService {
    private final CardRepositoryImpl cardRepository;

    public CardService(CardRepositoryImpl cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(String cardNumber, String cardholderName, String expiryDate, String cvv, CardType cardType) {
        Card card = new Card(cardNumber, cardholderName, expiryDate, cvv, cardType);
        return cardRepository.save(card);
    }

    // ... other card-related methods (findById, blockCard, etc.)
}