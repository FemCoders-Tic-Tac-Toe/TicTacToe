package tictactoe;
import org.example.Board;
import org.example.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

        int[] position = {1, 2};
        playerX.setLastMove(position);

        boolean result = board.checkMoveAllowed(playerX);

        assertTrue(result, "Move 1, 2 not allowed");

    }

    @Test
    void isFull_WhenBoardIsFull_ReturnsTrue(){
        for (int row=0; row<3; row++) {
            for (int col=0; col<3; col++) {
                board.board[row][col] = 'X';
            }
        }
        assertTrue(board.isFull(), "Board is full");
    }

    @Test
    void isFull_WhenBoardIsNotFull_ReturnsFalse() {
        for (int row=0; row<3; row++) {
            for (int col=0; col<3; col++) {
                board.board[row][col] = 'X';
            }
        }
        board.board[1][1] = '_';
        assertFalse(board.isFull(), "Board has an empty cell");
    }

    @ParameterizedTest
    @CsvSource({
            "0, 2",
            "2, 2"
    })
    void checkMove_TwoIntegers_ReturnsTrue(int row, int col){
        int[] position = {row, col};
        playerX.setLastMove(position);
        boolean result = board.checkMoveAllowed(playerX);
        assertTrue(result, "Move 1, 2 not allowed");
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 2",
            "3, 6"
    })
    void checkMove_TwoIntegers_ReturnsFalse(int row, int col){
        int[] position = {row, col};
        playerX.setLastMove(position);
        boolean result = board.checkMoveAllowed(playerX);
        assertFalse(result, "Move 1, 2 not allowed");
    }
}
