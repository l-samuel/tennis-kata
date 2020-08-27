package org.sg.tennis;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TennisGameTest {

    TennisGame tennisGame = new TennisGame();

    @Test
    void should_init_a_game() {

        //given
        String players1 = "name player 1";
        String players2 = "name player 2";
        //when
        Game gameInit = tennisGame.init(players1, players2);
        //then
        Game game = new Game("name player 1","name player 2");
        assertThat(gameInit).isEqualTo(game);
    }

    @Test
    void should_add_a_point_for_player_1_to_a_game() {
        //given
        Game game = new Game("name player 1","name player 2");
        //when
        Game game1 = tennisGame.addPoint(game, true);
        //then
        int [] score = {1,0};
        assertThat(game1.getCurrentScore()).isEqualTo(score);
    }

    @Test
    void should_add_a_point_for_player_1_to_a_game_bis() {
        //given
        Game game = new Game("name player 1","name player 2");
        //when
        Game game1 = tennisGame.addPoint(game, true);
        //then
        int [] score = {1,0};
        assertThat(game1.getCurrentScore()).isEqualTo(score);
    }

    @Test
    void should_add_a_point_for_player_2_to_a_game() {
        //given
        Game game = new Game("name player 1","name player 2");
        //when
        Game game1 = tennisGame.addPoint(game, false);
        //then
        int [] score = {0,1};
        assertThat(game1.getCurrentScore()).isEqualTo(score);
    }

    @Test
    void should_add_a_point_for_player_2_to_a_game_bis() {
        //given
        Game game = new Game("name player 1","name player 2",new int[]{1,1});
        //when
        Game game1 = tennisGame.addPoint(game, false);
        //then
        int [] score = {1,2};
        assertThat(game1.getCurrentScore()).isEqualTo(score);
    }
}
