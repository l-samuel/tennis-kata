package org.sg.tennis.calculator;

import org.sg.tennis.game.Match;
import org.sg.tennis.game.Set;

import java.util.List;

public class CalculateSet extends Calculate {

    @Override
    public void calculate(final Match match) {
        Set currentSet = match.getCurrentSet();

        if(isPlayerWinSet(currentSet.getScorePlayer1(), currentSet.getScorePlayer2())
        || isPlayerWinSet(currentSet.getScorePlayer2(), currentSet.getScorePlayer1()) ){
            List<Set> score = match.getScore();
            score.add(currentSet);
            match.resetCurrentSet();
        }
    }

    private boolean isPlayerWinSet(final int scorePlayer1,final int scorePlayer2) {
        return winSet(scorePlayer1, scorePlayer2) || winTieBreak(scorePlayer1, scorePlayer2);
    }

    private boolean winSet(final int scorePlayer1,final int scorePlayer2) {
        return scorePlayer1 > 5 && scorePlayer1 - scorePlayer2 > 1;
    }

    private boolean winTieBreak(final int scorePlayer1,final int scorePlayer2) {
        return scorePlayer1==7 & scorePlayer2==6;
    }
}
