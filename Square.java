package ae2_2681496C;

/*
 * Each cell in the board is a square object.
 */
public class Square {
	private int row, col; //Integer denoting the row and column position.
	protected boolean shipExist; //Indicate whether the square has a ship in it.
	protected Battleship battleship; //Reference to a battleship if one is currently on the square.
	protected boolean hasFireShot; //Indicate whether the player has fired a shot at the square.
	public boolean hintOpen = false;
	
	public Square( int row, int col, boolean shipExist, boolean hasFireShot, Battleship battleship ) {
		this.row = row; 
		this.col = col; 
		this.shipExist = shipExist; 
		this.hasFireShot = hasFireShot; 
		this.battleship = battleship;
	}
	
	public Square( int row, int col, boolean shipExist, boolean hasFireShot ) {
		this.row = row; 
		this.col = col; 
		this.shipExist = shipExist; 
		this.hasFireShot = hasFireShot; 
		this.battleship = null;
	}
	
	/*
	 * Setters 
	 */
	
	public void setBattleship( Battleship battleship ) {
		this.battleship = battleship;
		this.setShipExist();
	}
	private void setShipExist() {
		this.shipExist = true;
	}
	public void sethasFireShot() {
		this.hasFireShot = true;
		if ( this.shipExist ) {
			this.battleship.hitShip();
			this.shipExist = false;	
		}
	}
	
	/*
	 * Getter
	 */
	public boolean gethasFireShot() {
		return this.hasFireShot;
	}
	public boolean getShipExist() {
		return this.shipExist;
	}
	public Battleship getBattleship() {
		return this.battleship;
	}
	public int getShipHealth() {
		if ( this.battleship == null ) {
			return -1;
		} else {
			return this.battleship.getShipHealth();
		}
	}
	
	
	public void hintOpen( boolean open ) {
		if ( open == true ) {
			this.hintOpen = true;
		} else {
			this.hintOpen = false;
		}
	}
	/*
	 * Return a representation of the square. 
	 * If the player has not interacted with a square then the representation should be ‘-‘.
	 * If the player has fired and missed then the square representation should be ‘o’.
	 * If they have hit a ship then the square representation should be ‘x’.
	 * Each square should be printed with String.format to exactly 3 spaces in length.
	 */
	public String toString( boolean open ) { //Create a method that catches the argument of hint opening status. 
		
		boolean squareHasShip = this.getShipExist();
		boolean squareBeShot = this.gethasFireShot();
		Battleship squareShip =  this.getBattleship();
		boolean openHint = open;
		
		String squareStatus = "";
		
		if ( openHint == true ) {
			if ( squareHasShip == false && squareBeShot == false) {
				squareStatus = String.format("%-3s", "-"); 
				
			} else if ( squareShip == null && squareBeShot == true) {
				squareStatus = String.format("%-3s", "o");
				
			} else if ( squareHasShip == true &&  squareBeShot == false ) {
				squareStatus = String.format("%-3s", "s"); //If the hint has been opened, battleships will represent "s" on the visualisation.
				
			} else if ( squareShip != null &&  squareBeShot == true) {
				squareStatus = String.format("%-3s", "x");
			}
		} else {
			if ( squareHasShip == false && squareBeShot == false) {
				squareStatus = String.format("%-3s", "-"); 
				
			} else if ( squareShip == null && squareBeShot == true) {
				squareStatus = String.format("%-3s", "o");
				
			} else if ( squareHasShip == true &&  squareBeShot == false ) {
				squareStatus = String.format("%-3s", "-");
				
			} else if ( squareShip != null &&  squareBeShot == true) {
				squareStatus = String.format("%-3s", "x");
			}
		}
		return squareStatus;
		 
	}
	/*
	 * The Squares class should have a toString method that will return a representation of the square.
	 */
	public String toString() {
		if ( this.hintOpen == true ) {
			return this.toString( true );
		} else {
			return this.toString( false );
		}
	}
	
	
	
}
