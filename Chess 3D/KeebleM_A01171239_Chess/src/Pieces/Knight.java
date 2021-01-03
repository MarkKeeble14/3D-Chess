package Pieces;

import Board.Board;
import Board.Tile;

public class Knight extends Piece {
	public Knight(final Alliance pieceAlliance, final int piecePosition, int row, int col, int aisle, String type) {
		super(pieceAlliance, piecePosition, row, col, aisle, type);
	}
	
	public boolean validateMove(Tile currentTile, Tile targetTile) {
		boolean allowMove = false;
		// Current
		int curRow = currentTile.getRow();
		int curCol = currentTile.getCol();
		
		// Target
		int targetRow = targetTile.getRow();
		int targetCol = targetTile.getCol();
		
		if (Math.abs(targetCol - curCol) == 2 && Math.abs(targetRow - curRow) == 1) {
			allowMove = allowBasedOnOccupant(targetTile);
		} else if (Math.abs(targetRow - curRow) == 2 && Math.abs(targetCol - curCol) == 1) {
			allowMove = allowBasedOnOccupant(targetTile);
		}
		return allowMove;
	}
}
