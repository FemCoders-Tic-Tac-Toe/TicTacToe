package org.example;

public class Board {
    private final char[][] tableBoard = new char[3][3];

    public Board() {
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                tableBoard[row][col] = '_';
            }
        }
    }

    public String showBoard() {
        StringBuilder boardString = new StringBuilder();
        boardString.append("\t╔═══════════════╗");
        for (int row = 0; row < 3; row++) {
            boardString.append("\n\t║\t");
            for (int col = 0; col < 3; col++) {
                boardString.append(this.tableBoard[row][col]).append("\t");
            }
            boardString.append("║");
        }
        return boardString.append("\n\t╚═══════════════╝").toString();
    }

    public boolean checkMoveAllowed(Player player) {
        int row = player.getLastMove()[0];
        int col = player.getLastMove()[1];
        try {
            if (tableBoard[row][col] == '_') {
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
        if (tableBoard[row][col] == '_') {
            tableBoard[row][col] = player.getToken();
        }
    }

    public boolean isWinner (Player player) {
        char token = player.getToken();
        for (int i = 0; i < 3; i++) {
            if ((tableBoard[i][0] == token && tableBoard[i][1] == token && tableBoard[i][2] == token)||(tableBoard[0][i] == token && tableBoard[1][i] == token && tableBoard[2][i] == token)){
                return true;
            }
        }
        return (tableBoard[0][0] == token && tableBoard[1][1] == token && tableBoard[2][2] == token) || (tableBoard[0][2] == token && tableBoard[1][1] == token && tableBoard[2][0] == token);
    }

    public boolean isFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (this.tableBoard[row][col] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getTableBoard() {
        return tableBoard;
    }
}
