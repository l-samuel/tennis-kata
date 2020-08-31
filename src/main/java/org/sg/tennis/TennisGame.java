package org.sg.tennis;

import org.sg.tennis.calculator.Calculate;
import org.sg.tennis.calculator.CalculateCurrentGame;
import org.sg.tennis.calculator.CalculateSet;
import org.sg.tennis.game.Match;
import org.sg.tennis.printer.Printer;

public class TennisGame {

    private Writer writer = new Printer();

    public Match init(final String player1, final String player2) {
        return new Match(player1, player2);
    }

    public void printScore(final Match match){
        writer.print(match);
    }

    public void addPointPlayersOne(final Match match) {
        if(match.isOver()) {
            throw new IllegalArgumentException("The game is over");
        }
        match.addPointPlayer1();
    }

    public void addPointPlayersTwo(final Match match) {
        if(match.isOver()) {
            throw new IllegalArgumentException("The game is over");
        }
        match.addPointPlayer2();
    }

    public Match calculateScore(final Match match) {
        Calculate calculateCurrentGame = new CalculateCurrentGame();
        Calculate calculateSet = new CalculateSet();
        calculateCurrentGame.setNextCalculation(calculateSet);
        calculateCurrentGame.calculate(match);
        return match;
    }
}
