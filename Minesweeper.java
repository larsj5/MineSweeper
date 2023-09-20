//Lars Jensen
//CS 110
//Class that represents game of minesweeper
import java.util.Random;
import java.util.Scanner;

public class Minesweeper
{
   //constants
   final int BEGINNER_HEIGHT = 8;
   final int BEGINNER_WIDTH = 8;
   final int BEGINNER_NUM_MINES = 8;
   
   final int INTERMEDIATE_HEIGHT = 10;
   final int INTERMEDIATE_WIDTH = 12;
   final int INTERMEDIATE_NUM_MINES = 10;
   
   final int EXPERT_HEIGHT = 16;
   final int EXPERT_WIDTH = 20;
   final int EXPERT_NUM_MINES = 50;
    
   //variables
   private Grid board;
   private Status gameStatus = Status.OK; //initialize to OK
   int width;
   int height;
   int numMines;
   
   /** constructor that gets the difficulty for the game 
       and then creates a grid and starts the game by calling the
       method perform action
   */
   public Minesweeper()
   {
      System.out.println("Welcome to Minesweeper!");
      getDifficulty();
      board = new Grid(width, height, numMines);
      displayBoard();
   }
   
   /** method displayBoard displays the board
   */
   public void displayBoard()
   {
      System.out.println(board);
   }
   
   /** method getInput gets the input from the user
       for their next move
       @return String that is users input
   */
   public String getInput()
   {
      System.out.println("What next?");
      System.out.println("Options: (U)ncover r c, (F)lag r c, (Q)uit");
      Scanner keyboard = new Scanner(System.in); 
      return keyboard.nextLine();
   }
   
   /** method next move takes in the user input and 
       performs the move that they request
       @param is user input
   */
   public void nextMove(String move)
   {  
      String action;
      int row;
      int col;
      String [] pieces;
      
      if (move.toUpperCase().charAt(0) == 'U')
      {
         pieces = move.split("\\s+");
         action = pieces[0];
         row = Integer.parseInt(pieces[1]);
         col = Integer.parseInt(pieces[2]);
         gameStatus = board.uncoverSquare(row, col);
         performAction(gameStatus);
      }
      else if (move.toUpperCase().charAt(0) == 'F')
      {
         pieces = move.split("\\s+");
         action = pieces[0];
         row = Integer.parseInt(pieces[1]);
         col = Integer.parseInt(pieces[2]);
         board.flagSquare(row, col);
         gameStatus = Status.OK;
         performAction(gameStatus);
      }
      else if (move.toUpperCase().charAt(0) == 'Q')
      {
         System.out.println("Quitting game...");
         System.exit(1);
      }
      else
      {
         gameStatus = Status.OK;
         performAction(gameStatus);
      }
   }
   
   /** method getGameStatus gets the status of the game
       @return status of game
   */
   public Status getGameStatus()
   {
      return gameStatus;
   }
   
   /** method exposeAllMines exposes all mines once game is over
   */
   public void exposeAllMines()
   {
      board.exposeMines();
      displayBoard();
   }
   
   /** method performAction takes in the status of the game after 
       a move and performs the correct action
       @param gameStatus is the status of the game
   */
   public void performAction(Status status)
   {
      if (gameStatus == Status.OK)
      {  
         displayBoard();
      }
      if (gameStatus == Status.MINE)
      {
         exposeAllMines();
         System.out.println("You hit a mine, game over");
         System.exit(1);
      }
      if (gameStatus == Status.WIN)
      {
         displayBoard();
         System.out.println("Congratulations, you win!");
         System.exit(1);
      }
   }
   
   /** method getDifficulty displays the options for difficulty
       and sets the correct height, width, and number of mines
       @return height, width, numMines
   */
   public void getDifficulty()
   {
      System.out.println("Select Difficulty:");
      System.out.print("(B)eginner\n(I)ntermediate\n(E)xpert\n");
      Scanner keyboard = new Scanner(System.in);
      String choice = keyboard.nextLine();
      if (choice.toUpperCase().charAt(0) == 'B')
      {
         width = BEGINNER_WIDTH;
         height = BEGINNER_HEIGHT;
         numMines = BEGINNER_NUM_MINES;
      }
      if (choice.toUpperCase().charAt(0) == 'I')
      {
         width = INTERMEDIATE_WIDTH;
         height = INTERMEDIATE_HEIGHT;
         numMines = INTERMEDIATE_NUM_MINES;
      }
      if (choice.toUpperCase().charAt(0) == 'E')
      {
         width = EXPERT_WIDTH;
         height = EXPERT_HEIGHT;
         numMines = EXPERT_NUM_MINES;
      }
      
   }
}