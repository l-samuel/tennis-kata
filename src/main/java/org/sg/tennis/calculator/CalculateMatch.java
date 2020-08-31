package org.sg.tennis.calculator;

import org.sg.tennis.game.Game;
import org.sg.tennis.game.Set;

import java.util.List;

public class CalculateMatch extends Calculate {

    @Override
    public void calculate(final Game game) {
        List<Set> score = game.getScore();
        long winningSet = score.stream()
                .filter(s -> isPlayerWinSet(s.getScorePlayer1(), s.getScorePlayer2()) || isPlayerWinSet(s.getScorePlayer2(), s.getScorePlayer1()))
                .count();

    }

    private boolean isPlayerWinSet(final int scorePlayer1, final int scorePlayer2) {
        return scorePlayer1 > 5 && scorePlayer1 - scorePlayer2 > 1;
    }

    private boolean isGameFinished(final long winningSet) {
        return winningSet > 2;
    }
}
