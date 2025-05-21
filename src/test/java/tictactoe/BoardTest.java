package tictactoe;
import org.example.Board;
import org.example.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
                board.getBoard()[row][col] = 'X';
            }
        }
        assertTrue(board.isFull(), "Board is full");
    }

    @Test
    void isFull_WhenBoardIsNotFull_ReturnsFalse() {
        for (int row=0; row<3; row++) {
            for (int col=0; col<3; col++) {
                board.getBoard()[row][col] = 'X';
            }
        }
        board.getBoard()[1][1] = '_';
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

    private static Stream<Arguments> acceptableArg() {
        return Stream.of(
                Arguments.of(new int[]{1, 2}, new int[]{2, 2}, new int[]{1, 0}),
                Arguments.of(new int[]{1, 2}, new int[]{1, 0}, new int[]{0, 2}),
                Arguments.of(new int[]{1, 2}, new int[]{1, 2}, new int[]{1, 2})
        );
    }

    @ParameterizedTest
    @MethodSource("acceptableArg")
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

    public static class BoardArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    arguments(new int[]{1, 1}, new int[]{2, 2}, new int[]{0, 0}, new char[][]{{ 'X', '_','_'}, {'_','X','_'},{'_','_','X'}}),
                    arguments(new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 0}, new char[][]{{ 'X', 'X','X'}, {'_','_','_'},{'_','_','_'}}),
                    arguments(new int[]{0, 1}, new int[]{2, 1}, new int[]{1, 1}, new char[][]{{ '_', 'X','_'}, {'_','X','_'},{'_','X','_'}})
            );
        }
    }

    @DisplayName("Update board")
    @ParameterizedTest
    @ArgumentsSource(BoardArgumentProvider.class)
    void updateBoard_ThreeMoves_ReturnsBoardState(int[] position1, int[] position2, int[] position3, char [][] expected){
        playerX.setLastMove(position1);
        board.updateLastMove(playerX);
        playerX.setLastMove(position2);
        board.updateLastMove(playerX);
        playerX.setLastMove(position3);
        board.updateLastMove(playerX);
        char [][] result = board.getBoard();
        assertArrayEquals(expected, result);
    }
}
