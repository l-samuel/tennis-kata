package org.sg.tennis;

public  abstract  class Calculate {

    protected Calculate nextCalculation;
    public void setNextCalculation(final Calculate nextCalculation){
        this.nextCalculation = nextCalculation;
    }

    abstract protected void calculate(Game game);
}
