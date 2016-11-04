package assignment5;
import application.Main;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import javafx.application.*;




public class CritterWorldGUI extends Application {
	public static GridPane grid = new GridPane();
	
	public CritterWorldGUI() {
		
	}
	
	@Override 
	public void start(Stage primaryStage) {
		try {
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(2);
			grid.setVgap(2);
			grid.setPadding(new Insets(25, 25, 25, 25));
			grid.setGridLinesVisible(false);
			grid.setId("pane");
			
			
			
			grid.setStyle("-fx-background-image: url('https://upload.wikimedia.org/wikipedia/commons/c/ca/Star-forming_region_S106_(captured_by_the_Hubble_Space_Telescope).jpg')");
			double widthConstant = grid.getWidth();
			double heightConstant = grid.getHeight();
			Scene scene = new Scene(grid, widthConstant, heightConstant, Color.AQUA);
			//scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			
			
			//grid.setStyle("-fx-background-color: #C0C0C0;");
			primaryStage.setScene(scene);
			primaryStage.setTitle("CRITTER WORLD");
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
