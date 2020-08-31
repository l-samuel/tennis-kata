package org.sg.tennis;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class Game {

    private final Player player ;
    private final Player player2;
    private int pointsP1 =0;
    private int pointsP2 =0;
//    private CurrentGame currentGame;
    private List<Set> score;
    private Set currentSet = new Set(0, 0);

    public Game(String player1, String player2) {
        this.player = new Player(player1);
        this.player2 = new Player(player2);
        this.score = new ArrayList<>();
        this.score.add(new Set(0, 0));
//        this.currentGame = new CurrentGame(0,0);
    }

    public Game(String namePlayer1, String namePlayer2, int pointsP1, int pointsP2, List<Set> score, Set currentSet) {
        this.player = new Player(namePlayer1);
        this.player2 = new Player(namePlayer2);
        this.pointsP1 = pointsP1;
        this.pointsP2 = pointsP2;
        this.score = score;
        this.currentSet = currentSet;
//        this.currentGame = new CurrentGame(pointsP1, pointsP2);
    }

    public String getPlayer1() {
        return player.getName();
    }

    public String getPlayer2() {
        return player2.getName();
    }

    public boolean isInProgress() {

        long winningSet = score.stream()
                .filter(s -> isPlayerWinSet(s.getScorePlayer1(), s.getScorePlayer2()) || isPlayerWinSet(s.getScorePlayer2(), s.getScorePlayer1()))
                .count();

        return !isGameFinished(winningSet);
    }

    private boolean isPlayerWinSet(final int scorePlayer1, final int scorePlayer2) {
        return scorePlayer1 > 5 && scorePlayer1 - scorePlayer2 > 1;
    }

    private boolean isGameFinished(final long winningSet) {
        return winningSet > 2;
    }
//    public CurrentGame getCurrentGame() {
//        return currentGame;
//    }

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
                Objects.equals(player, game.player) &&
                Objects.equals(player2, game.player2) &&
                Objects.equals(score, game.score) &&
                Objects.equals(currentSet, game.currentSet);
    }

    @Override
    public String toString() {
        return "Game{" +
                "player=" + player +
                ", player2=" + player2 +
                ", pointsP1=" + pointsP1 +
                ", pointsP2=" + pointsP2 +
                ", score=" + score +
                ", currentSet=" + currentSet +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, player2, pointsP1, pointsP2, score, currentSet);
    }

    public boolean isTieBreak() {
        return currentSet.getScorePlayer1()== 6 && currentSet.getScorePlayer2()==6;
    }
}
