package org.sg.tennis;

public class CalculateCurrentGame extends Calculate {

    @Override
    public void calculate(final Game game) {
        int p1 = game.getP1();
        int p2 = game.getP2();
        Set set = game.getCurrentSet();
        if(isPlayerWinGame(p1,p2)) {
            game.resetCurrentGame();
            set.addSetPlayer1();
            nextCalculation.calculate(game);
        }
        if(isPlayerWinGame(p2,p1)) {
            game.resetCurrentGame();
            set.addSetPlayer2();
            nextCalculation.calculate(game);
        }
    }

    private boolean isPlayerWinGame(int p1, int p2) {
        return (p1 > 3) && (p1 - p2) > 1;
    }
}
