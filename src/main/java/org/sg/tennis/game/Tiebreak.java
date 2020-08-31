package org.sg.tennis.game;

import java.util.Objects;

public class Tiebreak extends Game {
    private static final int POINT_TIE_BREAK = 6;
    private int pointsP1;
    private int pointsP2;

    public Tiebreak(int pointsP1, int pointsP2) {
        super(pointsP1, pointsP2);
    }

    @Override
    public boolean isPlayerWinGame(final int p1, final int p2) {
        return (p1 > POINT_TIE_BREAK) && (p1 - p2) > 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tiebreak tiebreak = (Tiebreak) o;
        return pointsP1 == tiebreak.pointsP1 &&
                pointsP2 == tiebreak.pointsP2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pointsP1, pointsP2);
    }
}
