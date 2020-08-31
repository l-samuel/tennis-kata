package org.sg.tennis.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Match {

    private final Player player1;
    private final Player player2;
    private List<Set> score;
    private Set currentSet = new Set(0, 0);
    private Game currentGame = new Game(0, 0);

    public Match(final String player1, final String player2) {
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        this.score = new ArrayList<>();
        this.score.add(new Set(0, 0));
    }

    public Match(final String namePlayer1, final String namePlayer2, final int pointsP1, final int pointsP2,
                 final List<Set> score, final Set currentSet) {
        this.player1 = new Player(namePlayer1);
        this.player2 = new Player(namePlayer2);
        this.score = score;
        this.currentSet = currentSet;
        startNewGame(pointsP1, pointsP2);
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

    public boolean isOver() {
        return isPlayerWin(player1) || isPlayerWin(player2);
    }

    public boolean isPlayerWin(Player player) {
        long winningSet = score.stream()
                .filter(s -> player.equals(player1) ? isPlayerWinSet(s.getScorePlayer1(), s.getScorePlayer2()) : isPlayerWinSet(s.getScorePlayer2(), s.getScorePlayer1()))
                .count();
        return isGameFinished(winningSet);
    }

    private boolean isPlayerWinSet(final int scorePlayer1, final int scorePlayer2) {
        return scorePlayer1 > 5 && scorePlayer1 - scorePlayer2 > 1 || scorePlayer1 == 7 & scorePlayer2 == 6;
    }

    private boolean isGameFinished(final long winningSet) {
        return winningSet > 2;
    }

    public List<Set> getScore() {
        return score;
    }

    public Set getCurrentSet() {
        return currentSet;
    }

    public void addPointPlayer1() {
        currentGame.addPointPlayer1();
    }

    public void addPointPlayer2() {
        currentGame.addPointPlayer2();
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void startNewGame() {
        if (isTieBreak()) {
            currentGame = new Tiebreak(0, 0);
        } else {
            currentGame = new Game(0, 0);
        }
    }

    private void startNewGame(int pointsP1, int pointsP2) {
        if (isTieBreak()) {
            currentGame = new Tiebreak(pointsP1, pointsP2);
        } else {
            currentGame = new Game(pointsP1, pointsP2);
        }
    }

    public void resetCurrentSet() {
        currentSet = new Set(0, 0);
    }

    public boolean isTieBreak() {
        return currentSet.getScorePlayer1() == 6 && currentSet.getScorePlayer2() == 6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(player1, match.player1) &&
                Objects.equals(player2, match.player2) &&
                Objects.equals(score, match.score) &&
                Objects.equals(currentSet, match.currentSet) &&
                Objects.equals(currentGame, match.currentGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, score, currentSet, currentGame);
    }

    @Override
    public String toString() {
        return "Match{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", score=" + score +
                ", currentSet=" + currentSet +
                ", currentGame=" + currentGame +
                '}';
    }
}
