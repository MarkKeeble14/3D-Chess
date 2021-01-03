module ca.bcit.comp1510.examples {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires java.desktop;

	opens Board to javafx.fxml;
	exports Board;
}