package ae2_2681496C;

/*
 * MediumBattleship class inherit from the Battleship superclass. 
 * MediumBattleship has a size of 2, and there must be 2 on the board.
 * A static attribute for each sub class that specify the total number of permissible ships of that class on the board.
 */

public class MediumBattleship extends Battleship{
	
	private static int mediumBattleShipCount = 0;
	
	public MediumBattleship() {
		this.size = 2;
		this.remainingHealth = this.size;
		this.sunk = false;
		mediumBattleShipCount ++;
		
		if ( mediumBattleShipCount > 2 ) {
			throw new RuntimeException( "Medium battleship count exceed three." );
		}
	}

}
