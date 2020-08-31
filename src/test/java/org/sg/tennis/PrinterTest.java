package org.sg.tennis;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sg.tennis.game.Match;
import org.sg.tennis.game.Set;
import org.sg.tennis.printer.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PrinterTest {

    private Printer printer = new Printer();
    private String namePlayer1 = "name player 1";
    private String namePlayer2 = "name player 2";

    @Test
    void should_print_players_name() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(6, 0), new Set(6, 0), new Set(6, 4)), new Set(0, 0));
        //when
        String gamePrinted = printer.print(match);
        //then
        assertThat(gamePrinted).contains("Player  1 : name player 1\n" +
                "Player  2 : name player 2");
    }

    @ParameterizedTest
    @MethodSource("scoreArg")
    void should_print_score(List<Set> score, Set currentSet,String result) {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 0, 0, score, currentSet);
        //when
        String gamePrinted = printer.print(match);
        //then
        assertThat(gamePrinted).contains(result);
    }

    @ParameterizedTest
    @MethodSource("currentGameArg")
    void should_print_current_game_status(int pointsP1, int pointsP2, String result) {
        //given
        Match match = new Match(namePlayer1, namePlayer2, pointsP1, pointsP2, Lists.list(new Set(6, 0), new Set(6, 0), new Set(5, 4)), new Set(1, 0));
        //when
        String gamePrinted = printer.print(match);
        //then
        assertThat(gamePrinted).contains(result);
    }

    @Test
    void should_not_print_current_game_status_if_game_finished() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(6, 0), new Set(6, 0), new Set(6, 4)), new Set(0, 0));
        //when
        String gamePrinted = printer.print(match);
        //then
        assertThat(gamePrinted).doesNotContain("Current game status :");
    }

    @ParameterizedTest
    @MethodSource("matchArg")
    void should_print_match_status(List<Set> score, String result) {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 0, 0, score, new Set(1, 0));
        //when
        String gamePrinted = printer.print(match);
        //then
        assertThat(gamePrinted).contains(result);
    }

    @Test
    void should_print_game() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 3, 4, Lists.list(new Set(6, 0), new Set(6, 0)), new Set(5, 4));
        //when
        String gamePrinted = printer.print(match);
        //then
        assertThat(gamePrinted).isEqualTo("Player  1 : name player 1\n" +
                "Player  2 : name player 2\n" +
                "Score : (6-0) (6-0) (5-4)\n" +
                "Current game status : advantage\n" +
                "Match Status : in progress");
    }


    private static Stream<Arguments> scoreArg() {
        return Stream.of(
                arguments(Lists.list(new Set(6, 1), new Set(7,5)), new Set(1,0), "Score : (6-1) (7-5) (1-0)"),
                arguments(new ArrayList<>(),new Set(0,0), "Score : (0-0)"),
                arguments(Lists.list(new Set(6, 1), new Set(7,5), new Set(2,6),new Set(6,7),new Set(4,6)),new Set(0,0), "Score : (6-1) (7-5) (2-6) (6-7) (4-6)"));
    }

    private static Stream<Arguments> matchArg() {
        return Stream.of(
                arguments(Lists.list(new Set(6, 1), new Set(7,5), new Set(6,0)), "Match Status : Player1 wins"),
                arguments(Lists.list(new Set(1, 0)), "Match Status : in progress"),
                arguments(Lists.list(new Set(1, 6), new Set(6,7), new Set(6,0), new Set(2,6)), "Match Status : Player2 wins"));
    }

    private static Stream<Arguments> currentGameArg() {
        return Stream.of(
                arguments(0,0, "Current game status : 0-0"),
                arguments(1,0, "Current game status : 15-0"),
                arguments(0,1, "Current game status : 0-15"),
                arguments(2,0, "Current game status : 30-0"),
                arguments(3,2, "Current game status : 40-30"),
                arguments(3,3, "Current game status : deuce"),
                arguments(4,4, "Current game status : deuce"),
                arguments(8,8, "Current game status : deuce"),
                arguments(8,8, "Current game status : deuce"),
                arguments(8,9, "Current game status : advantage"),
                arguments(3,4, "Current game status : advantage")
               );
    }
}