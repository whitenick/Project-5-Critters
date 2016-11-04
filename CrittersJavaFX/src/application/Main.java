package application;
//import assignment5;
	
import java.util.ArrayList;
import java.util.List;

import assignment5.Critter;
import assignment5.InvalidCritterException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import assignment5.CritterWorldGUI;

public class Main extends Application {
	public static GridPane grid = new GridPane();
	//public static Label outputResult = new Label("");
	public static TextArea outputResult = new TextArea();
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			Scene scene = new Scene(grid, 500, 500, Color.BEIGE);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CRITTER COMMANDS");
			
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
			grid.add(makeButton, 0, 3);
			
			//Output Label for Critter Input 
			
			Label outputLabel = new Label("User input Results: ");
			grid.add(outputLabel, 0, 5);
			GridPane.setValignment(outputLabel, VPos.CENTER);
			
			Label userResponse = new Label(null);
			grid.add(userResponse, 1, 5);
			
			Label numberCritters = new Label(null);
			grid.add(numberCritters, 2, 5);
			
			
			
			//Button for making and preparing data for critters 
			
			

			makeButton.setText("Make!");
			makeButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					String className = makeTextField.getText();
					String number = numberTextField.getText();
					int critterCount = 0;
					if(makeTextField.getText() != null && !makeTextField.getText().isEmpty()) {
						//String className = makeTextField.getText();
						userResponse.setText(className);
						
						if (numberTextField.getText() != null && !numberTextField.getText().isEmpty()) {
							if(Integer.parseInt(number)>= 1) {
								critterCount = Integer.parseInt(number);
							}
						}
						
						else {
							critterCount = 1;
						}
						numberCritters.setText(Integer.toString(critterCount));
						}
					for(int i = 0; i<critterCount; i++) {
						try {
							Critter.makeCritter(className);
						} catch (InvalidCritterException j) {
							System.out.println(j.toString());
						}
					}
					}
				}
			);
			
			Label stepNum = new Label("Enter Number of Steps: ");
			grid.add(stepNum, 0, 8);
			
			TextField stepTextField = new TextField();
			grid.add(stepTextField, 1, 8);
			
			Button stepButton = new Button();
			stepButton.setText("Step");
			grid.add(stepButton, 0, 9);
			
			stepButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					int stepCount = 0;
					if(stepTextField.getText() != null && !stepTextField.getText().isEmpty()) {
						String unparsedNumber = stepTextField.getText();
						if(Integer.parseInt(unparsedNumber)>1) {
							stepCount = Integer.parseInt(unparsedNumber);
						}
						else {
							Critter.worldTimeStep();
						}
					}
					for(int i = 0; i<stepCount; i++) {
						Critter.worldTimeStep();
					}
					
				}
			});
			
			Label statsLabel = new Label("Enter class name for stats list: ");
			grid.add(statsLabel, 0, 10);
			
			TextField statsTextField = new TextField();
			grid.add(statsTextField, 1, 10);
			
			Button statsButton = new Button();
			statsButton.setText("Stats");
			grid.add(statsButton, 0, 11);
			
			TextArea statsResult = new TextArea("Stats Results: ");
			grid.add(statsResult, 1, 11);
			outputResult = statsResult;
			
			statsButton.setOnAction(new EventHandler<ActionEvent>() {
				List<Critter> critterList = new ArrayList<Critter>();
				
				@Override
				public void handle(ActionEvent e) {
					if(statsTextField.getText() != null && !statsTextField.getText().isEmpty()) {
						String statsclass = statsTextField.getText();
						try {
							critterList = Critter.getInstances(statsclass);
						} catch(InvalidCritterException r) {
							String returnString = r.toString();
							outputResult.setText(returnString);
						}
					}
					else {
						critterList = Critter.runPopulation();
						Critter.runStats(critterList);
					}
			}
			});
			
			
			//Display GUI
			CritterWorldGUI critterWorld = new CritterWorldGUI();
			Stage secondaryStage = new Stage();
			critterWorld.start(secondaryStage);
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
