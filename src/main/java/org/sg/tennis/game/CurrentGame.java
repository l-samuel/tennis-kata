package org.sg.tennis.game;

import java.util.Objects;

public class CurrentGame {

    private int p1;
    private int p2;

    public CurrentGame(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public void addPointPlayer1() {
        p1++;
    }

    public void addPointPlayer2() {
        p2++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentGame that = (CurrentGame) o;
        return p1 == that.p1 &&
                p2 == that.p2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }

}
