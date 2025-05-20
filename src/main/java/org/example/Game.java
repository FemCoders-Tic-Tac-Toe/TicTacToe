package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player playerX = new Player('X', "Player X");
    private Player playerO = new Player('O', "Player O");
    private Player [] players = {this.playerX,  this.playerO};

    String instructions ="Instructions";
    String askPlayer = "Choose a row (1-3) and a column (1-3)";

    Scanner scan = new Scanner(System.in);

    public Game(){}

    public void updateTokens(){
        // test token and name are different for each player
        for(int i = 0; i < this.players.length; i++){
            System.out.print("Name for Player " + (i+1) + ": ");
            String name = scan.next();
            this.players[i].setName(name);
            scan.nextLine();
            System.out.print("Token for Player " + (i+1) + ": ");
            char token = scan.next().charAt(0);
            this.players[i].setToken(token);
            scan.nextLine();
        }
    }

    public void startGame(){
        System.out.println("\t\tStarting TicTacToe");
        System.out.println(this.instructions);

//        this.askToUpdateTokens();
//        this.newGame();
    }
}
