package ae2_2681496C;

import java.util.Random;

public class Board {
	
	private int rowNum, colNum; 
	private Square[][] squarebroad;
	public int remainShipNum;
	public int healthStatus;
	public boolean openTheHint;
	/*
	 * The board constructor should take in two values representing the number of rows and columns. 
	 * (assume that these values will be 10)
	 */
	public Board( int rowNum, int colNum ) {
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.populate(rowNum, colNum);
		
		int smallShipNum = 3, mediumShipNum = 2, largeShipNum = 1;  //Generate different types and numbers of battleships according to the given number.
		this.generateBattleship( rowNum, colNum , smallShipNum, mediumShipNum, largeShipNum);
		this.remainShipNum = smallShipNum + mediumShipNum + largeShipNum; 
	}
	/*
	 * Populate the board data structure with Square objects.
	 */
	private void populate(int rowNum, int colNum) {
		Square[][] squarebroad = new Square[rowNum][colNum];
		this.squarebroad = squarebroad;
		for( int i = 0; i < rowNum; i ++ ) {
			for( int j = 0; j < colNum; j ++) {
				boolean shipExist = false, hasFireShot = false; 
				Square square = new Square( i, j, shipExist, hasFireShot);
				this.squarebroad[i][j] = square;
			}
		}
	}
	/*
	 * Responsible for generating battleships.
	 * When generating battleships, the placement of the ships should follow certain restrictions.
	 * 1. You should ensure that the ship length does not exist out of bounds of the board. 2. Ensure that ships do not overlap.
	 * 3. Placement of the ships must be randomised. Direction of the ships must be randomised.
	 */
	private void generateBattleship(int rowNum, int colNum, int smallShipNum, int mediumShipNum, int largeShipNum) {
		Random r = new Random();
		int shiprow, shipcol;
		
		boolean isShipAcceptable = false;
		int shipAlreadySeting = 0; 
		int shipNum = 6;
		
		// Set small battleship.
		for ( int i = 0; i < smallShipNum; i++ ) {
			SmallBattleship smallBattleship = new SmallBattleship();
			
			boolean setOneShip = false;
			while ( setOneShip == false ) {
				shiprow = r.nextInt(rowNum-1);
				shipcol = r.nextInt(colNum-1);
				
				boolean direction = direction();
				
				isShipAcceptable = isShipAcceptable( rowNum, colNum, direction, shiprow, shipcol,  smallBattleship.getSize());
				if ( isShipAcceptable == true  ) {
					Square shipSquare = squarebroad[shiprow][shipcol];
					shipSquare.setBattleship(smallBattleship);
					
					setOneShip = true;
					shipAlreadySeting += 1;
				}
			}
		}
		// Set medium battleship.
		for ( int i = 0; i < mediumShipNum; i++ ) {
			MediumBattleship mediumBattleship = new MediumBattleship();
			boolean setOneShip = false;
			while ( setOneShip == false ) {
				shiprow = r.nextInt(rowNum-1);
				shipcol = r.nextInt(colNum-1);
				
				boolean direction = direction();
				
				isShipAcceptable = isShipAcceptable( rowNum, colNum, direction, shiprow, shipcol,  mediumBattleship.getSize());
				if ( isShipAcceptable == true  ) {
					
					if ( direction == true ) {
						for ( int j = 0 ; j < mediumBattleship.getSize(); j ++ ) {
							Square shipSquare = squarebroad[shiprow+j][shipcol];
							shipSquare.setBattleship(mediumBattleship);
						}
					} else {
						for ( int j = 0 ; j < mediumBattleship.getSize(); j ++ ) {
							Square shipSquare = squarebroad[shiprow][shipcol+j];
							shipSquare.setBattleship(mediumBattleship);
						}
					}
					
					setOneShip = true;
					shipAlreadySeting += 1;
				}
			}
		}
		// Set large battleship
		for ( int i = 0; i < largeShipNum; i++ ) {
			LargeBattleship largeBattleship = new LargeBattleship();
			boolean setOneShip = false;
			while ( setOneShip == false ) {
				shiprow = r.nextInt(rowNum-1);
				shipcol = r.nextInt(colNum-1);
				
				boolean direction = direction();
				
				isShipAcceptable = isShipAcceptable( rowNum, colNum, direction, shiprow, shipcol,  largeBattleship.getSize());
				if ( isShipAcceptable == true  ) {
					
					if ( direction == true ) {
						for ( int j = 0 ; j < largeBattleship.getSize(); j ++ ) {
							Square shipSquare = squarebroad[shiprow+j][shipcol];
							shipSquare.setBattleship(largeBattleship);
						}
					} else {
						for ( int j = 0 ; j < largeBattleship.getSize(); j ++ ) {
							Square shipSquare = squarebroad[shiprow][shipcol+j];
							shipSquare.setBattleship(largeBattleship);
						}
					}
					
					setOneShip = true;
					shipAlreadySeting += 1;
				}
			}
		}	
		
	}
	/* 
	 * Responsible for checking if the placement of the ships follows certain restrictions.
	 * 1. Ensure that the ship length does not exist out of bounds of the board.
	 * 2. Ensure that ships do not overlap.
	 */
	public boolean isShipAcceptable( int rowNum, int colNum, boolean vertical, int shiprow, int shipcol , int shipSize ) {
		if ( vertical == true ) { // Direction = vertical
			if ( shipSize == 1 ) { //Small battleship
				if ( squarebroad[shiprow][shipcol].getShipExist() == true ) { //Ensure ships do not overlap.
					return false; 
				} else { 
					return true; 
				}
			}
			else if ( shipSize == 2 ) { //Medium  battleship
				if ( shiprow == rowNum-1 ) { //Ensure the ship length does not out of bounds of the board.
					return false;
				} else if ( squarebroad[shiprow][shipcol].getShipExist() == true || squarebroad[shiprow+1][shipcol].getShipExist() == true ){ //Ensure ships do not overlap.
					return false;
				} else {
					return true;
				}
			}
			else { //Large  battleship
				if ( shiprow == rowNum-1 || shiprow == rowNum-2 ) { //Ensure the ship length does not out of bounds of the board.
					return false; 
				} else if ( squarebroad[shiprow][shipcol].getShipExist() == true || //Ensure ships do not overlap.
						  squarebroad[shiprow+1][shipcol].getShipExist() == true || 
						  squarebroad[shiprow+2][shipcol].getShipExist() == true) {
					return false;
				} else {
					return true;
				}
			}
		} else { // Direction = horizontal
			if ( shipSize == 1 ) { //Small battleship
				if ( squarebroad[shiprow][shipcol].getShipExist() == true ) { //Ensure ships do not overlap.
					return false;
				} else {
					return true;
				}
			}
			else if ( shipSize == 2 ) { //Medium  battleship
				if ( shipcol == colNum-1 ) { //Ensure the ship length does not out of bounds of the board.
					return false;
				} else if ( squarebroad[shiprow][shipcol].getShipExist() == true || squarebroad[shiprow][shipcol+1].getShipExist() == true ){ //Ensure ships do not overlap.
					return false;
				} else {
					return true;
				}
			}
			else { //Large  battleship
				if ( shipcol == colNum-1 || shipcol == colNum-2) { //Ensure the ship length does not out of bounds of the board.
					return false;
				} else if ( squarebroad[shiprow][shipcol].getShipExist() == true || //Ensure ships do not overlap.
						  squarebroad[shiprow][shipcol+1].getShipExist() == true ||
						  squarebroad[shiprow][shipcol+2].getShipExist() == true) {
					return false;
				} else {
					return true;
				}
			}
		} 
	}
	/*
	 * Responsible for giving a random direction.
	 */
	private boolean direction() {
		Random r = new Random();
		return r.nextBoolean();
	}
	/*
	 * Check if user's hit is valid.
	 */
	public boolean checkIfHitValid( int userInputRow, int UserInputCol ) { 
		if ( userInputRow+1 <= this.rowNum && UserInputCol+1 <= this.colNum ) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Check if the hit have been checked prior.
	 */
	public boolean checkIfFireDuplicates( int userInputRow, int UserInputCol ) {
		return this.squarebroad[userInputRow][UserInputCol].gethasFireShot();
	}
	
	/*
	 * Check if user hit the battleship.
	 * Access square and set it as it has been fire.
	 */
	public boolean checkIfHitShip( int userInputRow, int UserInputCol ) {
		if ( this.squarebroad[userInputRow][UserInputCol].getShipExist() == true) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Check if the battleship has been sunk.
	 */
	public boolean checkIfSunkShip( int userInputRow, int UserInputCol ) {
		if ( this.squarebroad[userInputRow][UserInputCol].getShipHealth() > 0 ) {
			return false;
		} else if ( this.squarebroad[userInputRow][UserInputCol].getShipHealth() == -1 ) {
			return false;
		} else {
			return true;
		} 
	}
	
	/*
	 * Set the status (be fired/ be sunk) of the square.
	 */
	public void broadGetFire( int userInputRow, int UserInputCol ) {
		this.squarebroad[userInputRow][UserInputCol].sethasFireShot();
		boolean ifsunk = this.checkIfSunkShip(userInputRow, UserInputCol);
		if (ifsunk == true) {
			this.remainShipNum -= 1;
		}
	}
	
	/*
	 * Get the number of surviving ships.
	 */
	public int getShipnumeber() {
		return this.remainShipNum;
	}
	/*
	 * Get the remaining health of the battleship which been hit.
	 */
	public int getFiredShipHealth( int userInputRow, int UserInputCol ) {
		int shipHealth = this.squarebroad[userInputRow][UserInputCol].getShipHealth();
		return shipHealth;
	}
	/*
	 * Access the square on board.
	 */
	public Square[][] getSquareBroad(){ 
		return this.squarebroad; 
	}
	/*
	 * Visualise the position of ships.
	 */
	public void openTheHit( boolean open ) {
		if ( open == true ) {
			this.openTheHint = true;
		} else {
			this.openTheHint = false;
		}
	}
	
	
	/*
	 * Visualising the Game.
	 * Invoke the toString representation of each Square object within the data structure.
	 */
	public String toString() {
		String figure = "";
		for ( int i = 0; i < this.rowNum; i++ ) {
			String line = "";
			for ( int j = 0; j < this.colNum; j++ ) {
				if ( this.openTheHint == true ) { //if the hint has been opened, the position of battleships will be visualised.
					line += this.squarebroad[i][j].toString( true );
				} else {
					line += this.squarebroad[i][j].toString( false );
				}
			} 
			figure += line+"\n";
		}
		return figure;  
	}
}





























