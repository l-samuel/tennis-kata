package org.sg.tennis.printer;

import org.sg.tennis.game.Game;
import org.sg.tennis.game.Set;

import java.util.*;
import java.util.stream.Collectors;

public class Printer {

    private static final String SEPARATOR_SCORE = "-";
    public static final String LINE_BREAK = "\n";
    private Map<Integer, String> scoreDictionary;

    public Printer() {
        scoreDictionary = initDictionary();
    }

    private Map<Integer, String> initDictionary() {
        Map<Integer, String> dictionary = new HashMap<>();
        dictionary.put(0, "0");
        dictionary.put(1, "15");
        dictionary.put(2, "30");
        dictionary.put(3, "40");
        return dictionary;
    }

    public String print(final Game game) {
        String result;
        result = printPlayers(game.getPlayer1().getName(), game.getPlayer2().getName());
        result += printScore(game.getScore());
        if (game.isInProgress()) {
            result += printCurrentGame(game.getPointsP1(), game.getPointsP2());
        }
        result += printMatchStatus(game);
        return result;
    }


    private String printPlayers(final String player1, final String player2) {
        String players = "";
        players += "Player  1 : ";
        players += player1 + LINE_BREAK;
        players += "Player  2 : ";
        players += player2 + LINE_BREAK;
        return players;
    }

    private String printScore(final List<Set> score) {
        String result = "";
        result += "Score : ";
        String collect = score
                .stream()
                .map(set -> format(set))
                .collect(Collectors.joining(" ", "", "\n"));
        result += collect;
        return result;
    }

    private String format(final Set set) {
        return "(" + set.getScorePlayer1() + SEPARATOR_SCORE + set.getScorePlayer2() + ")";
    }


    private String printCurrentGame(final int pointsP1, final int pointsP2) {
        String result = "Current game status : ";
        Optional<String> specificRule = Arrays.stream(Rules.values())
                .filter((rule) -> rule.valid(pointsP1, pointsP2))
                .map(Rules::getResult)
                .findFirst();
        return result + specificRule.orElseGet(() -> printClassicGame(pointsP1, pointsP2))+LINE_BREAK;
    }

    private String printClassicGame(int pointsP1, int pointsP2) {
        String result= "";
        result += scoreDictionary.get(pointsP1);
        result += SEPARATOR_SCORE;
        result += scoreDictionary.get(pointsP2);
        return result;
    }

    private String printMatchStatus(final Game game) {
        String result = "Match Status : ";
        if (game.isPlayerWin(game.getPlayer1())) {
            result += "Player1 wins";
        } else if (game.isPlayerWin(game.getPlayer2())) {
            result += "Player2 wins";
        } else {
            result += "in progress";
        }
        return result;
    }
}
