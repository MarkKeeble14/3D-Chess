package Board;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Application {
	
	/** The Constant NUM_BOARDS. */
	private static final int NUM_BOARDS = 3;
	
	/** The rectangle. */
	public static Rectangle r;
	
	/** The text. */
	public static Text t;
	
	/** The first piece collection. */
	public static FlowPane p1;
	
	/** The second piece collection. */
	public static FlowPane p2;
	
	/** The group. */
	public static FlowPane root = new FlowPane();
	
	/** The Constant HEIGHT. */
	public static final int HEIGHT = 768;
	
	/** The Constant WIDTH. */
	public static final int WIDTH = HEIGHT / 16 * 27;
	
	/** The turn. */
	private static int turn = 1;
	
	/** The board list. */
	public static ArrayList<Board> boardList = new ArrayList<Board>();
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Start.
	 *
	 * @param arg0 the arg 0
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		begin();
		
		// JavaFX cleanup
	    final double appWidth = WIDTH;
	    final int appHeight = HEIGHT;
	    Scene scene = new Scene(root, appWidth, appHeight, Color.WHITE);
	        
	    arg0.setTitle("Chess");
	    arg0.setScene(scene);
	    arg0.show();		
	}
	
	/**
	 * Restart.
	 */
	public static void restart() {
		;
	}
	
	/**
	 * Begin.
	 */
	public static void begin() {
		float xPos = 0f;
		
		// Instantiate 3 boards.
		for (int i = 0; i < NUM_BOARDS; i++) {
			Board board = new Board(i);
			System.out.println(board.toString() + " INSTANTIATED.\n");
			board.setStyle("-fx-border-color: black");
			
			getBoardList().add(board);
			root.getChildren().add(board);
		}
		
		// Set up view
		// Piece Collections
		// P1
		p1 = new FlowPane();
		p1.setTranslateX(0);
		p1.setTranslateY(HEIGHT - Main.HEIGHT / 8);
		p1.setMinHeight(Main.HEIGHT / 4);
		p1.setMinWidth(WIDTH / 3);
		p1.setStyle("-fx-background-color: black");
		
		// P2
		p2 = new FlowPane();
		p2.setTranslateX(0);
		p2.setTranslateY(HEIGHT - Main.HEIGHT / 4);
		p2.setMinHeight(Main.HEIGHT / 4);
		p2.setMinWidth(WIDTH / 3);
		p2.setStyle("-fx-background-color: white");
		
		
		// StackPane
		// R
		r = new Rectangle(HEIGHT / 4, HEIGHT / 3);
		r.setFill(Color.WHITE);
		r.setStroke(Color.BLACK);
		
		// T
		t = new Text("Turn");
		t.setFill(Color.BLACK);
		t.setFont(new Font(HEIGHT / 32));
				
		// S
		StackPane s = new StackPane();
		s.setTranslateX(WIDTH - (HEIGHT / 4));
		s.setTranslateY(HEIGHT - HEIGHT / 3);
		s.getChildren().addAll(r, t);
		
		// Add to group.
		root.getChildren().add(s);
		root.getChildren().add(p1);
		root.getChildren().add(p2);
	}
	
	/**
	 * Gets the board list.
	 *
	 * @return the board list
	 */
	public static ArrayList<Board> getBoardList() {
		return boardList;
	}
	
	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public static int getTurn() {
		return turn;
	}
	
	/**
	 * Swaps the turns.
	 */
	public static void swapTurn() {
		if (Main.getTurn() % 2 != 1) {
			r.setFill(Color.WHITE);
			t.setFill(Color.BLACK);
		} else {
			r.setFill(Color.BLACK);
			t.setFill(Color.WHITE);
		}
		System.out.println("TURN SWAP\n");
		turn++;
	}
}
