package ca.sheridancollege.project;

import java.util.Arrays;
import java.util.Random;

public class TestGame {
    public static void main(String[] args) {

        Deck dk = new Deck();

        Player player1 = new Player("player1"); // how to implement this hmm

        Player player2 = new Player("player2"); // this implemented void() play before and was abstract

        Card[] deck = new Card[52];

        for (int i = 0; i < deck.length; i++){
            Card randomCard = getRandomCardFromDeck(dk);
            for (int j = 0; j < i; j++) {
                if (randomCard.equals(deck[j])) {
                    randomCard = getRandomCardFromDeck(dk);
                    j = -1;
                }
            }
            deck[i] = randomCard;
        }

        Card[] handOne = Arrays.copyOfRange(deck, 0, deck.length/2); // splits the first half of the deck to be given to player1
        Card[] handTwo = Arrays.copyOfRange(deck, 26, deck.length); // splits the second half of the deck to be given to player2

        for(Card hand1 : handOne) {
            System.out.println(hand1);
        }

        System.out.println();

        for(Card hand2 : handTwo) {
            System.out.println(hand2);
        }

        // need to add compareTo for game logic

        // need to assign hands to players once they have been created

        // need to create the game logic - and complete the game until one player has all the cards

    }
    private static Card getRandomCardFromDeck(Deck deck){
        Random random = new Random();
        int randomNumber = random.nextInt(52);
        return deck.getCards()[randomNumber];
    }
}
