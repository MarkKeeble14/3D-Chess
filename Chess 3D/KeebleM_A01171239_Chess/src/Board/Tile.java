package Board;

import Pieces.*;
import javafx.event.ActionEvent;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The Class Tile.
 */
public class Tile extends StackPane {
	
	/** The color. */
	private Color color;
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/** The aisle. */
	private int aisle;
	
	/** The r. */
	private Rectangle r;
	
	/** The tile coordinate. */
	protected final int tileCoordinate;

	/**
	 * The PIECE currently OCCUPYING the TILE.
	 */
	private Piece occupant;

	/**
	 * Instantiates a new tile.
	 *
	 * @param color the color
	 * @param row the row
	 * @param col the col
	 * @param aisle the aisle
	 * @param tileCoord the tile coord
	 * @param tileSize the tile size
	 */
	public Tile(Color color, int row, int col, int aisle, int tileCoord, int tileSize) {
		r = new Rectangle();
		this.color = color;
		this.row = row;
		this.col = col;
		this.aisle = aisle;
		this.tileCoordinate = tileCoord;

		// Setting graphical components.
		r.setFill(color);
		r.setWidth(tileSize);
		r.setHeight(tileSize);
		r.setX(row * tileSize);
		r.setY(col * tileSize);
		
		// Event Listener
		// Tragedy Unfolds.
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			if (Main.getTurn() % 2 == 1) {
				if (Board.getActiveTile() == null && this.getOccupant() != null
						&& this.getOccupant().getPieceAlliance() == Alliance.WHITE) { // Selecting active tile.
					System.out.println("Active Tile set to " + this.toString() + "\n");
					Board.setActiveTile(this);
					r.setFill(Color.RED);
					
					// Validation Algorithm
					validationAlgorithm();
				} else if (Board.getActiveTile() != null) { // Active tile case.
					if (this.getOccupant() == null) {
						// Clicked on empty space.
						Board.setTargetTile(this);
						System.out.println("The space is empty.\nTarget Tile: " + Board.getTargetTile().toString() + "\n");
						Piece curPiece = Board.getActiveTile().getOccupant();
						
						// Validate Move
						if (Board.getTargetTile().getR().getFill() == Color.BLUE) {
							// First Move
							checkPawnFirstMove(curPiece);
							
							// Swap
							System.out.println(curPiece.toString() + " moved to " + Board.getTargetTile().toString() + "\n");
							swapNotOccupied(curPiece);
							
							validMoveMade();
						} else if (Board.getActiveTile() != null) {
							Board.getTargetTile().setOccupant(null);
							invalidMoveAttempted();
						}
					} else if (this.getOccupant() != null) {
						// Case which deals with Different Alliances
						// Clicked on non-empty space.
						Board.setTargetTile(this);
						System.out.println("Target Tile: " + this.toString() + "\n");
						
						Piece activePiece = Board.getActiveTile().getOccupant();
						// Validate Move
						if (Board.getTargetTile().getR().getFill() == Color.BLUE) {
							// Game Ends
							if (this.getOccupant().getText() == "King") {
								Main.root.getChildren().removeAll(Main.p1, Main.p1, Main.r, Main.t);
								
								// Remove Boards
								for (Board b : Main.getBoardList()) {
									Main.root.getChildren().remove(b);
								}
								Rectangle r2 = new Rectangle(Main.WIDTH, Main.HEIGHT);
								r2.setFill(Color.WHITE);
								Text t2 = new Text("Black Wins!");
								t2.setFill(Color.BLACK);
								t2.setFont(Font.font(24));
								StackPane s2 = new StackPane();
								s2.getChildren().addAll(r2, t2);
								Main.root.getChildren().addAll(s2);
							}
							// First Move
							checkPawnFirstMove(activePiece);
							
							// Swap
							System.out.println(activePiece.toString() + " moved to " + Board.getTargetTile().toString()
									+ ". " + Board.getTargetTile().getOccupant() + " taken.\n");
							swapOccupied(activePiece);
							validMoveMade();
						} else {
							invalidMoveAttempted();
						}
					}
				}
			} else if (Main.getTurn() % 2 != 1) {
				if (Board.getActiveTile() == null && this.getOccupant() != null
						&& this.getOccupant().getPieceAlliance() == Alliance.BLACK) { // Selecting active tile.
					// Set Active Tile.
					Board.setActiveTile(this);
					System.out.println("Active Tile set to " + this.toString() + "\n");
					
					r.setFill(Color.RED);
					// Validation Algorithm
					validationAlgorithm();
				} else if (Board.getActiveTile() != null) { // Active tile case.
					if (this.getOccupant() == null) {
						Board.setTargetTile(this);
						System.out.println("The space is empty.\nTarget Tile: " + Board.getTargetTile().toString() + "\n");
						
						Piece curPiece = Board.getActiveTile().getOccupant();
						
						// If the rectangles color is blue, it has been validated.
						if (Board.getTargetTile().getR().getFill() == Color.BLUE) {					
							// If the current piece is a pawn, do the correct thing with it's firstName member.
							checkPawnFirstMove(curPiece);
							
							// Swap
							System.out.println(curPiece.toString() + " moved to " + Board.getTargetTile().toString());
							swapNotOccupied(curPiece);
							
							// Reset
							validMoveMade();
						} else if (Board.getActiveTile() != null) {
							Board.getTargetTile().setOccupant(null);
							
							// Deselect
							invalidMoveAttempted();
						}
					} else if (this.getOccupant() != null) {
						System.out.println("Clicked on non-empty space\n");
						Board.setTargetTile(this);
						System.out.println("Target Tile: " + this.toString() + "\n");
						
						// Different Alliances
						Piece activePiece = Board.getActiveTile().getOccupant();

						if (Board.getTargetTile().getR().getFill() == Color.BLUE) {
							// Game Ends
							if (this.getOccupant().getText() == "King") {
								Main.root.getChildren().removeAll(Main.p1, Main.p1, Main.r, Main.t);
								for (Board b : Main.getBoardList()) {
									Main.root.getChildren().remove(b);
								}
								Rectangle r2 = new Rectangle(Main.WIDTH, Main.HEIGHT);
								r2.setFill(Color.BLACK);
								Text t2 = new Text("White Wins!");
								t2.setFill(Color.WHITE);
								t2.setFont(Font.font(24));
								StackPane s2 = new StackPane();
								s2.getChildren().addAll(r2, t2);
								Main.root.getChildren().addAll(s2);
							}
							
							// First Move
							checkPawnFirstMove(activePiece);
							
							// Swap
							System.out.println(activePiece.toString() + " moved to " + Board.getTargetTile().toString()
									+ ". " + Board.getTargetTile().getOccupant() + " taken.\n");
							swapOccupied(activePiece);
							
							validMoveMade();
						} else {
							invalidMoveAttempted();
						}
					}
				}
			}
		});

		// Add rectangle to the Tile (StackPane).
		this.getChildren().add(r);
		
		// Add pieces to starting positions.
		if (aisle == 0) {
			try {
				Piece piece = addPiece(row, tileCoord);
				Board.getPieceList().add(piece);
				this.setOccupant(piece);
				this.getChildren().add(piece);
			} catch (Exception e) {
				// Exception for when tile is null.
				// Maybe can fix easy, try later.
			}	
		}
		// End of Constructor
	}

	/**
	 * Unused.
	 *
	 * @param event the event
	 */
	public void processButtonPress(ActionEvent event) {
	    Main.restart();
	  }
	
	/**
	 * Return a piece depending on the given coords. 
	 *
	 * @param row the row
	 * @param tileCoord the tile coord
	 * @return the piece
	 */
	public Piece addPiece(int row, int tileCoord) {
		Piece b;
		// Setting pieces, depending on tileCoordinate.
		// NOT PAWNS
		if (row != 1 && row != 6) {
			switch (tileCoord) {
			case 0:
				return new Rook(Alliance.WHITE, tileCoord, row, col, aisle, "Rook");
			case 1:
				return new Knight(Alliance.WHITE, tileCoord, row, col, aisle, "Knight");
			case 2:
				return new Bishop(Alliance.WHITE, tileCoord, row, col, aisle, "Bishop");
			case 3:
				return new Queen(Alliance.WHITE, tileCoord, row, col, aisle,  "Queen");
			case 4:
				return new King(Alliance.WHITE, tileCoord, row, col, aisle,  "King");
			case 5:
				return new Bishop(Alliance.WHITE, tileCoord, row, col, aisle,  "Bishop");
			case 6:
				return new Knight(Alliance.WHITE, tileCoord, row, col, aisle,  "Knight");
			case 7:
				return new Rook(Alliance.WHITE, tileCoord, row, col, aisle,  "Rook");
			case 56:
				return new Rook(Alliance.BLACK, tileCoord, row, col, aisle,  "Rook");
			case 57:
				return new Knight(Alliance.BLACK, tileCoord, row, col, aisle,  "Knight");
			case 58:
				return new Bishop(Alliance.BLACK, tileCoord, row, col, aisle,  "Bishop");
			case 59:
				return new Queen(Alliance.BLACK, tileCoord, row, col, aisle,  "Queen");
			case 60:
				return new King(Alliance.BLACK, tileCoord, row, col, aisle,  "King");
			case 61:
				return new Bishop(Alliance.BLACK, tileCoord, row, col, aisle,  "Bishop");
			case 62:
				return new Knight(Alliance.BLACK, tileCoord, row, col, aisle,  "Knight");
			case 63:
				return new Rook(Alliance.BLACK, tileCoord, row, col, aisle,  "Rook");
			}
		} else {
			// PAWNS
			if (tileCoord <= 15 && tileCoord >= 8) {
				b = new Pawn(Alliance.WHITE, tileCoord, row, col, aisle,  "Pawn");
				return b;
			} else if (tileCoord <= 55 && tileCoord >= 48) {
				b = new Pawn(Alliance.BLACK, tileCoord, row, col, aisle,  "Pawn");
				return b;
			}
		}
		return null;
	}

	/**
	 * Resets board from opposing turn.
	 */
	private void validMoveMade() {
		invalidMoveAttempted();
		Main.swapTurn();
	}

	/**
	 * Resets from current turn.
	 */
	private void invalidMoveAttempted() {
		Tile t = Board.getActiveTile();
		Rectangle r = null;

		try {
			for (int i = t.getChildren().size(); i > 0; i--) {
				try {
					if (r == null) {
						r = (Rectangle) t.getChildren().get(t.getChildren().size() - i);
					}
				} catch (ClassCastException e) {
					System.out.println("Err");
				}
			}

			if (t.row % 2 != 0) {
				if (t.col % 2 != 0) {
					r.setFill(Color.WHITE);
				} else {
					r.setFill(Color.BLACK);
				}
			} else {
				if (t.col % 2 != 0) {
					r.setFill(Color.BLACK);
				} else {
					r.setFill(Color.WHITE);
				}
			}
		} catch (NullPointerException e) {
			;
		}

		for (Board b : Main.getBoardList()) {
			for (Tile resetT : b.getTileList()) {
				resetT.getR().setFill(resetT.getBaseColor());
			}
		}
		
		Board.setActiveTile(null);
		Board.setTargetTile(null);
	}

	/**
	 * Check pawn first move.
	 *
	 * @param activePiece the active piece
	 */
	private void checkPawnFirstMove(Piece activePiece) {
		try {
			if (activePiece.getPieceType() == "Pawn") {
				Pawn p = (Pawn)activePiece;
				if (p.isFirstMove() == true) {
					p.setFirstMove(false);
				}
			}
		} catch (ClassCastException cce) {
			;
		}
	}
	
	/**
	 * Swap occupied.
	 *
	 * @param activePiece the active piece
	 */
	private void swapOccupied(Piece activePiece) {
		Board.getTargetTile().getChildren().add(Board.getTargetTile().getChildren().size(),
				activePiece);
		Board.getActiveTile().getChildren().remove(activePiece);
		this.getChildren().remove(this.getOccupant());
		DeadTile d = new DeadTile(this.getOccupant());
		Main.p2.getChildren().add(d);
		
		// Setting / Removing pieces
		Board.getTargetTile().setOccupant(Board.getActiveTile().getOccupant());
		activePiece.setCoords(Board.getTargetTile().getRow(), Board.getTargetTile().getCol(), Board.getTargetTile().getAisle(),
				Board.getTargetTile().getTileCoordinate());
		Board.getActiveTile().setOccupant(null);
	}
	
	/**
	 * Swap not occupied.
	 *
	 * @param curPiece the cur piece
	 */
	private void swapNotOccupied(Piece curPiece) {
		Board.getTargetTile().setOccupant(Board.getActiveTile().getOccupant());

		Piece activePiece = (Piece) Board.getActiveTile().getChildren()
				.get(Board.getTargetTile().getChildren().size());
		Board.getTargetTile().getChildren().add(Board.getTargetTile().getChildren().size(),
				activePiece);
		Board.getActiveTile().getChildren().remove(activePiece);

		// Setting / Removing pieces
		Board.getTargetTile().setOccupant(Board.getActiveTile().getOccupant());
		activePiece.setCoords(Board.getTargetTile().getRow(), Board.getTargetTile().getCol(), Board.getTargetTile().getAisle(),
				Board.getTargetTile().getTileCoordinate());
		Board.getActiveTile().setOccupant(null);
	}
	
	/**
	 * Validation algorithm.
	 */
	private void validationAlgorithm() {
		Piece thisPiece = Board.getActiveTile().getOccupant();
		for (Board b : Main.getBoardList()) {
			Board.setCheck(true);
			for (Tile t : b.getTileList()) {
				if (thisPiece.validateMove(this, t)) {
					t.getR().setFill(Color.BLUE);
				}
			}
			Board.setCheck(false);
		}
	}
	
	/**
	 * Gets the occupant.
	 *
	 * @return the occupant
	 */
	public Piece getOccupant() {
		return occupant;
	}

	/**
	 * Sets the occupant.
	 *
	 * @param piece the new occupant
	 */
	public void setOccupant(Piece piece) {
		occupant = piece;
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
	 * Gets the tile coordinate.
	 *
	 * @return the tile coordinate
	 */
	public int getTileCoordinate() {
		return tileCoordinate;
	}
	
	/**
	 * Gets the base color.
	 *
	 * @return the base color
	 */
	public Color getBaseColor() {
		return color;
	}
	
	/**
	 * Gets the r.
	 *
	 * @return the r
	 */
	public Rectangle getR() {
		return r;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Tile [row=" + row + ", col=" + col + ", aisle=" + aisle + ", tileCoordinate=" + tileCoordinate + ", occupant=" + occupant
				+ "]";
	}
}
