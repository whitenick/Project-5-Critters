package assignment5;
import java.util.List;
import assignment5.Critter.CritterShape;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.application.*;




public class CritterWorldGUI extends Application {
	static int scale = 8;
	private static ScrollPane root = new ScrollPane();
	private int cWidth = Params.world_width * scale;
	private int cHeight = Params.world_height * scale;
	
	public CritterWorldGUI() {
		
	}
	
	private static void paintCanvas(GraphicsContext gc){
		gc.clearRect(0, 0, Params.world_width * scale, Params.world_height * scale);
		List<Critter> critters = Critter.runPopulation();
		
		for(int i = 0; i < critters.size(); i++){
			CritterShape shape = critters.get(i).viewShape();
			
			//TODO:Finish other cases
			switch(shape){
				case CIRCLE:
					gc.setFill(critters.get(i).viewColor());
					gc.fillOval(critters.get(i).x_coord, critters.get(i).y_coord, 5, 5);
					break;
				case SQUARE:
					gc.setFill(Color.BLACK);
					gc.fillRect(scale * critters.get(i).x_coord, scale * critters.get(i).y_coord, scale, scale);
					break;
				case TRIANGLE:
					gc.setFill(critters.get(i).viewColor());
					gc.fillOval(critters.get(i).x_coord, critters.get(i).y_coord, 5, 5);
					break;
				case DIAMOND:
					gc.setFill(critters.get(i).viewColor());
					gc.fillOval(critters.get(i).x_coord, critters.get(i).y_coord, 5, 5);
					break;
				case STAR:
					gc.setFill(critters.get(i).viewColor());
					gc.fillOval(critters.get(i).x_coord, critters.get(i).y_coord, 5, 5);
					break;
			}
		}
	}
	
	@Override 
	public void start(Stage primaryStage) {
		//TODO:Set Background WHITE
		Canvas world = new Canvas(cWidth, cHeight);
		root.setContent(world);
		Scene scene = new Scene(root);
		GraphicsContext gc = world.getGraphicsContext2D();
		
		paintCanvas(gc);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("CRITTER WORLD");
		primaryStage.show();
	}
}
