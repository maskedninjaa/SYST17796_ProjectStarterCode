package ca.sheridancollege.project;

import ca.sheridancollege.project.games.war.WarGame;
public class Main { // main class to run everything
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to WAR!");
        WarGame game = new WarGame("WAR");
        while(!game.play()) {
            Thread.sleep(500); // a game can last up to 100 rounds give or take
        }
        game.declareWinner();
    }
}
