//Lars Jensen
//CS 110
//NumberSquare class represents a square in minesweeper that is not a 
//mine and tells how many neighboring mines there are to square

public class NumberSquare extends Square
{
   //instance variables
   private int neighborMines;
   private int myRow;
   private int myCol;
   
   /**constructor for NumberSquare takes in values for neighboring
      mines, an it's row and the column, and creates the square
      @param neighborMines
      @param myRow
      @param myCol
   */
   public NumberSquare(int neighborMines, int myRow, int myCol)
   {
      this.neighborMines = neighborMines;
      this.myRow = myRow;
      this.myCol = myCol;
   }
   
   /** method uncover overrides one in Square and uncovers the square
       to reveal what is underneath
       @return true if successful
   */
   @Override
   public boolean uncover()
   {
      if (isUncovered() || isFlagged())
         return false;
      else
      {
         setUncovered();
         if (neighborMines == 0)
            setElement("_");
         else
            setElement(neighborMines + "");
         return true;
      }
   }
   
   /** method getNeighborMines gets the amount of 
       neighboring mines to the square
       @return number of neighboring mines
   */
   public int getNeighborMines()
   {
      return neighborMines;
   }
   
   /** method isMine returns that the number square is
       not a mine(false)
       @return false, not a mine
   */
   @Override
   public boolean isMine()
   {
      return false;
   }
}