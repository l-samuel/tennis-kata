package org.sg.tennis;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sg.tennis.game.Game;
import org.sg.tennis.game.Match;
import org.sg.tennis.game.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.util.Lists.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TennisGameTest {

    private TennisGame tennisGame = new TennisGame();
    private String namePlayer1 = "name player 1";
    private String namePlayer2 = "name player 2";

    @Test
    void should_init_a_game() {

        //given
        String players1 = "name player 1";
        String players2 = "name player 2";
        //when
        Match matchInit = tennisGame.init(players1, players2);
        //then
        Match match = new Match("name player 1", "name player 2");
        assertThat(matchInit).isEqualTo(match);
    }

    @Test
    void should_add_a_point_for_player_1_to_current_game() {
        //given
        Match match = new Match("name player 1", "name player 2");
        //when
        tennisGame.addPointPlayersOne(match);
        //then
        assertThat(match.getCurrentGame()).isEqualTo(new Game(1,0));
    }

    @Test
    void should_add_a_point_for_player_2_to_current_game() {
        //given
        Match match = new Match("name player 1", "name player 2");
        //when
        tennisGame.addPointPlayersTwo(match);
        //then
        assertThat(match.getCurrentGame()).isEqualTo(new Game(0,1));
    }

    @ParameterizedTest
    @MethodSource("winningGameArg")
    void player_1_win_the_current_game(int p1, int p2) {
        //given
        Match initMatch = new Match(namePlayer1, namePlayer2, p1, p2, emptyScore(), new Set(0, 0));
        //when
        tennisGame.addPointPlayersOne(initMatch);
        Match match = tennisGame.calculateScore(initMatch);

        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, emptyScore(), new Set(1, 0));
        assertThat(match).isEqualTo(expectedMatch);
    }

    @Test
    void player_2_win_the_current_game() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 1, 3, emptyScore(), new Set(0, 0));
        tennisGame.addPointPlayersTwo(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, emptyScore(), new Set(0, 1));
        assertThat(match1).isEqualTo(expectedMatch);
    }

    @Test
    void player_1_win_the_set() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 3, 1, emptyScore(), new Set(5, 0));

        //when
        tennisGame.addPointPlayersOne(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, list(new Set(6,0)), new Set(0, 0));
        assertThat(match1).isEqualTo(expectedMatch);
    }

    @Test
    void next_set_is_tie_break() {
        Match match = new Match(namePlayer1, namePlayer2, 3, 1, emptyScore(), new Set(5, 6));
        //when
        tennisGame.addPointPlayersOne(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, emptyScore(), new Set(6, 6));
        assertThat(match1).isEqualTo(expectedMatch);
        assertThat(match.isTieBreak()).isTrue();
    }

    @Test
    void player_1_win_a_point_in_tieBreak() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 5, 4, emptyScore(), new Set(6, 6));

        //when
        tennisGame.addPointPlayersOne(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 6, 4, emptyScore(), new Set(6, 6));
        assertThat(match1).isEqualTo(expectedMatch);
    }

    @Test
    void player_1_win_the_set_by_tieBreak() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 6, 5, emptyScore(), new Set(6, 6));
        //when
        tennisGame.addPointPlayersOne(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, list(new Set(7, 6)), new Set(0, 0));
        assertThat(match1).isEqualTo(expectedMatch);
    }

    @Test
    void player_2_win_the_set_by_tieBreak() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 10, 11, emptyScore(), new Set(6, 6));
        //when
        tennisGame.addPointPlayersTwo(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, list(new Set(6, 7)), new Set(0, 0));
        assertThat(match1).isEqualTo(expectedMatch);
    }

    @Test
    void player_2_win_the_set() {
        //given
        Match match = new Match(namePlayer1, namePlayer2, 1, 3, emptyScore(), new Set(0, 5));

        //when
        tennisGame.addPointPlayersTwo(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, list(new Set(0, 6)), new Set(0, 0));
        assertThat(match1).isEqualTo(expectedMatch);
    }

    @Test
    void player_1_win_the_match() {
        //given
        List<Set> score = new ArrayList<>();
        score.add(new Set(6, 0));
        score.add(new Set(6, 0));

        Match match = new Match(namePlayer1, namePlayer2, 3, 1, score, new Set(5, 4));
        //when
        tennisGame.addPointPlayersOne(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, list(new Set(6, 0), new Set(6, 0), new Set(6, 4)), new Set(0, 0));
        assertThat(match1).isEqualTo(expectedMatch);
        assertThat(match.isInProgress()).isFalse();
    }

    @Test
    void player_2_win_the_match() {
        //given
        List<Set> score = new ArrayList<>();
        score.add(new Set(0, 6));
        score.add(new Set(5, 7));

        Match match = new Match(namePlayer1, namePlayer2, 3, 4, score, new Set(5, 6));
        //when
        tennisGame.addPointPlayersTwo(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, list(new Set(0, 6), new Set(5, 7), new Set(5, 7)), new Set(0, 0));
        assertThat(match1).isEqualTo(expectedMatch);
        assertThat(match.isInProgress()).isFalse();
    }

    @Test
    void player_2_win_the_match_in_tie_break() {
        //given
        List<Set> score = new ArrayList<>();
        score.add(new Set(0, 6));
        score.add(new Set(5, 7));
        score.add(new Set(7, 6));
        score.add(new Set(6, 0));

        Match match = new Match(namePlayer1, namePlayer2, 10, 11, score, new Set(6, 6));
        //when
        tennisGame.addPointPlayersTwo(match);
        Match match1 = tennisGame.calculateScore(match);
        //then
        Match expectedMatch = new Match(namePlayer1, namePlayer2, 0, 0, list(new Set(0, 6), new Set(5, 7), new Set(7, 6), new Set(6, 0), new Set(6, 7)), new Set(0, 0));
        assertThat(match1).isEqualTo(expectedMatch);
        assertThat(match.isInProgress()).isFalse();
    }

    @Test
    void should_throw_illegal_argument_when_we_add_a_point_to_a_game_finished() {
        //given
        List<Set> score = new ArrayList<>();
        score.add(new Set(6, 0));
        score.add(new Set(6, 0));
        score.add(new Set(6, 0));

        Match match = new Match(namePlayer1, namePlayer2, 0, 0, score, new Set(0, 0));
        //when & then
        assertThatThrownBy(() -> tennisGame.addPointPlayersOne(match))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The game is over");
    }

    @Test
    void should_throw_illegal_argument_when_add_a_point_to_game_finished_player_two() {
        //given
        List<Set> score = new ArrayList<>();
        score.add(new Set(6, 0));
        score.add(new Set(6, 0));
        score.add(new Set(6, 0));

        Match match = new Match(namePlayer1, namePlayer2, 0, 0, score, new Set(0, 0));
        //when & then
        assertThatThrownBy(() -> tennisGame.addPointPlayersTwo(match))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The game is over");
    }

    private static Stream<Arguments> winningGameArg() {
        return Stream.of(arguments(3, 0),
                arguments(5, 4));
    }

    private List<Set> emptyScore() {
        return new ArrayList<>();
    }

}
