//Lars Jensen
//CS 110
//Class that extends square and defines a mine square
public class MineSquare extends Square
{
   /** method uncover reveals that square is a mine (*)
       @return true if uncover successful
   */
   public boolean uncover()
   {
      if (isUncovered() || isFlagged())
         return false;
      else
      {
         setElement("*");
         setUncovered();
         return true;
      }
   }
   
   /** method isMine returns that square is a mine (true)
       @return true
   */
   public boolean isMine()
   {
      return true;
   }
}