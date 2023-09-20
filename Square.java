//Lars Jensen
//CS 110
//Square class in an abstract class that represents a square in minesweeper board

public abstract class Square
{
   //instance varaibles
   private String element;
   private boolean flagged;
   private boolean uncovered;
   
   /** Default no-arg constructor that sets all 
       variables to default; element as underscore, flagged
       as false, and uncovered as false
   */
   public Square()
   {
      this("x", false, false);
   }
   
   /** alternate constructor that takes in value for element,
       if it's flagged, and if it's uncovered
       @param element of square
       @param flagged or not
       @param uncovered or not
   */
   public Square(String element, boolean flagged, boolean uncovered)
   {
      this.element = element;
      this.flagged = flagged;
      this.uncovered = uncovered;
   }
   
   /** method isFlagged returns if the square is flagged or not
       @return true if flagged
   */
   public boolean isFlagged()
   {
      if(flagged)
         return true;
      return false;
   }
   
   /** method isUncovered checks if a square is uncovered
       @return true if uncovered
   */
   public boolean isUncovered()
   {
      if(uncovered)
         return true;
      return false;
   }
   
   /** method flagSquare flags a square (changes it to "f")
   */
   public void flagSquare()
   {
      if (flagged)
      {
         element = "x";
         flagged = false;
      }
      else
      {
         element = "f";
         flagged = true;
      }
   }
   
   /** method setUncovered sets the square to uncovered
   */
   public void setUncovered()
   {
      uncovered = true;
   }
   
   /** method setElement sets the element of a square
       @param element to be set
   */
   public void setElement(String element)
   {
      this.element = element;
   }
   
   /** toString method returns the square as a string, 
       specifically the element part of it
   */
   @Override
   public String toString()
   {
      return String.format(element);
   }
   
   /**abstract method uncover that reveals what the square is
      @return if uncover was successful
   */
   public abstract boolean uncover();
   
   /** abstract method isMine returns if square is a mine or not
       @return if square is a mine
   */
   public abstract boolean isMine();
}