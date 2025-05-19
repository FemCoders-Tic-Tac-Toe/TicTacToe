package org.example;

public class Board {

    public char[][] board = new char[3][3];

    public Board() {
    }

    public String showBoard() {
        System.out.println("test showBoard");
        return "testString";
    }

    public void updateLastMove (char[][] lastMove) {
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
