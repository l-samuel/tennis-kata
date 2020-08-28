package org.sg.tennis;

import java.util.List;

public class CalculateMatch extends Calculate {


    @Override
    public void calculate(Game game) {
        List<Set> score = game.getScore();
        long winningSet = score.stream().filter(s -> isPlayerWinSet(s.getScorePlayer1(), s.getScorePlayer2()) || isPlayerWinSet(s.getScorePlayer2(),s.getScorePlayer1())).count();
        if(winningSet>2) {
            game.setInProgress(false);
        }
    }

    private boolean isPlayerWinSet(int scorePlayer1, int scorePlayer2) {
        return scorePlayer1 > 5 && scorePlayer1 - scorePlayer2 > 1;
    }

}
