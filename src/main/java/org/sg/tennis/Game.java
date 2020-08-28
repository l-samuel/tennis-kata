package org.sg.tennis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

final class Game {

    private String player1;
    private String player2;
    private boolean inProgress = true;
    private int p1=0;
    private int p2=0;
    private CurrentGame currentGame;
    private List<Set> score;
    private Set currentSet = new Set(0, 0);

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = new ArrayList<>();
        this.score.add(new Set(0, 0));
        this.currentGame = new CurrentGame(0,0);
    }

    public Game(String player1, String player2, int p1, int p2, List<Set> score, Set currentSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.p1 = p1;
        this.p2 = p2;
        this.score = score;
        this.currentSet = currentSet;
        this.currentGame = new CurrentGame(p1,p2);
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


    public CurrentGame getCurrentGame() {
        return currentGame;
    }

    public List<Set> getScore() {
        return score;
    }

    public void setScore(List<Set> score) {
        this.score = score;
    }

    public Set getCurrentSet() {
        return currentSet;
    }

    public void addPointPlayer1() {
        currentGame.addPointPlayer1();
        p1++;
    }

    public void addPointPlayer2() {
        currentGame.addPointPlayer2();
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
                Objects.equals(currentGame, game.currentGame) &&
                Objects.equals(score, game.score) &&
                Objects.equals(currentSet, game.currentSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, inProgress, p1, p2, currentGame, score, currentSet);
    }
}
