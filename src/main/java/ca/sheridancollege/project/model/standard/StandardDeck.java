package ca.sheridancollege.project.model.standard;

import ca.sheridancollege.project.model.Deck;

public class StandardDeck extends Deck {

    public StandardDeck() {
        super(52);
        generateDeck();
    }

    @Override
    public void generateDeck() {
        for (StandardDeckCard.Suit s : StandardDeckCard.Suit.values()) {
            for (StandardDeckCard.Value v : StandardDeckCard.Value.values()) {
                StandardDeckCard standardDeckCard = new StandardDeckCard(s, v);
                this.getCards().add(standardDeckCard);
            }
        }
    }
}
