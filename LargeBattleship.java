package ae2_2681496C;

/*
 * LargeBattleship class inherit from the Battleship superclass. 
 * SmallBattleship has a size of 3, and there must be 1 on the board.
 * A static attribute for each sub class that specify the total number of permissible ships of that class on the board.
 */

public class LargeBattleship extends Battleship {
	
	private static int largeBattleShipCount = 0;
	
	public LargeBattleship() {
		this.size = 3;
		this.remainingHealth = this.size;
		this.sunk = false;
		largeBattleShipCount ++;
		
		if ( largeBattleShipCount > 1 ) {
			throw new RuntimeException( "Large battleship count exceed three." );
		}
	}

}
