package ca.sheridancollege.project.games.war;

import ca.sheridancollege.project.games.Player;
import ca.sheridancollege.project.model.standard.StandardCard;

import java.util.ArrayList;

public class WarPlayer extends Player {

    private final ArrayList<StandardCard> hand = new ArrayList<>();

    public WarPlayer(String name) {
        super(name);
    }

    public void receive(StandardCard card) { // this is the player receiving cards to the bottom of their hand (multiple cards - call more than once)
        hand.add(card);  // ok
    }

    @Override
    public StandardCard play() { // this is the player flipping a card from the top of their hand (single card most of the time, but sometimes multiple cards)
        if (!hand.isEmpty()) {
            return hand.remove(0);
        }
        return null;
    }

}
