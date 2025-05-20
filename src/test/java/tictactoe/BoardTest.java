package tictactoe;
import org.example.Board;
import org.example.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Player playerX;
    Player playerO;
    Board board;

    @BeforeEach
    void setUpBeforeEach(){
        System.out.println("Executes before every test");
        playerX = new Player('X', "Player X");
        playerO = new Player('X', "Player O");
        board = new Board();
    }

    @Test
    void checkMove_1row2col_ReturnsTrue(){

        int[] arr = {1, 2};
        playerX.setLastMove(arr);

        boolean result = board.checkMoveAllowed(playerX);

        assertTrue(result, "Move 1, 2 not allowed");

    }
}
