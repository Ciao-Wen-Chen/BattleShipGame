package ae2_2681496C;

import java.util.Scanner;

/*
 * Each player class should have a reference to a board object they will play the game with. Their name, and scores. 
 */
public class Player {

	private Board board; //A reference to a board object they will play the game with.
	private String name; //Player's name.
	private int score; //Player's score.
	
	
	public Player( String name, Board board ) {
		this.name = name;
		this.board = board;
		this.score = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	/*
	 * Players have a takeTurn method, which should be called from a main game loop within the main class. 
	 * This method should prompt the user to input their guess from the console.
	 */
	public boolean takeTurn() {
		/*
		 * Prompt the user to input their guess from the console.
		 */
		
		System.out.println( this.name + ", please input your guess:" );
		Scanner s = new Scanner( System.in );
		int row = s.nextInt();
		int col = s.nextInt();
		
		if ( this.board.checkIfHitValid( row, col ) ) { //Check if the input is out of range.
			
			if ( this.board.checkIfFireDuplicates( row, col ) == false ) { //Check if the inputs coordinates have been checked prior.
				
				if( this.board.checkIfHitShip( row, col ) ) { //Check whether the input hits the battleship.
					
					this.board.broadGetFire( row, col ); 
					int originalScore = this.score; 
					int shipremainingHealth = this.board.getFiredShipHealth( row, col );
					
					if ( this.board.checkIfSunkShip( row, col ) ){ //Check whether sunk the battleship.
						
						this.score += 1;
						System.out.println( this.name + " sunks a battleship, player's score (" + originalScore + "+1); "+"now the score is " + this.score +"."+
						" It still has " + this.board.getShipnumeber() + " battleship(s)." );
						
					} else { 
						System.out.println( this.name + " hit a part of battleship, now the score is " + this.score + 
						"; the ship remains " + shipremainingHealth + " health." );
					}
				} else {
					this.board.broadGetFire( row, col ); 
					System.out.println( this.name + " didn't hit any ship." );
				}
			} else {
				System.out.println( "Player inputs coordinates that have been checked prior, he/she lose their turn." );
			}
		} else {
			System.out.println( "Your input is out of range, which is invalid." );
			this.takeTurn();
			return false;
		}
		
		/*
		 * Check if all battleships on the board are already be sunk
		 */
		if ( this.board.getShipnumeber() == 0 ) {
			
			System.out.println( this.name + " hitted the last battleship, game over." );
			System.out.print(board.toString());
			return true;
			
		} else {
			
			System.out.print(board.toString());
			return false;
			
		}
		
	}
	
}
