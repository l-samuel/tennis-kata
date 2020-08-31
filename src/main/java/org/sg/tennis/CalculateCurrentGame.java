package org.sg.tennis;

public class CalculateCurrentGame extends Calculate {

    public static final int POINT_TIE_BREAK = 6;
    public static final int POINT_GAME = 3;

    @Override
    public void calculate(final Game game) {
        int p1 = game.getPointsP1();
        int p2 = game.getPointsP2();
        Set set = game.getCurrentSet();
        int pointToWin = game.isTieBreak() ? POINT_TIE_BREAK : POINT_GAME;

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

    private boolean isPlayerWinGame(final int p1, final int p2, final int points) {
        return (p1 > points) && (p1 - p2) > 1;
    }
}
