//Lars Jensen
//CS 110
//Class that defines the grid in minesweeper
import java.util.Random;

public class Grid
{
   //instance variables
   private Square[][] grid;
   private int width; // = 12
   private int height; // = 10
   private int numMines; // = 10
   private int numSquaresUncovered = 0;
   
   /** constructor for grid that takes in the width (# of columns),
       height (# of rows), and number of mines and creates grid object
       @param width of grid
       @param height of grid
       @param numMines in grid
   */
   public Grid(int width, int height, int numMines)
   {
      this.width = width;
      this.height = height;
      this.numMines = numMines;
      grid = new Square[height][width];
      
      //place mines
      for (int i = 0; i < numMines; i++)
      {
         Random rand = new Random();
         int mineRow = rand.nextInt(height);
         int mineCol = rand.nextInt(width);
         
         //make sure mine hasn't already been placed there
         while (grid[mineRow][mineCol] != null)
         {
            mineRow = rand.nextInt(height);
            mineCol = rand.nextInt(width);
         }
         grid[mineRow][mineCol] = new MineSquare();
      }
      
      //fill out grid
      for (int row = 0; row < height; row++)
      {
         for (int col = 0; col < width; col++)
         {
            if(grid[row][col] == null)
               grid[row][col] = new NumberSquare(getNeighbors(row, col), row, col);
         }
      }
      
   }
   
   /** method get neighbors finds the neighboring mines to the square
       @param myRow is the row of the square
       @param myCol is the column of the square
       @return number of neighboring mines
   */
   public int getNeighbors(int myRow, int myCol)
   {
      //constants
      final int NORMAL_SCAN = 1;
      final int NO_SCAN = 0;
      final int EDGE = 0;
      
      //variables
      int neighborMines = 0;
      int goLeft = NORMAL_SCAN;
      int goRight = NORMAL_SCAN;
      int goUp = NORMAL_SCAN;
      int goDown = NORMAL_SCAN;
      
      //ensure that scan doesn't go off the board
      if (myCol == EDGE)
         goLeft = NO_SCAN;
      if (myRow == EDGE)
         goUp = NO_SCAN;
      if (myRow == height - 1)
         goDown = NO_SCAN;
      if (myCol == width - 1)
         goRight = NO_SCAN;
      
      for (int row = (myRow - goUp); row < myRow + goDown + NORMAL_SCAN; row++)
      {
         for (int col  = (myCol - goLeft); col < myCol + goRight + NORMAL_SCAN; col++)
         {
            if (grid[row][col] == null || grid[row][col].isMine() == false)
            {
               neighborMines = neighborMines;
            }
            else
               neighborMines++;
         }
      }
      return neighborMines;
   }
   
   /** method uncoverSquare uncovers the square in the grid
       @param myRow is the row of square
       @param myCol is the column of square
       @return Status of game after uncovering
   */
   public Status uncoverSquare(int myRow, int myCol)
   {
      //constants
      final int NO_MINE_GRID = 2;
      final int ONE_FROM_EDGE = 1;
      final int EDGE = 0;
      final int ONE_MINE_GRID = 1;
      
      //variables
      int goLeft;
      int goRight;
      int goUp;
      int goDown;      
      
      
      if (grid[myRow][myCol].isMine())
      {
         return Status.MINE;
      }
      else
      {
         if(getNeighbors(myRow, myCol) == 0)
         {
            //initialize variables to base move
            goLeft = NO_MINE_GRID;
            goRight = NO_MINE_GRID;
            goUp = NO_MINE_GRID;
            goDown = NO_MINE_GRID;
            
            //make sure to not run off edge
            if (myRow - ONE_FROM_EDGE == -1)
               goUp = EDGE;
            if (myRow - NO_MINE_GRID == -1)
               goUp = ONE_FROM_EDGE;
               
            if (myRow + ONE_FROM_EDGE == height)
               goDown = EDGE;
            if (myRow + NO_MINE_GRID == height)
               goDown = ONE_FROM_EDGE;
               
            if (myCol - ONE_FROM_EDGE == -1)
               goLeft = EDGE;
            if (myCol - NO_MINE_GRID == -1)
               goLeft = ONE_FROM_EDGE;
         
            if (myCol + ONE_FROM_EDGE == width)
               goRight = EDGE;
            if (myCol + NO_MINE_GRID == width)
               goRight = ONE_FROM_EDGE;
         
            // expose 5x5 or whatever size if square close to edge
            for(int row = (myRow - goUp); row < (myRow + 1 + goDown); row++)
            {
               for(int col = (myCol - goLeft); col < (myCol + 1 + goRight); col++)
               {
                  if (grid[row][col].isMine() == false && grid[row][col].isUncovered() == false)
                  {
                     grid[row][col].uncover();
                     numSquaresUncovered++;
                  }
               }
            }
            
         }
         
         else if(getNeighbors(myRow, myCol) == 1)
         {
            //initialize variables to base move
            goLeft = ONE_MINE_GRID;
            goRight = ONE_MINE_GRID;
            goUp = ONE_MINE_GRID;
            goDown = ONE_MINE_GRID;
            
            //make sure not to go off grid
            if (myRow - ONE_FROM_EDGE == -1)
               goUp = EDGE;
            if (myRow + ONE_FROM_EDGE == height)
               goDown = EDGE;
            if (myCol - ONE_FROM_EDGE == -1)
               goLeft = EDGE;
            if (myCol + ONE_FROM_EDGE == width)
               goRight = EDGE;
            
            //expose 3x3
            for(int row = (myRow - goUp); row < (myRow + 1 + goDown); row++)
            {
               for(int col = (myCol - goLeft); col < (myCol + 1 + goRight); col++)
               {
                  if (grid[row][col].isMine() == false && grid[row][col].isUncovered() == false)
                  {
                     grid[row][col].uncover();
                     numSquaresUncovered++;
                  }
               }
            }
         }
         else
         {
            if (grid[myRow][myCol].isUncovered() == false)
            {
               grid[myRow][myCol].uncover();
               numSquaresUncovered++;
            }
         }
            
         
         //check to see if user has won
         if (numSquaresUncovered == (height * width) - numMines)
            return Status.WIN;
     
         //if not return status of OK
         return Status.OK;
         
      }
   }
   
   /** method expose mines goes through and exposes all of the 
       mines on the board
   */
   public void exposeMines()
   {
      for (int row = 0; row < height; row++)
      {
         for (int col = 0; col < width; col++)
         {
            if (grid[row][col].isMine())
               grid[row][col].uncover();
         }
      }
   }
   
   /** method flagSquare flags the square at given row and col
       @param myRow is row of square
       @param myCol is column of square
   */
   public void flagSquare(int myRow, int myCol)
   {
      grid[myRow][myCol].flagSquare();
   }
   
   /** method toString prints the grid, including labels 
       @return a string containing the grid
   */
   @Override
   public String toString()
   {
      String grid = "  ";
      
      //add top line
      for (int i = 0; i < width; i++)
      {
         String num = i + "";
         grid += String.format("%3s", num);
      }
      grid +="\n";
      
      //grid += "   0  1  2  3  4  5  6  7  8  9 10 11\n";
      
      //add rest of grid
      for (int row = 0; row < height; row++)
      {
         grid += String.format("%2d", row);
         for (int col = 0; col < width; col++)
         {
            grid += String.format("%3s", this.grid[row][col].toString());
         }
         grid += "\n";
      }
      return grid;
   }
   
}