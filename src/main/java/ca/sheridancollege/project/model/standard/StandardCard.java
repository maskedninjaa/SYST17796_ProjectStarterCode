/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project.model.standard;

import ca.sheridancollege.project.model.Card;

public class StandardCard extends Card implements Comparable{

    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    }

    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    private final Suit suit;
    private final Value value;

    public StandardCard(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Value getValue() {
        return this.value;
    }

    // need to add a compareTo method - wow phoenix
    @Override
    public int compareTo(Object o) {
        StandardCard compareToCard = (StandardCard)o;
        if(compareToCard.getValue() == this.value) {
            return 0;
        } else if(compareToCard.getValue().ordinal() > this.value.ordinal()) {
            return -1;
        } else
            return 1;
    }


}
