package org.example;

public class Player {
    private char token;
    private int roundsPlayed = 0;
    private char[][] lastMove = new char[3][3];

    public Player(char token) {
        this.token = token;
    }

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public void setRoundsPlayed(int roundsPlayed) {
        this.roundsPlayed = roundsPlayed;
    }

    public char[][] getLastMove() {
        return lastMove;
    }

    public void setLastMove(char[][] lastMove) {
        this.lastMove = lastMove;
    }
}
