package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player playerX = new Player('X', "Player X");
    private Player playerO = new Player('O', "Player O");
    private Player [] players = {this.playerX,  this.playerO};

    String instructions = "\n\t📜 GAME RULES - TIC TAC TOE 📜\n" +
            "\t╔═══════════════════════════════════════════════════════════\n" +
            "\t║ 1️⃣ The game is played by two players on a 3×3 grid.       \n" +
            "\t║ 2️⃣ You can choose the token you want                      \n" +
            "\t║    Otherwise, the default tokens are ❌ and ⭕.           \n" +
            "\t║ 3️⃣ Players take turns placing their token in empty cells. \n" +
            "\t║    ➤ Choose a number for a row (1-3) and a column (1-3)   \n" +
            "\t║      separated by a space. Example:  2 2                  \n" +
            "\t║ 4️⃣ The first to align 3 tokens (↕ ↔ ↖︎↘︎) wins the game.   \n" +
            "\t║ 5️⃣ If all 9 squares are full and no one has won: TIE 🤝   \n" +
            "\t╚═══════════════════════════════════════════════════════════\n";

    String askPlayer = "Choose a row (1-3) and a column (1-3)";

    Scanner scan = new Scanner(System.in);

    public Game(){}

    public void startGame(){
        System.out.println("\t╔══════════════════════════════╗");
        System.out.println("\t║       🎮 TIC TAC TOE 🎮      ║");
        System.out.println("\t╚══════════════════════════════╝");
        System.out.println(this.instructions);

        this.askToUpdateTokens();
        this.newGame();
    }

    public void newGame(){
        System.out.println("New Game");
        this.board = new Board();

        System.out.println("Games won");
        for(Player player: this.players) {
            System.out.println("Player " + player.getName() + ": " + player.getGamesWon());
            player.setRoundsPlayed(0);
        }

        System.out.println(this.board.showBoard());

        boolean newTurn = true;
        while (newTurn){
            Player currentPlayer = this.players[0];
            Player nextPlayer = this.players[1];
            newTurn = newRound(this.players[0]);
            this.players[0] = nextPlayer;
            this.players[1] = currentPlayer;
            if (this.board.isFull()) {
                System.out.println("Tie");
                this.askToContinue();
            }
        }
        Player winner = this.players[1];
        System.out.println("Winner is player " + winner.getName());
        winner.setGamesWon(winner.getGamesWon()+1);
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

    public boolean newRound(Player player){
        this.askPlayer(player);
        if (!this.board.checkMoveAllowed(player)){
            return newRound(player);
        } else{
            player.setRoundsPlayed(player.getRoundsPlayed()+1);
            this.board.updateLastMove(player);
            System.out.println(this.board.showBoard());
            if (player.getRoundsPlayed() < 3){
                return true;
            } else {
                return !this.board.isWinner(player);
            }
        }
    }

    public void askPlayer(Player player){
        try {
            System.out.println("Player " + player.getName() + ". Round: " + (player.getRoundsPlayed() + 1) + ". " + this.askPlayer);
            int[] position = new int[2];
            for (int i = 0; i < position.length; i++) {
                position[i] = scan.nextInt() - 1;
            }
            scan.nextLine();
            player.setLastMove(position);
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
}
