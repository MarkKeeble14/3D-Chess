package Board;

import java.util.ArrayList;

import Pieces.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Class Board.
 */
public class Board extends GridPane {
	
	/** The Constant NUM_TILES. */
	public static final int NUM_TILES = 64;
	
	/** The Constant NUM_IN_LINE. */
	public static final int NUM_IN_LINE = 8;
	
	/** The Constant tileSize. */
	public static final int tileSize = Main.HEIGHT / 17; /* Change to reflect size of scene maybe. */
	
	/** The tile list. */
	public Tile[] tileList = new Tile[NUM_TILES]; /* Holds all 64 tiles. */
	
	/** The piece list. */
	public static ArrayList<Piece> pieceList = new ArrayList<Piece>(); /* Holds all 32 pieces, not needed */
	
	/** The active tile. */
	private static Tile activeTile = null; /* Enables movement. The tile which is selected, starting from. */
	
	/** The target tile. */
	private static Tile targetTile = null; /* Enables movement. The tile which is selected; desired to move to. */
	
	/** Check to differentiate between pawn first move? */
	private static boolean check = true;
	
	/** The aisle of the board. */
	private int aisle;
	
	/**
	 * Instantiates a new board.
	 *
	 * @param aisle the aisle
	 */
	// Board Constructor
	public Board(int aisle) {
		this.aisle = aisle;
		// Create Tiles, color tiles, etc.
		for (int row = 0, tile = 0; row < NUM_IN_LINE; row++) {
			Tile s;
			for (int col = 0; col < NUM_IN_LINE; col++, tile++) {
				if (row % 2 != 0) {
					if (col % 2 != 0) {
						s = new Tile(Color.WHITE, row, col, aisle, tile, tileSize);
						 Rectangle r = (Rectangle) s.getChildren().get(0);
						 r.setStroke(Color.BLACK);
					} else {
						s = new Tile(Color.BLACK, row, col, aisle, tile, tileSize);
						 Rectangle r = (Rectangle) s.getChildren().get(0);
						 r.setStroke(Color.BLACK);
					}
				} else {
					if (col % 2 != 0) {
						s = new Tile(Color.BLACK, row, col, aisle, tile, tileSize);
						 Rectangle r = (Rectangle) s.getChildren().get(0);
						 r.setStroke(Color.BLACK);
					} else {
						s = new Tile(Color.WHITE, row, col, aisle, tile, tileSize);
						 Rectangle r = (Rectangle) s.getChildren().get(0);
						 r.setStroke(Color.BLACK);
					}
				}
				tileList[tile] = s;
				this.add(s, col, row); 
			}
		}
	}

	/**
	 * Sets the active tile.
	 *
	 * @param tile the new active tile
	 */
	// Tiles
	public static void setActiveTile(Tile tile) {
		activeTile = tile;
	}
	
	/**
	 * Gets the active tile.
	 *
	 * @return the active tile
	 */
	public static Tile getActiveTile() {
		return activeTile;
	}
	
	/**
	 * Sets the target tile.
	 *
	 * @param tile the new target tile
	 */
	public static void setTargetTile(Tile tile) {
		targetTile = tile;
	}
	
	/**
	 * Gets the target tile.
	 *
	 * @return the target tile
	 */
	public static Tile getTargetTile() {
		return targetTile;
	}
	
	/**
	 * Gets the tile list.
	 *
	 * @return the tile list
	 */
	public Tile[] getTileList() {
		return tileList;
	}
	
	/**
	 * Gets the piece list.
	 *
	 * @return the piece list
	 */
	static public ArrayList<Piece> getPieceList() {
		return pieceList;
	}

	/**
	 * Gets the check.
	 *
	 * @return the check
	 */
	public static boolean getCheck() {
		return check;
	}

	/**
	 * Sets the check.
	 *
	 * @param check the new check
	 */
	public static void setCheck(boolean check) {
		Board.check = check;
	}
	
	/**
	 * Gets the aisle.
	 *
	 * @return the aisle
	 */
	public int getAisle() {
		return aisle;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Board [aisle=" + aisle + "]";
	}
}
