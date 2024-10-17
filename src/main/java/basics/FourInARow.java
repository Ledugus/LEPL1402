package basics;


/**
 * A class that represents a game of Four in a Row.
 * The game is played on a 6x7 board.
 * A player wins when he has 4 pieces in a row, column or diagonal.
 *
 * FourInARow is a two-player connection rack game, in which the players choose a color and
 * then take turns dropping colored tokens into a six-row, seven-column vertically suspended grid.
 * The pieces fall straight down, occupying the lowest available space within the column.
 *
 * The objective of the game is to be the first to form a horizontal,
 * vertical, or diagonal line of four of one's own tokens.
 *
 * Your task is to model the game and implement the method hasWon(char player) that returns true.
 * if the player has won the game.
 * We advise you to model the state of the game with an internal 2D array of char.
 */
public class FourInARow {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    private static final char EMPTY = '-';
    private static final char[] PLAYERS = {'X', 'O'};

     // add your own instance variables here
    public char[][] board;
    public FourInARow() {
         board = new char[][] {{'-', '-', '-', '-', '-', '-', '-'},
                 {'-', '-', '-', '-', '-', '-', '-'},
                 {'-', '-', '-', '-', '-', '-', '-'},
                 {'-', '-', '-', '-', '-', '-', '-'},
                 {'-', '-', '-', '-', '-', '-', '-'},
                 {'-', '-', '-', '-', '-', '-', '-'}};
    }

    /**
     * Play a piece in column j for the given player.
     * @param j the column index
     * @param player the player (X or O)
     * @throws IllegalArgumentException if j is not a valid column index or if the column is full or if the player is not X or O
     */
    public void print() {
        for (char[] line : this.board){
            System.out.println(new String(line));
        }
        System.out.println(" ");


    }
    public void play(int j, char player) {
        if (j<0 || j>6){
            throw new IllegalArgumentException();
        }
        if (board[0][j] != '-') {
             throw new IllegalArgumentException();
        }

        int height = 0;
        while (height < 5 && board[height+1][j] == '-'){
            height++;
        }
        board[height][j] = player;
        print();
    }


    /**
     * Returns true if the player has won the game.
     * @param player the player (X or O)
     * @return true if the player has won the game
     * @throws IllegalArgumentException if the player is not X or O
     */
    public boolean hasWon(char player) {
         // add your own code here
        return false;
    }
}
