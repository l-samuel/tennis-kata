package org.sg.tennis.calculator;

import org.sg.tennis.game.Match;

public abstract class Calculate {

    protected Calculate nextCalculation;

    public void setNextCalculation(final Calculate nextCalculation) {
        this.nextCalculation = nextCalculation;
    }

    public abstract void calculate(Match match);
}
