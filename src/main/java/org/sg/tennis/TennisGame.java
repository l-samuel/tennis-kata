package org.sg.tennis;

public class TennisGame {

    public Game init(String players1, String players2) {
        return new Game(players1, players2);
    }

    public Game addPoint(Game game, boolean playerOne) {
        int[] currentScore = game.getCurrentScore();
        int[] score;
        score = currentScore;
        if (playerOne) {
            score[0] = score[0] + 1;
        } else {
            score[1] = score[1] + 1;
        }
        return new Game(game.getPlayer1(), game.getPlayer2(), score);
    }
}
