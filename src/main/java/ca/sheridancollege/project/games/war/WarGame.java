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
        System.out.println("Welcome " + player1.getName() + ", and " + player2.getName());
        players.add(player1);
        players.add(player2);
        setPlayers(players);
        System.out.println("Both players should be ready to play now.");
    }

    @Override
    public boolean play() { // just 1 round , first round of game - arrange the deck, not first round - take card from both players then decide what to do next
        WarPlayer player1 = (WarPlayer) getPlayers().get(0);
        WarPlayer player2 = (WarPlayer) getPlayers().get(1);
        StandardCard firstPlayersCard;
        StandardCard secondPlayersCard;
        System.out.println("Round: " + roundNumber);
        if (roundNumber == 1) {
            System.out.println("Deck is being created!");
            StandardDeck deck = new StandardDeck();
            System.out.println("The deck is now being shuffled!");
            deck.shuffle(); // shuffles the deck so it's out of order
            System.out.println("This is the shuffled deck:" + deck.getCards());
            // SPLIT THE DECK
            System.out.println("The deck is now being split in half");
            List<Card> playersOneHand = deck.getCards().subList(0, deck.getSize() / 2); // splits the shuffled deck into one half
            List<Card> playersTwoHand = deck.getCards().subList(deck.getSize() / 2, deck.getSize()); // splits the shuffled deck into the second half
            // give first half of deck to player 1
            for (Card card : playersOneHand) {
                player1.receive((StandardCard) card);
            }
            System.out.println("Players One deck is:" + playersOneHand);
            // give second half of deck to player 2
            for (Card card : playersTwoHand) {
                player2.receive((StandardCard) card);
            }
            System.out.println("Players Two deck is:" + playersTwoHand);
        } else {
            // player 1 will play first
            firstPlayersCard = player1.play(); // saving the output of the method, so we can use it to compare
            System.out.println("Player 1 has played " + firstPlayersCard);
            if (firstPlayersCard == null) { // declare winner as player 2 - need to stop execution (return)
                winner = player2;
                return true;
            }
            // player 2 will play second
            secondPlayersCard = player2.play(); // saving the output of the method
            System.out.println("Player 2 has played " + secondPlayersCard);
            if (secondPlayersCard == null) { // declare winner as player 1
                winner = player1;
                return true;
            }
            // the cards will then be compared based on rank (value)

            // if player 1 has a lower ranked card, then they lose that round and player 2 receives all the cards
            if (firstPlayersCard.compareTo(secondPlayersCard) < 0) { // need to call compareTo method to compare objects (King > Queen , Ace is lowest)
                // receive method etc.
                player2.receive(firstPlayersCard);
                player2.receive(secondPlayersCard);
                System.out.println("Player 2 has the higher card, and has received both cards.");
                // if player 1 has a higher ranked card, then they win that round and player 1 receives all the cards
            } else if (firstPlayersCard.compareTo(secondPlayersCard) > 0) {
                player1.receive(secondPlayersCard);
                player1.receive(firstPlayersCard);
                System.out.println("Player 1 has the higher card, and has received both cards.");
            } else { // todo SPECIAL CASE - enable WAR
                // if player 1 has the same ranked card as player 2, then something crazy will happen - aka WAR
                // System.out.println("Need to add the SPECIAL CASE still....");
                ArrayList<StandardCard> firstPlayersCards = new ArrayList<>();
                ArrayList<StandardCard> secondPlayersCards = new ArrayList<>();
                System.out.println("Both players have drawn the same card. WAR will take place now."); // same card
                while (firstPlayersCard.compareTo(secondPlayersCard) == 0) { // this is a variation of WAR just to make the code easier to do
                    firstPlayersCard = player1.play(); // saving the output of the method, so we can use it to compare
                    System.out.println("Player 1 has played " + firstPlayersCard);
                    if (firstPlayersCard == null) {
                        winner = player2;
                        return true;
                    }
                    // player 2 will play second
                    secondPlayersCard = player2.play(); // saving the output of the method
                    System.out.println("Player 2 has played " + secondPlayersCard);
                    if (secondPlayersCard == null) {
                        winner = player1;
                        return true;
                    }
                    firstPlayersCards.add(firstPlayersCard);
                    secondPlayersCards.add(secondPlayersCard);
                }
                if (firstPlayersCard.compareTo(secondPlayersCard) < 0) { // need to call compareTo method to compare objects (King > Queen , Ace is lowest)
                    // receive method etc.
                    for (int i = 0; i < firstPlayersCards.size(); i++) {
                        player2.receive(firstPlayersCards.get(i));
                        player2.receive(secondPlayersCards.get(i));
                    }
                    System.out.println("Player 2 has won the WAR round and will now receive all the cards.");
                    // if player 1 has a higher ranked card, then they win that round and player 1 receives all the cards
                } else {
                    for (int i = 0; i < firstPlayersCards.size(); i++) {
                        player1.receive(firstPlayersCards.get(i));
                        player1.receive(secondPlayersCards.get(i));
                    }
                    System.out.println("Player 1 has won the WAR round and will now receive all the cards.");
                }
            }
        }
        roundNumber++;
        return false;
    }
    @Override
    public void declareWinner() {
        System.out.println("Congratulations, " + winner.getName() + ", you have won the game!");
    }
}
