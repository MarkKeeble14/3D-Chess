package Pieces;

import Board.Board;
import Board.Main;
import Board.Tile;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Class Queen.
 */
public class Queen extends Piece {
	
	/**
	 * Instantiates a new queen.
	 *
	 * @param pieceAlliance the piece alliance
	 * @param piecePosition the piece position
	 * @param row the row
	 * @param col the col
	 * @param aisle the aisle
	 * @param type the type
	 */
	public Queen(final Alliance pieceAlliance, final int piecePosition, int row, int col, int aisle, String type) {
		super(pieceAlliance, piecePosition, row, col, aisle, type);
	}
	
	/** The type. */
	private String type;
	
	/**
	 * Validate move.
	 *
	 * @param currentTile the current tile
	 * @param targetTile the target tile
	 * @return true, if successful
	 */
	public boolean validateMove(Tile currentTile, Tile targetTile) {
		boolean allowMove = false;
		
		// Current
		int curRow = currentTile.getRow();
		int curCol = currentTile.getCol();
		int curAisle = currentTile.getAisle();
		
		// Target
		int targetRow = targetTile.getRow();
		int targetCol = targetTile.getCol();
		int targetAisle = targetTile.getAisle();
		
		// QUEEN MOVEMENT LOGIC
		// ROOK MOVEMENT LOGIC
		// Same Column, Down
		if (targetCol == curCol && targetRow >= curRow + Math.abs(curAisle - targetAisle)) {
			// Base movement is ALLOWED. IE the rook is going STRAIGHT DOWN.
			// Method ALLOW sets the variable allow to either true or false depending on the target tiles occupant.
			allowMove = allowBasedOnOccupant(targetTile);
			
			// CONSIDER MOVEMENT FROM DIFFERENT AISLES. MUST be on a DIAGONAL from CURRENT AISLE TILE.
			if (targetAisle != curAisle && targetRow > curRow + Math.abs(targetAisle - curAisle)) {
				return false;
			}
			
			// Checking for obstructions within the rooks path.
			// Looping through the TileList of each board.
			for (Board b : Main.getBoardList()) {
				for (Tile t : b.getTileList()) {
					// SAME COLUMN, HAS an OCCUPANT, and is THE SAME AISLE.
					if (t.getCol() == curCol && t.getOccupant() != null && t.getOccupant() != this && t.getAisle() == aisle) {
						if (targetAisle != t.getAisle()) {
							if (curRow > targetRow) {
								if (t.getRow() > targetRow + 1 && t.getRow() < curRow - 1) {
									return false;
								}
							} else if (curRow < targetRow){
								if (t.getRow() < targetRow - 1 && t.getRow() > curRow + 1) {
									return false;
								}
							}
						// SAME COLUMN, HAS an OCCUPANT, and DIFFERENT AISLE.
						} else if (t.getCol() == curCol && t.getOccupant() != null && t.getOccupant() != this) {
							if (curRow > targetRow) {
								if (t.getRow() > targetRow && t.getRow() < curRow) {
									return false;
								}
							} else if (curRow < targetRow){
								if (t.getRow() < targetRow && t.getRow() > curRow) {
									return false;
								}
							}
						}
						
					}
				}	
			}
		// Same Row, Up
		} else if (targetCol == curCol && targetRow <= curRow - Math.abs(curAisle - targetAisle)) {
			allowMove = allowBasedOnOccupant(targetTile);
			
			// CONSIDER MOVEMENT FROM DIFFERENT TILES. MUST be on a DIAGONAL from CURRENT AISLE TILE.
			if (targetAisle != curAisle && targetRow < curRow - Math.abs(targetAisle - curAisle)) {
				return false;
			}
			
			// FOLLOW COMMENTS ON [SAME ROW, DOWN]. SAME LOGIC. 
			// DIFFERENT CONDITIONS MEANS I CAN'T CONDENSE INTO A METHOD THAT IS APPLICABLE FOR ALL CASES.
			for (Board b : Main.getBoardList()) {
				for (Tile t : b.getTileList()) {
					if (t.getCol() == curCol && t.getOccupant() != null && t.getOccupant() != this && t.getAisle() == aisle) {
						if (targetAisle != t.getAisle()) {
							if (curRow > targetRow) {
								if (t.getRow() > targetRow + 1 && t.getRow() < curRow - 1) {
									return false;
								}
							} else if (curRow < targetRow){
								if (t.getRow() < targetRow - 1 && t.getRow() > curRow + 1) {
									return false;
								}
							}
						} else {
							if (curRow > targetRow) {
								if (t.getRow() > targetRow && t.getRow() < curRow) {
									return false;
								}
							} else if (curRow < targetRow){
								if (t.getRow() < targetRow && t.getRow() > curRow) {
									return false;
								}
							}	
						}
					}
				}	
			}
		// Same Row, Right
		} else if (targetRow == curRow && targetCol >= curCol + Math.abs(curAisle - targetAisle)) {
			allowMove = allowBasedOnOccupant(targetTile);
			
			// CONSIDER MOVEMENT FROM DIFFERENT AISLES. MUST be on a DIAGONAL from CURRENT AISLE TILE.
			if (targetAisle != curAisle && targetCol > curCol + Math.abs(targetAisle - curAisle)) {
				return false;
			}
			
			// FOLLOW COMMENTS ON [SAME ROW, DOWN]. SAME LOGIC. 
			for (Board b : Main.getBoardList()) {
				for (Tile t : b.getTileList()) {
					if (t.getRow() == curRow && t.getOccupant() != null && t.getOccupant() != this && t.getAisle() == aisle) {
						if (targetAisle != t.getAisle()) {
							if (curCol > targetCol) {
								if (t.getCol() > targetCol + 1 && t.getCol() < curCol - 1) {
									return false;
								}
							} else if (curCol < targetCol){
								if (t.getCol() < targetCol - 1 && t.getCol() > curCol + 1) {
									return false;
								}
							}
						} else {
							if (curCol > targetCol) {
								if (t.getCol() > targetCol && t.getCol() < curCol) {
									return false;
								}
							} else if (curCol < targetCol){
								if (t.getCol() < targetCol && t.getCol() > curCol) {
									return false;
								}
							}	
						}
					}
				}	
			}
		// Same Row, Left
		} else if (targetRow == curRow && targetCol <= curCol - Math.abs(curAisle - targetAisle)) {
			allowMove = allowBasedOnOccupant(targetTile);
			
			// CONSIDER MOVEMENT FROM DIFFERENT AISLES. MUST be on a DIAGONAL from CURRENT AISLE TILE.
			if (targetAisle != curAisle && targetCol < curCol - Math.abs(targetAisle - curAisle)) {
				return false;
			}
			
			// FOLLOW COMMENTS ON [SAME ROW, DOWN]. SAME LOGIC. 
			for (Board b : Main.getBoardList()) {
				for (Tile t : b.getTileList()) {
					if (t.getRow() == curRow && t.getOccupant() != null && t.getOccupant() != this && t.getAisle() == aisle) {
						if (targetAisle != t.getAisle()) {
							if (curCol > targetCol) {
								if (t.getCol() > targetCol + 1 && t.getCol() < curCol - 1) {
									return false;
								}
							} else if (curCol < targetCol){
								if (t.getCol() < targetCol - 1 && t.getCol() > curCol + 1) {
									return false;
								}
							}
						} else {
							if (curCol > targetCol) {
								if (t.getCol() > targetCol && t.getCol() < curCol) {
									return false;
								}
							} else if (curCol < targetCol){
								if (t.getCol() < targetCol && t.getCol() > curCol) {
									return false;
								}
							}
						}
					}
				}	
			}
		}
		// BISHOP MOVEMENT LOGIC
		if (Math.abs(targetCol - curCol) == Math.abs(targetRow - curRow)) {
			// Base movement is ALLOWED. IE the BISHOP is going DOWN / RIGHT AN EVENT NUMBER OF SPACES (DIAGONAL).
			// Method ALLOW sets the variable allow to either true or false depending on the target tiles occupant.
			allowMove = allowBasedOnOccupant(targetTile);
			
			// VARS
			int aisleDif = Math.abs(targetAisle - curAisle);
			
			// THIS IS MOVEMENT FROM DIFFERENT AISLES CASE. LIMITS TO DIAGONAL MOVEMENT FROM DIFFERENT AISLES.
			if (curAisle != targetAisle 
				&& targetRow != curRow + aisleDif 
				&& targetCol != curCol + aisleDif 
				&& targetRow != curRow - aisleDif 
				&& targetCol != curCol - aisleDif) {
					return false;
			}
			
			// LOOP THROUGH THE TILELIST OF ALL BOARDS.
			for (Board b : Main.getBoardList()) {
				for (Tile t : b.getTileList()) {
					// Check for tiles which have pieces on them. Exclude tile which has the caller piece on it.
					if (t.getOccupant() != null && t.getOccupant() != this && t.getAisle() == targetAisle) {
						// Case 1 : Up Right // Algorithm checks Down Left from target.
						if (curCol < targetCol && curRow > targetRow) {
							if (t.getCol() > curCol + aisleDif && t.getRow() < curRow - aisleDif
									&& t.getCol() < targetCol && t.getRow() > targetRow) {
								for (int checkRow = t.getRow(), checkCol = t.getCol(); checkRow <= 7 && checkCol >= 0; checkRow++, checkCol--) {
									// Check if the current tile is along the diagonal.
									if (checkCol == curCol && checkRow == curRow) {
										return false;
									}
								}
							}
						} else if (curCol < targetCol && curRow < targetRow) {
							// Case 2 : Down Right // Algorithm checks Up Left from target.
							if (t.getCol() > curCol + aisleDif && t.getRow() > curRow + aisleDif 
									&& t.getCol() < targetCol && t.getRow() < targetRow) {
								for (int checkRow = t.getRow(), checkCol = t.getCol(); checkRow >= 0 && checkCol >= 0; checkRow--, checkCol--) {
									if (checkCol == curCol && checkRow == curRow) {
										return false;
									}
								}
							}
						} else if (curCol > targetCol && curRow < targetRow) {
							// Case 3 : Down Left // Algorithm checks Up Right from target.
							if (t.getCol() < curCol - aisleDif && t.getRow() > curRow + aisleDif 
									&& t.getCol() > targetCol && t.getRow() < targetRow) {
								for (int checkRow = t.getRow(), checkCol = t.getCol(); checkRow >= 0 && checkCol <= 7; checkRow--, checkCol++) {
									if (checkCol == curCol && checkRow == curRow) {
										return false;
									}
								}
							}
						} else if (curCol > targetCol && curRow > targetRow) {
							// Case 4 : Up Left // Algorithm checks Down Right from target.
							if (t.getCol() < curCol - aisleDif && t.getRow() < curRow - aisleDif 
									&& t.getCol() > targetCol && t.getRow() > targetRow) {
								for (int checkRow = t.getRow(), checkCol = t.getCol(); checkRow <= 7 && checkCol >= 0; checkRow++, checkCol++) {
									if (checkCol == curCol && checkRow == curRow) {
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		return allowMove;
	}
}
