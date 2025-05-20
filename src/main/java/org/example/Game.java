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

        this.askToUpdateTokens();
        this.newGame();
    }

    public void newGame(){
        System.out.println("New Game");
        board = new Board();

        System.out.println("Games won");
        for(Player player: this.players) {
            System.out.println("Player " + player.getName() + ": " + player.getGamesWon());
            player.setRoundsPlayed(0);
        }

        System.out.println(board.showBoard());

        boolean ans = true;
        while (ans){
            Player currentPlayer = this.players[0];
            Player nextPlayer = this.players[1];
//            ans = newRound(this.players[0]);
            this.players[0] = nextPlayer;
            this.players[1] = currentPlayer;
            if (board.isFull()) {
                System.out.println("Tie");
                this.askToContinue();
            }
        }
        Player winner = this.players[1];
        System.out.println("Winner is player " + winner.getName());
        winner.setGamesWon(winner.getGamesWon()+1);
        this.askToContinue();
    }
    public void askToContinue() {
        System.out.print("Do you want to play another game? (y/n) ");
        String response = scan.next().toLowerCase();
        scan.nextLine();
        if (response.equals("yes") || response.equals("y")) {
            System.out.println("Restarting the game...\n");
            this.newGame();
        } else if (response.equals("no") || response.equals("n")) {
            System.out.println("Exiting...");
            scan.close();
            System.exit(0);
        } else {
            System.out.println("Unexpected input");
            this.askToContinue();
        }
    }
    public void askToUpdateTokens() {
        System.out.print("Do you want to choose your personalized tokens? (y/n) ");
        String response = scan.next().toLowerCase();
        scan.nextLine();
        if (response.equals("yes") || response.equals("y")) {
            this.updateTokens();
        } else if (response.equals("no") || response.equals("n")) {
            System.out.println("Default tokens will be used: X and O");
        } else {
            System.out.println("Unexpected input");
            this.askToUpdateTokens();
        }
    }
}
