package Pieces;

import Board.Main;
import Board.Tile;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

// TODO: Auto-generated Javadoc
/**
 * The Class Piece.
 */
public abstract class Piece extends Button {
	
	/** The piece position. */
	protected int piecePosition;
    
    /** The piece alliance. */
    protected final Alliance pieceAlliance;
	
	/** The row. */
	protected int row;
	
	/** The col. */
	protected int col;
	
	/** The aisle. */
	protected int aisle;
	
	/** The type. */
	protected String type;
	
	/**
	 * Instantiates a new piece.
	 *
	 * @param pieceAlliance the piece alliance
	 * @param piecePosition the piece position
	 * @param row the row
	 * @param col the col
	 * @param aisle the aisle
	 * @param type the type
	 */
    Piece(final Alliance pieceAlliance, final int piecePosition, int row, int col, int aisle, final String type) {
        this.setPrefSize(Main.HEIGHT / 24, Main.HEIGHT / 24);
    	this.setFont(new Font(Main.HEIGHT / 96));
        
    	this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        this.row = row;
        this.col = col;
        this.aisle = aisle;
        this.type = type;
        
        this.setText(type);
        
        // Determine button color and border color.
        if (pieceAlliance == Alliance.BLACK) {
        	this.setTextFill(Color.WHITE);
        	this.setStyle("-fx-background-color: "+pieceAlliance+"; -fx-border-color: white;");
        } else {
        	this.setTextFill(Color.BLACK);
        	this.setStyle("-fx-background-color: "+pieceAlliance+"; -fx-border-color: black;");
        }
    }
	
    /**
     *  Method used to validate moves. Overridden by piece classes.
     *
     * @param currentTile the current tile
     * @param targetTile the target tile
     * @return true, if successful
     */
    abstract public boolean validateMove(Tile currentTile, Tile targetTile);

    /**
     *  Determines whether a tile is viable to move to, in terms of occupants.
     *
     * @param t the t
     * @return true, if successful
     */
    protected boolean allowBasedOnOccupant(Tile t) {
		if (t.getOccupant() != null) {
			if (t.getOccupant().getPieceAlliance() != this.getPieceAlliance()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}	
	}
    
    /**
     * Sets the coords.
     *
     * @param row the row
     * @param col the col
     * @param aisle the aisle
     * @param tileCoordinate the tile coordinate
     */
    public void setCoords(int row, int col, int aisle, int tileCoordinate) {
		this.row = row;
		this.col = col;
		this.aisle = aisle;
		this.piecePosition = tileCoordinate;
	}
    
    /**
     * Gets the piece position.
     *
     * @return the piece position
     */
    public int getPiecePosition() {
        return this.piecePosition;
    }

    /**
     * Gets the row.
     *
     * @return the row
     */
    public int getRow() {
		return row;
	}

	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
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
	 * Gets the piece alliance.
	 *
	 * @return the piece alliance
	 */
	public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    
    /**
     * Gets the piece type.
     *
     * @return the piece type
     */
    public String getPieceType() {
    	return this.getText();
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return  "[pieceType=" + type + ", piecePosition=" + piecePosition + ", pieceAlliance=" + pieceAlliance 
				+ ", row=" + row + ", col=" + col + ", aisle=" + aisle + "]";
	}
}
