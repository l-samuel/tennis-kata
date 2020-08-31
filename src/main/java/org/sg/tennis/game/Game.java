package org.sg.tennis.game;

import java.util.Objects;

public class Game {
    private final int POINT_GAME = 3;
    private int pointsP1;
    private int pointsP2;

    public Game(int pointsP1, int pointsP2) {
        this.pointsP1 = pointsP1;
        this.pointsP2 = pointsP2;
    }

    public void addPointPlayer1() {
        pointsP1++;
    }

    public void addPointPlayer2() {
        pointsP2++;
    }

    public int getPointsP1() {
        return pointsP1;
    }

    public int getPointsP2() {
        return pointsP2;
    }

    public boolean isPlayerWinGame(final int p1, final int p2) {
        return (p1 > POINT_GAME) && (p1 - p2) > 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return POINT_GAME == game.POINT_GAME &&
                pointsP1 == game.pointsP1 &&
                pointsP2 == game.pointsP2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(POINT_GAME, pointsP1, pointsP2);
    }

    @Override
    public String toString() {
        return "Game{" +
                "POINT_GAME=" + POINT_GAME +
                ", pointsP1=" + pointsP1 +
                ", pointsP2=" + pointsP2 +
                '}';
    }
}
