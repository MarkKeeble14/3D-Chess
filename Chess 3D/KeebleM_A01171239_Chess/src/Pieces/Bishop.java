package Pieces;

import Board.Main;
import Board.Board;
import Board.Tile;
import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Bishop.
 */
public class Bishop extends Piece {
	
	/**
	 * Instantiates a new bishop.
	 *
	 * @param pieceAlliance the piece alliance
	 * @param piecePosition the piece position
	 * @param row the row
	 * @param col the column
	 * @param aisle the aisle
	 * @param type the type
	 */
	public Bishop(final Alliance pieceAlliance, final int piecePosition, int row, int col, int aisle, String type) {
		super(pieceAlliance, piecePosition, row, col, aisle, type);
	}
	
	/**
	 * Validate move.
	 *
	 * @param currentTile the current tile
	 * @param targetTile the target tile
	 * @return true, if successful
	 */
	public boolean validateMove(Tile currentTile, Tile targetTile) {
		// Control Variable
		boolean allowMove = false;
		
		// Current
		int curRow = currentTile.getRow();
		int curCol = currentTile.getCol();
		int curAisle = currentTile.getAisle();
		
		// Target
		int targetRow = targetTile.getRow();
		int targetCol = targetTile.getCol();
		int targetAisle = targetTile.getAisle();
		
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
