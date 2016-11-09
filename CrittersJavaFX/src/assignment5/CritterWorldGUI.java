package assignment5;
import java.util.List;
import assignment5.Critter.CritterShape;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.event.EventHandler;




public class CritterWorldGUI extends Application {
	static int scale = 10;
	private static ScrollPane root;
	private static int closeFlag = 1;
	private static int cWidth = Params.world_width * scale;
	private static int cHeight = Params.world_height * scale;
	
	private static Canvas world = new Canvas();
	private static GraphicsContext gc;


	
	public CritterWorldGUI() {
		
	}
	
	public static boolean isClosed(){
		return closeFlag == 1;
	}
	
	public static void startDisplay(){
		CritterWorldGUI critterWorld = new CritterWorldGUI();
		Stage secondaryStage = new Stage();
		critterWorld.start(secondaryStage);
		closeFlag = 0;
	}
	
	public static void draw(int x, int y, Critter critter){
		CritterShape shape = critter.viewShape();
		Color strokeColor = critter.viewOutlineColor();
		Color fillColor = critter.viewFillColor();
		x *= scale;
		y *= scale;
		
		double[] xPoints;
		double[] yPoints;
		
		gc.setStroke(strokeColor);
		gc.setFill(fillColor);
		
		switch(shape){
			case CIRCLE:
				gc.strokeOval(x, y, scale, scale);
				gc.fillOval(x, y, scale, scale);
				break;
			case SQUARE:
				gc.strokeRect(x, y, scale, scale);
				gc.fillRect(x, y, scale, scale);
				break;
			case TRIANGLE:
				xPoints = new double[3];
				yPoints = new double[3];
				
				xPoints[0] = x;
				xPoints[1] = x + scale;
				xPoints[2] = x + 0.5*scale;
				
				yPoints[0] = y;
				yPoints[1] = y;
				yPoints[2] = y - scale;
				
				gc.strokePolygon(xPoints, yPoints, 3);
				gc.fillPolygon(xPoints, yPoints, 3);
				break;
			case DIAMOND:
				xPoints = new double[4];
				yPoints = new double[4];
				
				xPoints[0] = x;
				xPoints[1] = x + 0.5*scale;
				xPoints[2] = x + scale;
				xPoints[3] = x + 0.5*scale;
				
				yPoints[0] = y;
				yPoints[1] = y + scale;
				yPoints[2] = y;
				yPoints[3] = y - scale;
				
				gc.strokePolygon(xPoints, yPoints, 4);
				gc.fillPolygon(xPoints, yPoints, 4);
				break;
			case STAR:
				xPoints = new double[10];
				yPoints = new double[10];
				
				xPoints[0] = x;
				xPoints[1] = x + 0.25*scale;
				xPoints[2] = x + 0.125*scale;
				xPoints[3] = x + 0.5*scale;
				xPoints[4] = x + 0.875*scale;
				xPoints[5] = x + 0.75*scale;
				xPoints[6] = x + scale;
				xPoints[7] = x + 0.6875*scale;
				xPoints[8] = x + 0.5*scale;
				xPoints[9] = x + 0.3125*scale;
				
				yPoints[0] = y + 0.35*scale;
				yPoints[1] = y + 0.675*scale;
				yPoints[2] = y + scale;
				yPoints[3] = y + 0.85*scale;
				yPoints[4] = y + scale;
				yPoints[5] = y + 0.675*scale;
				yPoints[6] = y + 0.35*scale;
				yPoints[7] = y + 0.35*scale;
				yPoints[8] = y;
				yPoints[9] = y + 0.35*scale;
				
				gc.strokePolygon(xPoints, yPoints, 10);
				gc.fillPolygon(xPoints, yPoints, 10);
				break;
		}
	}

	public static void refresh(){
		paintCanvas(gc);
	}
	
	private static void paintCanvas(GraphicsContext gc){
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, cWidth, cHeight);//Might change
	}
	
	@Override 
	public void start(Stage primaryStage) {
		if((Params.world_height * Params.world_width * 100) < (1620 * 850)){
			scale *= (int) Math.round(Math.sqrt((1620 * 850) / (Params.world_width * Params.world_height * 100)));
		}
		cWidth = Params.world_width * scale;
		cHeight = Params.world_height * scale;
		world = new Canvas(cWidth, cHeight);
		gc = world.getGraphicsContext2D();
		root = new ScrollPane();
		root.setContent(world);
		Scene scene = new Scene(root);
		
		paintCanvas(gc);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("CRITTER WORLD");
		primaryStage.setHeight(850);
		primaryStage.setWidth(1620);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){

			@Override
			public void handle(WindowEvent event) {
				closeFlag = 1;
				
			}
			
		});

	}
}
