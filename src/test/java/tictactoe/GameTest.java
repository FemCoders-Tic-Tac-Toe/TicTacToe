package tictactoe;
import org.example.Board;
import org.example.Game;
import org.example.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;

    @BeforeEach
    void setUpBeforeEach(){
        System.out.println("Executes before every test");
        game = new Game();
    }

//    @Test
//    void updateTokens(){
//        game.newGame();
//        game.updateTokens();
//        String input ="One";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        boolean one = true;
//        assertTrue(one);
//
//    }
    @Test
    void newGame_InitializesCorrectly() {
        game.newGame();

        assertNotNull(game.getBoard(), "Init board");
        assertEquals("", game.getOutcomeMessage(), "Empty outcome message at start");

        for (Player player : new Player[]{game.playerX, game.playerO}) {
            assertEquals(0, player.getRoundsPlayed(), "Players rounds set at 0");
        }
    }

}
