package org.sg.tennis.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Game {

    private final Player player1;
    private final Player player2;
    private int pointsP1 =0;
    private int pointsP2 =0;
//    private CurrentGame currentGame;
    private List<Set> score;
    private Set currentSet = new Set(0, 0);

    public Game(String player1, String player2) {
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        this.score = new ArrayList<>();
        this.score.add(new Set(0, 0));
//        this.currentGame = new CurrentGame(0,0);
    }

    public Game(String namePlayer1, String namePlayer2, int pointsP1, int pointsP2, List<Set> score, Set currentSet) {
        this.player1 = new Player(namePlayer1);
        this.player2 = new Player(namePlayer2);
        this.pointsP1 = pointsP1;
        this.pointsP2 = pointsP2;
        this.score = score;
        this.currentSet = currentSet;
//        this.currentGame = new CurrentGame(pointsP1, pointsP2);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isInProgress() {
        return !isPlayerWin(player1) && !isPlayerWin(player2);
    }

    public boolean isPlayerWin(Player player){
        long winningSet = score.stream()
                .filter(s -> player.equals(player1) ? isPlayerWinSet(s.getScorePlayer1(), s.getScorePlayer2()) : isPlayerWinSet(s.getScorePlayer2(), s.getScorePlayer1()))
                .count();
        return isGameFinished(winningSet);
    }

    private boolean isPlayerWinSet(final int scorePlayer1, final int scorePlayer2) {
        return scorePlayer1 > 5 && scorePlayer1 - scorePlayer2 > 1 || scorePlayer1==7 & scorePlayer2==6 ;
    }

    private boolean isGameFinished(final long winningSet) {
        return winningSet > 2;
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
//        currentGame.addPointPlayer1();
        pointsP1++;
    }

    public void addPointPlayer2() {
//        currentGame.addPointPlayer2();
        pointsP2++;
    }

    public int getPointsP1() {
        return pointsP1;
    }

    public int getPointsP2() {
        return pointsP2;
    }

    public void resetCurrentGame() {
        pointsP1 = 0;
        pointsP2 = 0;
    }

    public void resetCurrentSet(){
        currentSet = new Set(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return pointsP1 == game.pointsP1 &&
                pointsP2 == game.pointsP2 &&
                Objects.equals(player1, game.player1) &&
                Objects.equals(player2, game.player2) &&
                Objects.equals(score, game.score) &&
                Objects.equals(currentSet, game.currentSet);
    }

    @Override
    public String toString() {
        return "Game{" +
                "player=" + player1 +
                ", player2=" + player2 +
                ", pointsP1=" + pointsP1 +
                ", pointsP2=" + pointsP2 +
                ", score=" + score +
                ", currentSet=" + currentSet +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, pointsP1, pointsP2, score, currentSet);
    }

    public boolean isTieBreak() {
        return currentSet.getScorePlayer1()== 6 && currentSet.getScorePlayer2()==6;
    }
}
