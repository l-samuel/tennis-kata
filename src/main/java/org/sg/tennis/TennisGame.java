package org.sg.tennis;

import org.sg.tennis.calculator.Calculate;
import org.sg.tennis.calculator.CalculateCurrentGame;
import org.sg.tennis.calculator.CalculateMatch;
import org.sg.tennis.calculator.CalculateSet;
import org.sg.tennis.game.Game;
import org.sg.tennis.printer.Printer;

public class TennisGame {

    private Printer printer = new Printer();

    public Game init(final String player1, final String player2) {
        return new Game(player1, player2);
    }

    public void printScore(Game game){
        printer.print(game);
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
