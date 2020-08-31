package org.sg.tennis;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TennisGameTest {

    /*

    Todo :
 - Ajouter controle partie en cours lors de l'ajout de point
 - possibilité de déplacer addPoint dans l'objet game
     */
    private TennisGame tennisGame = new TennisGame();
    private String namePlayer1 = "name player 1";
    private String namePlayer2 = "name player 2";

    @Test
    void should_init_a_game() {

        //given
        String players1 = "name player 1";
        String players2 = "name player 2";
        //when
        Game gameInit = tennisGame.init(players1, players2);
        //then
        Game game = new Game("name player 1", "name player 2");
        assertThat(gameInit).isEqualTo(game);
    }

    @Test
    void should_add_a_point_for_player_1_to_current_game() {
        //given
        Game game = new Game("name player 1", "name player 2");
        //when
        tennisGame.addPointPlayersOne(game);
        //then
        assertThat(game.getPointsP1()).isEqualTo(1);
    }

    @Test
    void should_add_a_point_for_player_2_to_current_game() {
        //given
        Game game = new Game("name player 1", "name player 2");
        //when
        tennisGame.addPointPlayersTwo(game);
        //then
        assertThat(game.getPointsP2()).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("winningGameArg")
    void player_1_win_the_current_game(int p1, int p2) {
        //given
        Game initGame = new Game(namePlayer1, namePlayer2, p1, p2, Lists.emptyList(), new Set(0, 0));
        //when
        tennisGame.addPointPlayersOne(initGame);
        Game game = tennisGame.calculateScore(initGame);

        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.emptyList(), new Set(1, 0));
        assertThat(game).isEqualTo(expectedGame);
    }

    @Test
    void player_2_win_the_current_game() {
        //given
        Game game = new Game(namePlayer1, namePlayer2, 1, 3, Lists.emptyList(), new Set(0, 0));
        tennisGame.addPointPlayersTwo(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.emptyList(), new Set(0, 1));
        assertThat(game1).isEqualTo(expectedGame);
    }

    @Test
    void player_1_win_the_set() {
        //given
        Game game = new Game(namePlayer1, namePlayer2, 3, 1, new ArrayList<>(), new Set(5, 0));

        //when
        tennisGame.addPointPlayersOne(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(6,0)), new Set(0, 0));
        assertThat(game1).isEqualTo(expectedGame);
    }

    @Test
    void next_set_is_tie_break() {
        Game game = new Game(namePlayer1, namePlayer2, 3, 1, new ArrayList<>(), new Set(5, 6));
        //when
        tennisGame.addPointPlayersOne(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.emptyList(), new Set(6, 6));
        assertThat(game1).isEqualTo(expectedGame);
        assertThat(game.isTieBreak()).isTrue();
    }

    @Test
    void player_1_win_a_point_in_tieBreak() {
        //given
        Game game = new Game(namePlayer1, namePlayer2, 5, 4, new ArrayList<>(), new Set(6, 6));

        //when
        tennisGame.addPointPlayersOne(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 6, 4, Lists.emptyList(), new Set(6, 6));
        assertThat(game1).isEqualTo(expectedGame);
    }

    @Test
    void player_1_win_the_set_by_tieBreak() {
        //given
        Game game = new Game(namePlayer1, namePlayer2, 7, 5, new ArrayList<>(), new Set(6, 6));
        //when
        tennisGame.addPointPlayersOne(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(7, 6)), new Set(0, 0));
        assertThat(game1).isEqualTo(expectedGame);
    }

    @Test
    void player_2_win_the_set_by_tieBreak() {
        //given
        Game game = new Game(namePlayer1, namePlayer2, 10, 11, new ArrayList<>(), new Set(6, 6));
        //when
        tennisGame.addPointPlayersTwo(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(6, 7)), new Set(0, 0));
        assertThat(game1).isEqualTo(expectedGame);
    }

    @Test
    void player_2_win_the_set() {
        //given
        Game game = new Game(namePlayer1, namePlayer2, 1, 3, new ArrayList<>(), new Set(0, 5));

        //when
        tennisGame.addPointPlayersTwo(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(0, 6)), new Set(0, 0));
        assertThat(game1).isEqualTo(expectedGame);
    }

    @Test
    void player_1_win_the_match() {
        //given
        List<Set> score = new ArrayList<>();
        score.add(new Set(6, 0));
        score.add(new Set(6, 0));

        Game game = new Game(namePlayer1, namePlayer2, 3, 1, score, new Set(5, 4));
        //when
        tennisGame.addPointPlayersOne(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(6, 0), new Set(6, 0), new Set(6, 4)), new Set(0, 0));
        assertThat(game1).isEqualTo(expectedGame);
        assertThat(game.isInProgress()).isFalse();
    }

    @Test
    void player_2_win_the_match() {
        //given
        List<Set> score = new ArrayList<>();
        score.add(new Set(0, 6));
        score.add(new Set(5, 7));

        Game game = new Game(namePlayer1, namePlayer2, 3, 4, score, new Set(5, 6));
        //when
        tennisGame.addPointPlayersTwo(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(0, 6), new Set(5, 7), new Set(5, 7)), new Set(0, 0));
        assertThat(game1).isEqualTo(expectedGame);
        assertThat(game.isInProgress()).isFalse();
    }

    @Test
    void player_2_win_the_match_in_tie_break() {
        //given
        List<Set> score = new ArrayList<>();
        score.add(new Set(0, 6));
        score.add(new Set(5, 7));
        score.add(new Set(7, 6));
        score.add(new Set(6, 0));

        Game game = new Game(namePlayer1, namePlayer2, 10, 11, score, new Set(6, 6));
        //when
        tennisGame.addPointPlayersTwo(game);
        Game game1 = tennisGame.calculateScore(game);
        //then
        Game expectedGame = new Game(namePlayer1, namePlayer2, 0, 0, Lists.list(new Set(0, 6), new Set(5, 7), new Set(7, 6), new Set(6, 0), new Set(6, 7)), new Set(0, 0));
        assertThat(game1).isEqualTo(expectedGame);
        assertThat(game.isInProgress()).isFalse();
    }

    private static Stream<Arguments> winningGameArg() {
        return Stream.of(arguments(3, 0),
                arguments(5, 4));
    }

}
