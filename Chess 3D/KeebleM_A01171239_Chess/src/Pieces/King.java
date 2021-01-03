package Pieces;

import Board.Board;
import Board.Tile;

public class King extends Piece {
	public King(final Alliance pieceAlliance, final int piecePosition, int row, int col, int aisle, String type) {
		super(pieceAlliance, piecePosition, row, col, aisle, type);
	}
	
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
		
		if (targetRow == curRow && targetCol == curCol) {
			return false;
		}
		
		if (Math.abs(targetAisle - curAisle) != 2) {
			if (targetCol <= curCol + 1 && targetCol >= curCol - 1) {
				if (this.getPieceAlliance() == Alliance.WHITE) {
					if (targetRow <= curRow + 1 && targetRow >= curRow - 1) {
						return allowBasedOnOccupant(targetTile);
					}
				} else if (this.getPieceAlliance() == Alliance.BLACK) {
					if (targetRow <= curRow + 1 && targetRow >= curRow - 1) {
						return allowBasedOnOccupant(targetTile);
					}
				}
			}	
		}
		return allowMove;
	}
}
