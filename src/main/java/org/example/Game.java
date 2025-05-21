package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Board board;
    public Player playerX = new Player('X', "Player X");
    public Player playerO = new Player('O', "Player O");
    private Player [] players = {this.playerX,  this.playerO};

    String logo = "\n\t\t\t\t\t╔══════════════════════════════╗\n\t\t\t\t\t        🎮 TIC TAC TOE 🎮      \n\t\t\t\t\t╚══════════════════════════════╝";
    String instructions = "\n\t\t\t\t\t📜 GAME RULES - TIC TAC TOE 📜\n" +
            "\t╔═══════════════════════════════════════════════════════════╗\n" +
            "\t  1️⃣ The game is played by two players on a 3×3 grid.       \n" +
            "\t  2️⃣ You can choose the token you want                      \n" +
            "\t     Otherwise, the default tokens are ❌ and ⭕.           \n" +
            "\t  3️⃣ Players take turns placing their token in empty cells. \n" +
            "\t     ➤ Choose a number for a row (1-3) and a column (1-3)   \n" +
            "\t       separated by a space. Example:  2 2                  \n" +
            "\t  4️⃣ The first to align 3 tokens (↕ ↔ ↖︎↘︎) wins the game.   \n" +
            "\t  5️⃣ If all 9 squares are full and no one has won: TIE 🤝   \n" +
            "\t╚═══════════════════════════════════════════════════════════╝\n";

    String newGameMessage = "\n\t\t\t════ New Game ════\n";
    String askPlayer = "Choose a row (1-3) and a column (1-3): ";
    String tieMessage = "It's a TIE 🤝";
    String winnerMessage = "\uD83C\uDFC6 WINNER is player ";
    String outcomeMessage = "";

    Scanner scan = new Scanner(System.in);

    public Game(){}

    public void startGame(){
        System.out.println(this.logo);
        System.out.println(this.instructions);

        this.askToUpdateTokens();
        this.newGame();
    }

    public void setupNewGame() {
        this.board = new Board();
        this.outcomeMessage = "";
        for(Player player: this.players) {
            player.setRoundsPlayed(0);
        }
    }

    public void newGame(){
        this.setupNewGame();
        System.out.println(this.newGameMessage);
        System.out.println("Games won:");
        for(Player player: this.players) {
            System.out.println("\tPlayer " + player.getName() + ": " + player.getGamesWon());
            player.setRoundsPlayed(0);
        }
        System.out.println("Empty board:");
        System.out.println(this.board.showBoard() + "\n");

        this.newTurn();
        System.out.println(this.outcomeMessage);
        this.askToContinue();
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

    public void updateTokens(){
        System.out.println("The name and token must be unique for each player. ");
        for(int i = 0; i < this.players.length; i++){
            boolean repeatName;
            boolean repeatToken;
            do {
                System.out.print("Player " + (i + 1) + ".\tName: ");
                String name = scan.next();
                this.players[i].setName(name);
                scan.nextLine();

                if(this.players[0].getName().equals(this.players[1].getName())) {
                    System.out.println("Name already taken. Use a different name");
                    repeatName = true;
                    continue;
                }
                repeatName = false;
                do{
                    System.out.print("\t\t\tToken: ");
                    char token = scan.next().charAt(0);
                    this.players[i].setToken(token);
                    scan.nextLine();

                    if(this.players[0].getToken() == this.players[1].getToken()) {
                        System.out.println("Token already taken. Use a different token");
                        repeatToken = true;
                        continue;
                    }
                    repeatToken = false;
                } while (repeatToken);
            } while (repeatName);
        }
    }















    public void newTurn(){
        if (!this.board.isFull()) {
            Player currentPlayer = this.players[0];
            Player nextPlayer = this.players[1];
            this.players[0] = nextPlayer;
            this.players[1] = currentPlayer;
            this.newPlay(currentPlayer);
        } else {
            this.outcomeMessage = this.tieMessage;
        }
    }

    public void newPlay(Player player){
        this.askPlayer(player);
        player.setRoundsPlayed(player.getRoundsPlayed()+1);
        this.board.updateLastMove(player);
        System.out.println(this.board.showBoard());
        if (player.getRoundsPlayed() < 3 ||!this.board.isWinner(player)){
            this.newTurn();
        }else {
            this.outcomeMessage = this.winnerMessage + player.getName();
            player.setGamesWon(player.getGamesWon()+1);
        }
    }

    public void askPlayer(Player player){
        try {
            System.out.print("Player " + player.getName() + ". Round: " + (player.getRoundsPlayed() + 1) + ". " + this.askPlayer);
            int[] position = new int[2];
            for (int i = 0; i < position.length; i++) {
                position[i] = scan.nextInt() - 1;
            }
            scan.nextLine();
            player.setLastMove(position);
            if (!this.board.checkMoveAllowed(player)){
                this.askPlayer(player);
            }
        } catch (InputMismatchException exception){
            System.out.println("Invalid input, choose a number in between 1 and 3");
            scan.nextLine();
            this.askPlayer(player);
        }
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

    public Board getBoard() {
        return board;
    }

    public String getOutcomeMessage() {
        return outcomeMessage;
    }
}
