//Lars Jensen
//CS 110
//Driver for Minesweeper game

/** I added the following functionality that I would like
    evaluated for extra credit:
    1 - Added option to select difficulty
*/

public class Driver
{
   public static void main(String[] args)
   {
      Minesweeper game = new Minesweeper();
      while (game.getGameStatus() == Status.OK)
      {
         game.nextMove(game.getInput());       
      }
   }
}