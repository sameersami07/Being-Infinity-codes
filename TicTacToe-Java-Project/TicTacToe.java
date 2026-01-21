import java.util.Scanner;

public class TicTacToe {

    static final int SIZE = 3;
    static char[][] board = new char[SIZE][SIZE];
    static Scanner sc = new Scanner(System.in);

    static String player1, player2;
    static char currentSymbol;
    static String currentPlayer;

    // Initialize board
    static void initBoard() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = ' ';
    }

    // Display board
    static void displayBoard() {
        System.out.println("\nCurrent Board:\n");

        System.out.print("   ");
        for (int i = 0; i < SIZE; i++)
            System.out.print(i + "   ");
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");

            System.out.print("  ");
            for (int j = 0; j < SIZE; j++)
                System.out.print("+---");
            System.out.println("+");
        }
    }

    // Switch player
    static void switchPlayer() {
        if (currentSymbol == 'X') {
            currentSymbol = 'O';
            currentPlayer = player2;
        } else {
            currentSymbol = 'X';
            currentPlayer = player1;
        }
    }

    // Check winner
    static boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == currentSymbol &&
                board[i][1] == currentSymbol &&
                board[i][2] == currentSymbol)
                return true;

            if (board[0][i] == currentSymbol &&
                board[1][i] == currentSymbol &&
                board[2][i] == currentSymbol)
                return true;
        }

        if (board[0][0] == currentSymbol &&
            board[1][1] == currentSymbol &&
            board[2][2] == currentSymbol)
            return true;

        if (board[0][2] == currentSymbol &&
            board[1][1] == currentSymbol &&
            board[2][0] == currentSymbol)
            return true;

        return false;
    }

    // Check draw
    static boolean isDraw() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    public static void main(String[] args) {

        System.out.println(" TIC TAC TOE 3x3 GAME ");

        System.out.print("Enter Player 1 name (X): ");
        player1 = sc.nextLine();

        System.out.print("Enter Player 2 name (O): ");
        player2 = sc.nextLine();

        System.out.println("\nGame started between " + player1 + " and " + player2);

        boolean playAgain = true;

        while (playAgain) {

            initBoard();
            currentSymbol = 'X';
            currentPlayer = player1;

            boolean gameOver = false;

            while (!gameOver) {

                displayBoard();
                System.out.println(currentPlayer + "'s turn (" + currentSymbol + ")");

                int r, c;

                while (true) {
                    System.out.print("Enter row and column: ");
                    r = sc.nextInt();
                    c = sc.nextInt();

                    if (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c] == ' ')
                        break;
                    else
                        System.out.println(" Invalid move. Try again.");
                }

                board[r][c] = currentSymbol;

                if (checkWin()) {
                    displayBoard();
                    System.out.println("\n " + currentPlayer + " WINS! ");
                    gameOver = true;
                }
                else if (isDraw()) {
                    displayBoard();
                    System.out.println("\n GAME DRAW!");
                    gameOver = true;
                }
                else {
                    switchPlayer();
                }
            }

            System.out.print("\nPlay again? (yes/no): ");
            sc.nextLine(); // clear buffer
            String ch = sc.nextLine();

            playAgain = ch.equalsIgnoreCase("yes");
        }

        System.out.println("\n Thanks for playing! ");
    }
}
