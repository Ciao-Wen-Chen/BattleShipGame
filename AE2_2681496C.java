package ae2_2681496C;

import java.util.Scanner;


public class AE2_2681496C {
	public static void main( String[] args ) {
		
		/*
		 * Generate a 10x10 board.
		 */
	    int rownum = 10, colnum = 10;
		Board board = new Board( rownum, colnum );
		board.openTheHit( false ); // If open the hint of the board, the ship representation on visualising will be ‘s‘.
		System.out.print(board.toString());
		
		/*
		 * Prompt the user to input their name as player's name.
		 * Two players play on the same board. 
		 */
		Scanner s = new Scanner( System.in );
		System.out.println( "Please input a name as player One:" );
		String PlayerOneName = s.nextLine();
		System.out.println( "Please input a name as player Two:" );
		String PlayerTwoName = s.nextLine();
	
		Player playerOne = new Player( PlayerOneName, board );
		Player playerTwo = new Player( PlayerTwoName, board );
		
		/*
		 * Logic to allow players to take turns by implementing the takeTurn method.
		 * The game will over when all the battleship be sunk.
		 */
		boolean gameOver = false;
		boolean playerOneTurn = true;
		
		while ( gameOver == false ) {
			if ( playerOneTurn == true ) {
				
				gameOver = playerOne.takeTurn();
				playerOneTurn = false;
				
			} else if ( playerOneTurn == false ) {
				
				gameOver = playerTwo.takeTurn();
				playerOneTurn = true;
			}
		}
		/*
		 * The player who has more scores will win the game
		 */
		System.out.println( playerOne.getName() +"'s score: "+ playerOne.getScore() +"; "+ playerTwo.getName() +"'s score: "+playerTwo.getScore() );
		
		if ( playerOne.getScore() > playerTwo.getScore() ) {
			
			System.out.println( playerOne.getName() + " wins the game."); 
			
		} else if ( playerOne.getScore() < playerTwo.getScore() ){
			System.out.println( playerTwo.getName() + " wins the game."); 
			
		} else {
			System.out.println( "It is a draw, no one wins the game." ); 
		}
		
	}
}
