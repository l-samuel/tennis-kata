package org.sg.tennis;

public class TennisGame {

    public Game init(String player1, String player2) {
        return new Game(player1, player2);
    }

    public void printScore(){

    }

    public void addPointPlayersOne(final Game game) {
        game.addPointPlayer1();
    }

    public void addPointPlayersTwo(final Game game) {
        game.addPointPlayer2();
    }

    public Game calculateScore(final Game game) {
        Calculate calCurrentGame = new CalculateCurrentGame();
        Calculate calSet = new CalculateSet();
        Calculate calculateMatch = new CalculateMatch();
        calCurrentGame.setNextCalculation(calSet);
        calSet.setNextCalculation(calculateMatch);
        calCurrentGame.calculate(game);
        return game;
    }
}
