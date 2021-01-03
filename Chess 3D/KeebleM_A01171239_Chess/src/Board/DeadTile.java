package Board;

import Pieces.Piece;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Class DeadTile.
 */
public class DeadTile extends StackPane {
	
	/**
	 * Instantiates a new dead tile.
	 *
	 * @param piece the piece
	 */
	public DeadTile(Piece piece) {
		Rectangle r = new Rectangle(Main.HEIGHT / 16, Main.HEIGHT / 16);
		r.setFill(Color.WHITE);
		this.getChildren().addAll(r, piece);
	}
}
