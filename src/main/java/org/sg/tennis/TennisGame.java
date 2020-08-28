package org.sg.tennis;

import java.util.List;

public class TennisGame {

    public Game init(String players1, String players2) {
        return new Game(players1, players2);
    }

    public void printScore(){

    }

    public void addPointPlayersOne(final Game game) {
        game.addPointPlayer1();
    }

    public void addPointPlayersTwo(final Game game) {
        game.addPointPlayer2();
    }

    public Game calculateScore(final Game game) {
        int p1 = game.getP1();
        int p2 = game.getP2();
        Set set = game.getCurrentSet();

        if (isPlayerWinGame(p1, p2)) {
            game.resetCurrentGame();
            set.addSetPlayer1();
        }

        if (isPlayerWinGame(p2, p1)) {
            game.resetCurrentGame();
            set.addSetPlayer2();
        }

        Set currentSet = game.getCurrentSet();
        if(isPlayerWinSet(currentSet.getScorePlayer1(), currentSet.getScorePlayer2())){
            List<Set> score = game.getScore();
            score.add(currentSet);
            game.resetCurrentSet();
        }

        if(isPlayerWinSet(currentSet.getScorePlayer2(), currentSet.getScorePlayer1())){
            List<Set> score = game.getScore();
            score.add(currentSet);
            game.resetCurrentSet();
        }

        List<Set> score = game.getScore();
        long winningSet = score.stream().filter(s -> isPlayerWinSet(s.getScorePlayer1(), s.getScorePlayer2()) || isPlayerWinSet(s.getScorePlayer2(),s.getScorePlayer1())).count();
        if(winningSet>2) {
            game.setInProgress(false);
        }

        return game;
    }

    private boolean isPlayerWinSet(int scorePlayer1, int scorePlayer2) {
        return scorePlayer1 > 5 && scorePlayer1 - scorePlayer2 > 1;
    }

    private boolean isPlayerWinGame(int p1, int p2) {
        return (p1 > 3) && (p1 - p2) > 1;
    }
}
