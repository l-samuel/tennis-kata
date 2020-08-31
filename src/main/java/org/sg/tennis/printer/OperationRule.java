package org.sg.tennis.printer;

@FunctionalInterface
public interface OperationRule {
    boolean rule(int p1, int p2);
}
