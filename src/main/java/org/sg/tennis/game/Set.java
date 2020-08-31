package org.sg.tennis.game;

import java.util.Objects;

public final class Set {
    private int scorePlayer1;
    private int scorePlayer2;

    public Set(int scorePlayer1, int scorePlayer2) {
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void addSetPlayer1(){
        scorePlayer1++;
    }

    public void addSetPlayer2() {
        scorePlayer2++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return scorePlayer1 == set.scorePlayer1 &&
                scorePlayer2 == set.scorePlayer2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scorePlayer1, scorePlayer2);
    }

    @Override
    public String toString() {
        return "Set{" +
                "scorePlayer1=" + scorePlayer1 +
                ", scorePlayer2=" + scorePlayer2 +
                '}';
    }

    public void reset() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
    }
}
