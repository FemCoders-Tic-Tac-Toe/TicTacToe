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

    public boolean isWinner () {

        System.out.println("test isWinner");
        return false;
    }

    public boolean isFull() {
        System.out.println("test isFull");
        return true;
    }


    //{'_','_','_'}, {'_','_','_'}, {'_','_','_'};
}
