import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(3, 3, 3);

        // Game loop
while (!game.getGameOver()) {
    Scanner playerInput = new Scanner(System.in);
            game.displayBoard();
            System.out.println("Enter x and y coordinate, and action you wish to take");
            int x = playerInput.nextInt();
            int y = playerInput.nextInt();
            String action = playerInput.next();
            // Get player input for row, col, and action (reveal or flag)
            // For now, just simulate a move (to be replaced with real player input logic)
            game.playerMove(x, y, action);
            game.printdisplayedBoard();

            // Check for win or loss conditions
            if (game.checkWin()) {
                System.out.println("Congratulations! You've won the game.");
                break;
            }
            if (game.checkLoss(x, y)) {
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
            }
        }
    }
}