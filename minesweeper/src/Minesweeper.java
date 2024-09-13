import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
        // Data members
        private char[][] board;   // The game board that will track the number that will be in each cell
        private char[][] displayedBoard; // board that will be revealed to the player
        private boolean[][] mines; // Array to track the locations of mines
        private boolean[][] revealed; // Array to track which cells have been revealed
        private int rows; // Number of rows in the board
        private int cols; // Number of columns in the board
        private int numMines; // Number of mines in the game
        private boolean gameOver; // Boolean to check if the game is over
        private int mineTracker = 0;
        Scanner playerInput = new Scanner(System.in);

        // Constructor to initialize the board with the specified dimensions and number of mines
        public Minesweeper(int rows, int cols, int numMines) {
            this.rows = rows;
            this.cols = cols;
            this.numMines = numMines;
            this.board = new char[rows][cols];
            this.displayedBoard = new char[rows][cols];
            this.mines = new boolean[rows][cols];
            this.revealed = new boolean[rows][cols];
            this.gameOver = false;
            initializeBoard(); // initializes 2d array board and displayed board with a char placeholder
            placeMines();
            calculateNumbers();
            // Call methods to initialize the board and place mines
        }
        public boolean getGameOver(){
            return this.gameOver;
        }
        public void setGameOver(boolean status)
        {
            this.gameOver = status;

        }
        // Method to initialize the game board with empty values
        private void initializeBoard() {
            for(int i = 0; i<rows; i++){
                for(int j = 0; j<cols; j++){
                    displayedBoard[i][j] = (char) '-';
                }
            }
            for(int i = 0; i<rows; i++){
                for(int j = 0; j<cols; j++){
                    board[i][j] = (char) '-';
                }
            }
            // TODO: Implement this method
        }

        // Method to randomly place mines on the board
        private void placeMines() {
            Random minesnum = new Random();
            for(int i = 0; i<rows; i++){
                int x = minesnum.nextInt(rows);
                int y = minesnum.nextInt(cols);
                mines[x][y] = true;
            }
            for(int i = 0; i<rows; i++){
                for(int j = 0; j<cols; j++){
                    if(!mines[i][j] == true) {
                        mines[i][j] = false;
                    }
                }
            }
            // TODO: Implement this method
        }

        // the countPosition methods check if a certain cell has a mine
    // countBottom checks if the cell below array[i][j] has a mine
        private int countBottom(int i, int j){ //
            if(mines[i][j-1]){ //box below
                return 1;
            }
            else
                return 0;
        }
        private int countTop(int i, int j){ //
            if(mines[i][j+1]){ //box above
                return 1;
            }
            else
                return 0;
        }
        private int countLeft(int i, int j){
            if(mines[i-1][j]){ //left box
                return 1;
            }
            else
                return 0;
        }
        private int countRight(int i, int j){
            if (mines[i+1][j]) { //right box
                return 1;
            }
            else {
                return 0;
            }
        }
        private int countDiagonalRightUp(int i, int j){
            if(mines[i+1][j+1]){ //box diagonal up right
                return 1;
            }
            else
                return 0;
        }
        private int countDiagonalRightDown(int i, int j){
            if(mines[i+1][j-1]){ //box diagonal down right
                return 1;
            }
            else
                return 0;
        }
        private int countDiagonalLeftUp(int i, int j){
            if(mines[i-1][j+1]){ //box diagonal up left
                return 1;
            }
            else return 0;
        }

        private int countDiagonalLeftBottom(int i, int j){
            if(mines[i-1][j-1]){ //box diagonal down left
                return 1;
            }
            else
                return 0;
        }

        private void calculateNumbers() {
            for(int i =0; i<rows; i++){
                for(int j=0; j<cols; j++){
                    if((!mines[i][j])){
                        int mineTracker = 0;
                        if(i ==0 && j==cols-1){
                            mineTracker = mineTracker + countBottom(i,j) + countDiagonalRightDown(i,j) + countRight(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        else if(i>0 && i<rows-1 && j==cols-1){
                            mineTracker = mineTracker + countRight(i,j) + countLeft(i,j) + countBottom(i,j) +countDiagonalLeftBottom(i,j) + countDiagonalRightDown(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        else if(i==rows-1 && j==cols-1){
                            mineTracker = mineTracker + countLeft(i,j) + countDiagonalLeftBottom(i,j)+countBottom(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        else if (i==rows-1 && j>0 && j<cols-1){
                            mineTracker =mineTracker + countBottom(i,j) + countTop(i,j) + countLeft(i,j) +countDiagonalLeftBottom(i,j) + countDiagonalLeftUp(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        else if(i==rows-1 && j==0){
                            mineTracker = mineTracker +countTop(i,j) + countDiagonalLeftUp(i,j) + countLeft(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        else if(i>0 && i<rows-1 && j==0){
                            mineTracker = mineTracker + countLeft(i,j) + countRight(i,j) + countTop(i,j) + countDiagonalLeftUp(i,j) +countDiagonalRightUp(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        else if(i==0 & j==0){
                            mineTracker = mineTracker +countTop(i,j) +countRight(i,j) +countDiagonalRightUp(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        else if(i==0 && j>0 && j<cols-1){
                            mineTracker = mineTracker + countBottom(i,j) + countTop(i,j) + countRight(i,j) + countDiagonalRightUp(i,j) +countDiagonalRightDown(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        else{
                            mineTracker = mineTracker + countDiagonalRightDown(i,j) +countDiagonalRightUp(i,j) +countDiagonalLeftUp(i,j) +countDiagonalLeftBottom(i,j) +countLeft(i,j) +countRight(i,j)+countTop(i,j)+countBottom(i,j);
                            //System.out.println("Minetracker is " + mineTracker);
                        }
                        System.out.println("Minetracker is " + mineTracker);
                        board[i][j] = (char) (mineTracker + '0');
                    }
                }
            }
            // TODO: Implement this method
        }

        // Method to display the current state of the board
        public void displayBoard() {

            // TODO: Implement this method
        }

        // Method to handle a player's move (reveal a cell or place a flag)
        public void playerMove(int rows, int cols, String action) {
            if(action.equals("reveal")){
                displayedBoard[rows][cols] = board[rows][cols];
                   // checkLoss(col, row);
                    //checkWin();
            }
            else if(action.equals("flag")){
                displayedBoard[rows][cols] = 'F';
            }

            // TODO: Implement this method
        }

    public boolean checkWin() {
            int currmines = 0;
            for(int i = 0; i<rows; i ++){
                for(int j = 0; j<cols; j++){
                    if(displayedBoard[i][j] == '-' || displayedBoard[i][j]== 'F' && mines[i][j]){
                        currmines = currmines +1;
                    }
                }
            }
            if(currmines == numMines){
                return true;
            }
            else
                return false;
    }

    public boolean checkLoss(int rows, int cols) {
            if (mines[rows][cols] && displayedBoard[rows][cols] == 'F'){
                return false;
            }
            else if (mines[rows][cols])
                return true;
            else
                return false;
    }

    public void printBoard(){
            String content = "";
            for(int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    //content = content + (this.board[i][j] + "\t");
                    System.out.print(this.board[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }
        public void printdisplayedBoard(){
            String content = "";
            for(int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    //content = content + (this.board[i][j] + "\t");
                    System.out.print(this.displayedBoard[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }
        public void printMines(){
            String content = "";
            for(int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    //content = content + (this.mines[i][j] + "\t");
                    System.out.print(this.mines[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }

    }
