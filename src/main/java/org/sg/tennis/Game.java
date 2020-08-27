package org.sg.tennis;

import java.util.Objects;

final class Game {

    private String player1;
    private String player2;
    private boolean inProgress = true;
    private int[] currentScore;

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentScore = new int[]{0, 0};
    }

    public Game(String player1, String player2, int[] currentScore) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentScore = currentScore;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return inProgress == game.inProgress &&
                Objects.equals(player1, game.player1) &&
                Objects.equals(player2, game.player2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, inProgress);
    }

    public int[] getCurrentScore() {
        return currentScore;
    }

}
