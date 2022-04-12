package ca.sheridancollege.project.games.war;

import ca.sheridancollege.project.games.Game;
import ca.sheridancollege.project.games.Player;
import ca.sheridancollege.project.model.Card;
import ca.sheridancollege.project.model.standard.StandardCard;
import ca.sheridancollege.project.model.standard.StandardDeck;

import java.util.ArrayList;
import java.util.List;

public class WarGame extends Game {

    private int roundNumber = 1;
    WarPlayer winner; // need this variable to declare a winner

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
        if(roundNumber == 1) {
            StandardDeck deck = new StandardDeck();
            StandardDeck shuffledDeck = deck.shuffle(); // shuffles the deck so it's out of order
            // SPLIT THE DECK
            List<Card> playersOneHand =  shuffledDeck.getCards().subList(0,shuffledDeck.getSize()/2); // splits the shuffled deck into one half
            List<Card> playersTwoHand = shuffledDeck.getCards().subList(shuffledDeck.getSize()/2,shuffledDeck.getSize()); // splits the shuffled deck into the second half
            // give first half of deck to player 1
            for(Card card: playersOneHand) {
                player1.receive((StandardCard) card);
            }

            // give second half of deck to player 2
            for(Card card: playersTwoHand) {
                player2.receive((StandardCard) card);
            }
        } else {
            // player 1 will play first
            StandardCard firstPlayersCard = player1.play(); // saving the output of the method, so we can use it to compare
            if(firstPlayersCard == null) {
                // declare winner as player 2 - need to stop execution (return)
                winner = player2;
                declareWinner();
                return;
            }
            // player 2 will play second
            StandardCard secondPlayersCard = player2.play(); // saving the output of the method
            if(secondPlayersCard == null ) {
                // declare winner as player 1
                winner = player1;
                declareWinner();
                return;
            }
            // the cards will then be compared based on rank (value)

            // if player 1 has a lower ranked card, then they lose that round and player 2 receives all the cards
            if(firstPlayersCard.compareTo(secondPlayersCard) < 0) { // need to call compareTo method to compare objects (King > Queen , Ace is lowest)
                // receive method etc.
                player2.receive(firstPlayersCard); // need to call multiple times?
                player2.receive(secondPlayersCard);
                // if player 1 has a higher ranked card, then they win that round and player 1 receives all the cards
            } else if(firstPlayersCard.compareTo(secondPlayersCard) > 0) {
                player1.receive(secondPlayersCard);
                player1.receive(firstPlayersCard);
            } else { // todo SPECIAL CASE - enable WAR
                // if player 1 has the same ranked card as player 2, then something crazy will happen - aka WAR
                System.out.println("Need to add the SPECIAL CASE still....");
            }
        }
        roundNumber++;
    }

    @Override
    public void declareWinner() {
        System.out.println("Congratulations, you are the winner of WAR!");
    }
}
