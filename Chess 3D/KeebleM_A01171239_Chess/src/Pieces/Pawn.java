package Pieces;

import java.util.ArrayList;
import java.util.List;

import Board.Main;
import Board.Board;
import Board.Tile;

// TODO: Auto-generated Javadoc
/**
 * The Class Pawn.
 */
public class Pawn extends Piece {
	
	/**
	 * Instantiates a new pawn.
	 *
	 * @param pieceAlliance the piece alliance
	 * @param piecePosition the piece position
	 * @param row the row
	 * @param col the col
	 * @param aisle the aisle
	 * @param type the type
	 */
	public Pawn(final Alliance pieceAlliance, final int piecePosition, int row, int col, int aisle, String type) {
		super(pieceAlliance, piecePosition, row, col, aisle, type);
		
		// Set Pawns First Move.
		firstMove = true;
	}

	/** The first move. */
	private boolean firstMove;

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
		
		int maxDist = 2;

		// PAWN MOVEMENT LOGIC
		// SAME COLUMN
		if (this == Board.getActiveTile().getOccupant()) {
			if (targetCol == curCol) {
				// FIRST MOVE
				if (firstMove == true) {
					// WHITE PIECE / MUST MOVE DOWN
					if (this.getPieceAlliance() == Alliance.WHITE) {
						if (targetRow > curRow && targetRow == curRow + targetAisle && Math.abs(targetRow - curRow) < maxDist + 1
								|| (targetAisle == curAisle && targetRow <= curRow + 2) && targetRow > curRow) {
							if (Board.getCheck() == false) {
								firstMove = false;
								maxDist = 1;
							}
							if (targetCol == curCol) {
								if (targetTile.getOccupant() == null) {
									allowMove = allowBasedOnOccupant(targetTile);
									for (Board b : Main.getBoardList()) {
										for (Tile t : b.getTileList()) {
											if (t.getCol() == curCol && t.getOccupant() != null && t.getOccupant() != this) {
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
							}
						}
					// BLACK PIECE / MUST MOVE UP
					} else if (this.getPieceAlliance() == Alliance.BLACK) {
						if (targetRow < curRow && targetRow == curRow - targetAisle && Math.abs(targetRow - curRow) < maxDist + 1
								|| (targetAisle == curAisle && targetRow >= curRow - 2) && targetRow < curRow) {
							if (Board.getCheck() == false) {
								firstMove = false;
								maxDist = 1;
							}
							if (targetCol == curCol) {
								if (targetTile.getOccupant() == null) {
									allowMove = allowBasedOnOccupant(targetTile);
									for (Board b : Main.getBoardList()) {
										for (Tile t : b.getTileList()) {
											if (t.getCol() == curCol && t.getOccupant() != null && t.getOccupant() != this && t.getAisle() == targetAisle) {
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
							}
						}
					} 
				} else {
					// Not First Move.
					if (Math.abs(targetAisle - curAisle) < maxDist) {
						if (targetTile.getOccupant() == null) {
							if (this.getPieceAlliance() == Alliance.WHITE) {
								if (targetRow > curRow && targetRow <= curRow + 1) {
									return allowBasedOnOccupant(targetTile);
								}
							} else if (this.getPieceAlliance() == Alliance.BLACK) {
								if (targetRow < curRow && targetRow >= curRow - 1) {
									return allowBasedOnOccupant(targetTile);
								}
							}
						}	
					}
					
				}
			} else if (targetTile.getOccupant() != null // Attack Case. Diagonal movement up to 1 block away.
					&& targetTile.getOccupant().getPieceAlliance() != this.getPieceAlliance()
					&& Math.abs(targetAisle - curAisle) != 2) {
				if (targetCol <= curCol + 1 && targetCol >= curCol - 1 && targetCol != curCol) {
					if (this.getPieceAlliance() == Alliance.WHITE) {
						if (targetRow > curRow && targetRow <= curRow + 1) {
							return allowBasedOnOccupant(targetTile);
						}
					} else if (this.getPieceAlliance() == Alliance.BLACK) {
						if (targetRow < curRow && targetRow >= curRow - 1) {
							return allowBasedOnOccupant(targetTile);
						}
					}
				}
			}
		}
		return allowMove;
	}

	/**
	 * Checks if is first move.
	 *
	 * @return true, if is first move
	 */
	public boolean isFirstMove() {
		return firstMove;
	}

	/**
	 * Sets the first move.
	 *
	 * @param firstMove the new first move
	 */
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
}