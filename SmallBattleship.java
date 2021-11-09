package ae2_2681496C;

/*
 * SmallBattleship class has a size of 1, and there must be 3 on the board.
 * A static attribute for each sub class that specify the total number of permissible ships of that class on the board.
 */
public class SmallBattleship extends Battleship {
	
	private static int smallBattleShipCount = 0;
	
	public SmallBattleship() {
		this.size = 1;
		this.remainingHealth = this.size;
		this.sunk = false;
		smallBattleShipCount ++;
		
		if ( smallBattleShipCount > 3 ) {
			throw new RuntimeException( "Small battleship count exceed three." );
		}
	}
}
