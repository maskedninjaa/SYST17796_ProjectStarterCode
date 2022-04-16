/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project.model;

import ca.sheridancollege.project.model.standard.StandardDeck;

import java.util.ArrayList;
import java.util.Collections;
public abstract class Deck {

    //The group of cards, stored in an ArrayList
    private final ArrayList<Card> cards = new ArrayList<>();
    private int size;//the size of the grouping

    public Deck(int size) {
        this.size = size;
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

    public abstract void generateDeck();
}
