package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board.showBoard());

        Player playerX = new Player('X');
        Player playerY = new Player('Y');

        System.out.println(board.isFull());
        int[] temp = new int[]{0,0};
        int[] tempY = new int[]{1,1};

        playerX.setLastMove(temp);
        playerY.setLastMove(tempY);
        board.updateLastMove(playerX);
        board.updateLastMove(playerY);

        System.out.println(board.showBoard());

        System.out.println(board.isFull());
        board.isWinner(playerY);

    }
}