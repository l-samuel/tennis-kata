package org.sg.tennis;

public class TennisGame {

    public Game init(final String player1, final String player2) {
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
        Calculate calculateCurrentGame = new CalculateCurrentGame();
        Calculate calculateSet = new CalculateSet();
        Calculate calculateMatch = new CalculateMatch();
        calculateCurrentGame.setNextCalculation(calculateSet);
        calculateSet.setNextCalculation(calculateMatch);
        calculateCurrentGame.calculate(game);
        return game;
    }
}
