package org.sg.tennis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

final class Game {

    private String player1;
    private String player2;
    private boolean inProgress = true;
    private int[] currentScore;
    private int p1=0;
    private int p2=0;
    private List<Set> score;
    private Set currentSet = new Set(0, 0);

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentScore = new int[]{0, 0};
        this.score = new ArrayList<>();
        this.score.add(new Set(0, 0));
    }

    public Game(String player1, String player2, int p1, int p2, List<Set> score, Set currentSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.p1 = p1;
        this.p2 = p2;
        this.score = score;
        this.currentSet = currentSet;
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

    public int[] getCurrentScore() {
        return currentScore;
    }

    public List<Set> getScore() {
        return score;
    }

    public void setScore(List<Set> score) {
        this.score = score;
    }

    public void setCurrentScore(int[] currentScore) {
        this.currentScore = currentScore;
    }

    public Set getCurrentSet() {
        return currentSet;
    }

    public void addPointPlayer1() {
        p1++;
    }

    public void addPointPlayer2() {
        p2++;
    }

    public int getP1() {
        return p1;
    }

    public int getP2() {
        return p2;
    }

    public void resetCurrentGame() {
        p1 = 0;
        p2 = 0;
    }

    public void resetCurrentSet(){
        currentSet = new Set(0, 0);
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return inProgress == game.inProgress &&
                p1 == game.p1 &&
                p2 == game.p2 &&
                Objects.equals(player1, game.player1) &&
                Objects.equals(player2, game.player2) &&
                Arrays.equals(currentScore, game.currentScore) &&
                Objects.equals(score, game.score) &&
                Objects.equals(currentSet, game.currentSet);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(player1, player2, inProgress, p1, p2, score, currentSet);
        result = 31 * result + Arrays.hashCode(currentScore);
        return result;
    }
}
