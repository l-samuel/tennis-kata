package org.sg.tennis.printer;

public enum Rules {

    DEUCE("deuce", (int p1, int p2) -> p1 == p2 && p2 > 2),
    ADVANTAGE("advantage", (int p1, int p2) -> (p1 > 3 || p2 > 3) && Math.abs(p1 - p2) == 1);

    private final String result;
    private final OperationRule operation;

    Rules(String result, OperationRule operation) {
        this.result = result;
        this.operation = operation;
    }

    public boolean valid(int p1, int p2) {
        return operation.rule(p1, p2);
    }


    public String getResult() {
        return result;
    }
}
