package ca.sheridancollege.project.model.standard;

import ca.sheridancollege.project.model.Deck;

public class StandardDeck extends Deck {

    public StandardDeck() {
        super(52);
        generateDeck();
    }

    @Override
    public void generateDeck() {
        for (StandardCard.Suit s : StandardCard.Suit.values()) {
            for (StandardCard.Value v : StandardCard.Value.values()) {
                StandardCard standardCard = new StandardCard(s, v);
                this.getCards().add(standardCard);
            }
        }
    }
}
