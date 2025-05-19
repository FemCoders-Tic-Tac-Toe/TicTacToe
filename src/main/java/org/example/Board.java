package org.example;

import java.util.Arrays;

public class Board {

    public char[][] board = new char[3][3];

    public Board() {
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                board[row][col] = '_';
            }
        }


    }

    public String showBoard() {
        String boardString = " ";
        for (int row = 0; row < 3; row++) {
            boardString += "\t\t";
            for (int col = 0; col < 3; col++) {
                boardString += this.board[row][col] + "\t";
            }
            boardString += "\n";
        }
        System.out.println("test showBoard");
        return boardString;
    }

    public void updateLastMove (Player player ) {
        int row = player.getLastMove()[0];
        int col = player.getLastMove()[1];
        if (board[row][col] == '_') {
            board[row][col] = player.getToken();
        }

        System.out.println("test updateLastMove");
    }

    public boolean isWinner (Player player) {
        char token = player.getToken();
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == token && board[i][1] == token && board[i][2] == token) {
                return true;
            }
            if (board[0][i] == token && board[1][i] == token && board[2][i] == token) {
                return true;
            }
        }
        if (board[0][0] == token && board[1][1] == token && board[2][2] == token){
            return true;
        }
        if (board[0][2] == token && board[1][1] == token && board[2][0] == token){
            return true;
        }
        System.out.println("no hay ganador");
        return false;
    }

    public boolean isFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (this.board[row][col] == '_') {
                    return false;
                }
            }
        }
        System.out.println("si esta lleno es true");
        return true;
    }


    //{'_','_','_'}, {'_','_','_'}, {'_','_','_'};
}
