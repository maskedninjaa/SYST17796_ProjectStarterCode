/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class Deck { // similar to card hand class

    //The group of cards, stored in an ArrayList
    private final int deckSize = 52;//the size of the grouping
    private final Card[] cards = new Card[deckSize];

    public Deck() {
        generateHand();
    }
    private void generateHand() {
        int countCards = 0;

        for(Card.Suit s : Card.Suit.values()) {
            for(Card.Value v : Card.Value.values()) {
                cards[countCards] = (new Card(s, v));
                countCards++;
            }
        }
    }


    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public Card[] getCards() {
        return cards;
    }

    // public void shuffle() { Collections.shuffle(cards); }

    /**
     * @return the size of the group of cards
     */
    // public int getSize() { return size;}

    /**
     * @param size the max size for the group of cards
     */
    // public void setSize(int size) { this.size = size;}
}
