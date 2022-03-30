package ca.sheridancollege.project.games.war;

import ca.sheridancollege.project.games.Game;
import ca.sheridancollege.project.games.Player;
import ca.sheridancollege.project.model.standard.StandardCard;
import ca.sheridancollege.project.model.standard.StandardDeck;

import java.util.ArrayList;
import java.util.List;

public class WarGame extends Game {

    private int roundNumber = 0;

    public WarGame(String name) {
        super(name);
        ArrayList<Player> players = new ArrayList<>();
        WarPlayer player1 = new WarPlayer("player1");
        WarPlayer player2 = new WarPlayer("player2");
        players.add(player1);
        players.add(player2);
        setPlayers(players);
    }

    @Override
    public void play() { // just 1 round , first round of game - arrange the deck, not first round - take card from both players then decide what to do next
        WarPlayer player1 = (WarPlayer) getPlayers().get(0);
        WarPlayer player2 = (WarPlayer) getPlayers().get(1);
        if(roundNumber == 0) {
            StandardDeck deck = new StandardDeck();
            StandardDeck shuffledDeck = deck.shuffle(); // shuffles the deck so it's out of order
            // SPLIT THE DECK
            StandardDeck firstDeck = (StandardDeck) shuffledDeck.getCards().subList(0,shuffledDeck.getSize()/2); // splits the shuffled deck into one half
            StandardDeck secondDeck = (StandardDeck) shuffledDeck.getCards().subList(shuffledDeck.getSize()/2,shuffledDeck.getSize()); // splits the shuffled deck into the second half
            // give first half of deck to player 1
            player1.receive(firstDeck); // created an abstract method in player called receive so player 1 can receive their half of the deck - thanks to intellij
            // give second half of deck to player 2
            player2.receive(secondDeck);
            // next time
        } else {

            // player 1 will play first
            StandardCard firstPlayersCard = player1.play(); // saving the output of the method, so we can use it to compare
            if(firstPlayersCard == null) {
                // declare winner as player 2 - need to stop execution (return)
                return;
            }
            // player 2 will play second
            StandardCard secondPlayersCard = player2.play(); // saving the output of the method
            if(secondPlayersCard == null ) {
                // declare winner as player 1
                return;
            }
            // the cards will then be compared based on rank (value)
            // if player 1 has a higher ranked card, then they win that round and player 1 receives all the cards
            if(firstPlayersCard.getValue() > secondPlayersCard.getValue()) { // look at this next time as well
                // receive method etc.
                player1.receive(firstPlayersCard); // need to call multiple times?
            }
            // if player 1 has a lower ranked card, then they lose that round and player 2 receives all the cards
            // if player 1 has the same ranked card as player 2, then something crazy will happen


        }
        roundNumber++;


    }

    @Override
    public void declareWinner() {
        // figure this out by myself somehow
    }
}
