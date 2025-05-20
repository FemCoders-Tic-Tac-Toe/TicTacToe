package tictactoe;
import org.example.Board;
import org.example.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    private static Stream<Arguments> isWinner_ThreeMoves_ReturnsFalse() {
        return Stream.of(
                Arguments.of(new int[]{1, 2}, new int[]{2, 2}, new int[]{1, 0}),
                Arguments.of(new int[]{1, 2}, new int[]{1, 0}, new int[]{0, 2}),
                Arguments.of(new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2})
        );
    }

    @ParameterizedTest
    @MethodSource()
    void isWinner_ThreeMoves_ReturnsFalse(int[] position1, int[] position2, int[] position3){
        playerX.setLastMove(position1);
        board.updateLastMove(playerX);
        playerX.setLastMove(position2);
        board.updateLastMove(playerX);
        playerX.setLastMove(position3);
        board.updateLastMove(playerX);
        boolean result = board.isWinner(playerX);
        assertFalse(result, "Move 1, 2 not allowed");
    }
}
