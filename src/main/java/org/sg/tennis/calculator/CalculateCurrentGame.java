package org.sg.tennis.calculator;

import org.sg.tennis.game.Game;
import org.sg.tennis.game.Match;
import org.sg.tennis.game.Set;

public class CalculateCurrentGame extends Calculate {

    @Override
    public void calculate(final Match match) {
        Game currentGame = match.getCurrentGame();
        int p1 = currentGame.getPointsP1();
        int p2 = currentGame.getPointsP2();
        Set set = match.getCurrentSet();

        if (currentGame.isPlayerWinGame(p1, p2)) {
            set.addSetPlayer1();
            match.startNewGame();
            if(nextCalculation!= null ){
                nextCalculation.calculate(match);
            }
        }

        if (currentGame.isPlayerWinGame(p2, p1)) {
            set.addSetPlayer2();
            match.startNewGame();
            if(nextCalculation!= null ){
                nextCalculation.calculate(match);
            }
        }
    }
}
