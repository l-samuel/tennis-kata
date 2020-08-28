package org.sg.tennis;

public class CalculateCurrentGame extends Calculate {

    @Override
    public void calculate(final Game game) {
        int p1 = game.getP1();
        int p2 = game.getP2();
        Set set = game.getCurrentSet();
        int pointToWin = game.isTieBreak() ? 6 : 3;

        if (isPlayerWinGame(p1, p2, pointToWin)) {
            game.resetCurrentGame();
            set.addSetPlayer1();
            nextCalculation.calculate(game);
        }
        if (isPlayerWinGame(p2, p1, pointToWin)) {
            game.resetCurrentGame();
            set.addSetPlayer2();
            nextCalculation.calculate(game);

        }


    }

    private boolean isPlayerWinGame(int p1, int p2, int points) {
        return (p1 > points) && (p1 - p2) > 1;
    }
}
