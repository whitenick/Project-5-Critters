package application;
//import assignment5;
	
import assignment5.Critter;
import assignment5.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.*;

public class Main extends Application {
	public static GridPane grid = new GridPane();
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			grid.setGridLinesVisible(false);
			
			Scene scene = new Scene(grid, 500, 500);
			primaryStage.setScene(scene);
			
			//Set text and titling for each of the commands
			Text critterCommands = new Text("Enter your Critter World Command: ");
			critterCommands.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(critterCommands, 0, 0, 2, 1);
			
			Label critterName = new Label("Critter Name: ");
			grid.add(critterName, 0, 1);
			
			Label critterNumber = new Label("# of Critters: ");
			grid.add(critterNumber, 0, 2);
			
			TextField numberTextField = new TextField();
			grid.add(numberTextField, 1, 2);
			
			TextField makeTextField = new TextField();
			grid.add(makeTextField, 1, 1);
			
			Button makeButton = new Button();
			makeButton.setText("Make!");
			
			
			grid.add(makeButton, 0, 3);
			
			Label stepNum = new Label("# of Steps: ");
			grid.add(stepNum, 0, 4);
			
			TextField stepTextField = new TextField();
			grid.add(stepTextField, 1, 4);
			
			Button stepButton = new Button();
			stepButton.setText("Step");
			grid.add(stepButton, 0, 5);
			
			primaryStage.show();
		}
			
		catch (Exception e) {
				e.printStackTrace();
			}
	}
			
			//primaryStage.show();
			
//			Painter.paint();
//			
//			primaryStage.setTitle("Critters World");
//			Button butn = new Button();
//			Button make = new Button();
//			
//			//Implement make button to prompt for name of Critter to make 
//			//@throws 
//			String makeCritter = null;
//			make.setText("make");
//			make.setOnAction(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event){
//					try{
//						Critter.makeCritter(makeCritter);
//						System.out.println("make");
//					}
//					catch (InvalidCritterException e){
//						
//					}
//				}
//			});
//			butn.setText("Press to play");
//			butn.setOnAction(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event){
//					System.out.println("Hello, world");
//				}
//			});
//			StackPane stage = new StackPane();
//			stage.getChildren().add(butn);
//			primaryStage.setScene(new Scene(stage, 300, 250));
//			primaryStage.show();
//			BorderPane root = new BorderPane();
//			root.getChildren().add(butn);
//			root.getChildren().add(make);
//			scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	public static void main(String[] args) {
		launch(args);
	}
}
