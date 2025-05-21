package org.example;

public class Board {
    private final char[][] board = new char[3][3];

    public Board() {
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                board[row][col] = '_';
            }
        }
    }

    public String showBoard() {
        StringBuilder boardString = new StringBuilder();
        boardString.append("\t╔═══════════════╗");
        for (int row = 0; row < 3; row++) {
            boardString.append("\n\t║\t");
            for (int col = 0; col < 3; col++) {
                boardString.append(this.board[row][col]).append("\t");
            }
            boardString.append("║");
        }
        return boardString.append("\n\t╚═══════════════╝").toString();
    }

    public boolean checkMoveAllowed(Player player) {
        int row = player.getLastMove()[0];
        int col = player.getLastMove()[1];
        try {
            if (board[row][col] == '_') {
                return true;
            } else{
                System.out.println("That space is not empty");
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException exceptionIndex){
            System.out.println("Out of range. Choose a number for a row and column between 1 and 3");
            return false;
        }
    }

    public void updateLastMove (Player player ) {
        int row = player.getLastMove()[0];
        int col = player.getLastMove()[1];
        if (board[row][col] == '_') {
            board[row][col] = player.getToken();
        }
    }

    public boolean isWinner (Player player) {
        char token = player.getToken();
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == token && board[i][1] == token && board[i][2] == token)||(board[0][i] == token && board[1][i] == token && board[2][i] == token)){
                return true;
            }
        }
        return (board[0][0] == token && board[1][1] == token && board[2][2] == token) || (board[0][2] == token && board[1][1] == token && board[2][0] == token);
    }

    public boolean isFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (this.board[row][col] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getBoard() {
        return board;
    }
}
