package ae2_2681496C;

/*
You are to specify a Battleship class. This class should specify the following attributes:
- Whether it is sunk.
- The remaining health (how many more hits it can take before sinking)
- The size of the ship.
 */
public class Battleship {
	
	protected boolean sunk; //Whether it is sunk.
	protected int remainingHealth, size; //The remaining health. And the size of the ship
	
	
	public Battleship() {
		this.size = 2; //Battleship should be specified as being 2 units in size.
		this.remainingHealth = this.size;
		this.sunk = false;
	}
	
	/* 
	 * Each Battleship object will be checked against whenever a player 
	 * fires into the board on a particular square.
	 */
	public void hitShip() {
		this.remainingHealth -= 1; //Decrease the health of the ship.
		if ( this.remainingHealth == 0 ) { //Update its state.
			this.sunk = true;
		}
	}
	
	public int getShipHealth() {
		return this.remainingHealth;
	}
	
	public int getSize() {
		return this.size;
	}
}
