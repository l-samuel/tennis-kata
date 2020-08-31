package org.sg.tennis.printer;

import org.sg.tennis.Writer;
import org.sg.tennis.game.Game;
import org.sg.tennis.game.Match;
import org.sg.tennis.game.Player;
import org.sg.tennis.game.Set;

import java.util.*;
import java.util.stream.Collectors;

public class Printer implements Writer {

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

    @Override
    public String print(final Match match) {
        String result;
        result = printPlayers(match.getPlayer1(), match.getPlayer2());
        if (match.isInProgress()) {
            List<Set> score = match.getScore();
            score.add(match.getCurrentSet());
            result += printScore(score);
            result += printCurrentGame(match.getCurrentGame());
        } else {
            result += printScore(match.getScore());
        }
        result += printMatchStatus(match);

        return result;
    }

    private String printPlayers(final Player player1, final Player player2) {
        String players = "";
        players += "Player  1 : ";
        players += player1.getName() + LINE_BREAK;
        players += "Player  2 : ";
        players += player2.getName() + LINE_BREAK;
        return players;
    }

    private String printScore(final List<Set> score) {
        String result = "Score : ";
        String collect = score
                .stream()
                .map(this::format)
                .collect(Collectors.joining(" ", "", "\n"));
        result += collect;
        return result;
    }

    private String format(final Set set) {
        return "(" + set.getScorePlayer1() + SEPARATOR_SCORE + set.getScorePlayer2() + ")";
    }


    private String printCurrentGame(final Game currentGame) {
        String result = "Current game status : ";
        Optional<String> specificRule = Arrays.stream(Rules.values())
                .filter((rule) -> rule.valid(currentGame.getPointsP1(), currentGame.getPointsP2()))
                .map(Rules::getResult)
                .findFirst();
        return result + specificRule.orElseGet(() -> printClassicGame(currentGame.getPointsP1(), currentGame.getPointsP2())) + LINE_BREAK;
    }

    private String printClassicGame(final int pointsP1, final int pointsP2) {
        String result = "";
        result += scoreDictionary.get(pointsP1);
        result += SEPARATOR_SCORE;
        result += scoreDictionary.get(pointsP2);
        return result;
    }

    private String printMatchStatus(final Match match) {
        String result = "Match Status : ";
        if (match.isPlayerWin(match.getPlayer1())) {
            result += "Player1 wins";
        } else if (match.isPlayerWin(match.getPlayer2())) {
            result += "Player2 wins";
        } else {
            result += "in progress";
        }
        return result;
    }


}
