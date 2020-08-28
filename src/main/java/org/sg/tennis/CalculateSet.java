package org.sg.tennis;

import java.util.List;

public class CalculateSet extends Calculate {

    @Override
    public void calculate(Game game) {
        Set currentSet = game.getCurrentSet();
        if(isPlayerWinSet(currentSet.getScorePlayer1(), currentSet.getScorePlayer2())){
            List<Set> score = game.getScore();
            score.add(currentSet);
            game.resetCurrentSet();
            nextCalculation.calculate(game);
        }

        if(isPlayerWinSet(currentSet.getScorePlayer2(), currentSet.getScorePlayer1())){
            List<Set> score = game.getScore();
            score.add(currentSet);
            game.resetCurrentSet();
            nextCalculation.calculate(game);
        }
    }

    private boolean isPlayerWinSet(int scorePlayer1, int scorePlayer2) {
        return scorePlayer1 > 5 && scorePlayer1 - scorePlayer2 > 1;
    }
}