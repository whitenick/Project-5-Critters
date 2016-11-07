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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {
	private static BorderPane root = new BorderPane();
	public static GridPane grid = new GridPane();
	private static GridPane bGrid = new GridPane();
	private static GridPane rGrid = new GridPane();
	public static Label outputResult = new Label();
	
	private static void initCenter(){
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		//Set text and titling for each of the commands
		Text critterCommands = new Text("Enter your Critter World Command: ");
		critterCommands.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(critterCommands, 0, 0, 2, 1);
		
		Label critterName = new Label("Critter Name: ");
		grid.add(critterName, 0, 1);
		
		Label critterNumber = new Label("# of Critters: ");
		grid.add(critterNumber, 0, 2);
		
		TextField makeTextField = new TextField();
		grid.add(makeTextField, 1, 1);
		
		TextField numberTextField = new TextField();
		grid.add(numberTextField, 1, 2);
		
		Button makeButton = new Button();
		grid.add(makeButton, 0, 3);
		
		
		//Button for making and preparing data for critters 
		
		makeButton.setText("Make!");
		makeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String className = makeTextField.getText();
				String number = numberTextField.getText();
				int critterCount = 0;
				if(makeTextField.getText() != null && !makeTextField.getText().isEmpty()) {
					
					if (numberTextField.getText() != null && !numberTextField.getText().isEmpty()) {
						if(Integer.parseInt(number)>= 1) {
							critterCount = Integer.parseInt(number);
						}
					}
					
					else {
						critterCount = 1;
					}
				}
				for(int i = 0; i<critterCount; i++) {
					try {
						Critter.makeCritter(className);
					} catch (InvalidCritterException j) {
						System.out.println(j.toString());
					}
				}
			}
		});
		
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
	}
	
	private static void initBottom(){
		bGrid.setAlignment(Pos.CENTER);
		bGrid.setHgap(10);
		bGrid.setVgap(10);
		
		Button showButton = new Button();
		bGrid.add(showButton, 0, 0);
		
		showButton.setText("Show World");
		showButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Critter.displayWorld();
			}
			}
		);
	}
	
	private static void initRight(){
		rGrid.setAlignment(Pos.TOP_CENTER);
		rGrid.setHgap(10);
		rGrid.setVgap(10);
		
		GridPane subGrid = new GridPane();
		Label statsLabel = new Label("Enter Critter for Stats: ");
		subGrid.add(statsLabel, 0, 0);
		
		TextField statsTextField = new TextField();
		subGrid.add(statsTextField, 1, 0);
		
		Button statsBt = new Button();
		statsBt.setText("Stats");
		
		ScrollPane scroll = new ScrollPane();
		Label statsResult = new Label("Stats Results: \n");
		outputResult = statsResult;
		scroll.setContent(outputResult);
		
		statsBt.setOnAction(new EventHandler<ActionEvent>() {
			List<Critter> critterList = new ArrayList<Critter>();
			
			@Override
			public void handle(ActionEvent e) {
				if(statsTextField.getText() != null && !statsTextField.getText().isEmpty()) {
					String statsclass = statsTextField.getText();
					try {
						critterList = Critter.getInstances(statsclass);
						Critter.runStats(critterList);
					} catch(InvalidCritterException r) {
						String returnString = r.toString();
						outputResult.setText(returnString);
					}
				}
				else {
					critterList = Critter.runPopulation();
					Critter.runStats(critterList);
				}
				scroll.setVvalue(scroll.getVmax());
			}
		});
		
		rGrid.add(subGrid, 0, 0);
		rGrid.add(statsBt, 0, 1);
		rGrid.add(scroll, 0, 2);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("CRITTER COMMANDS");
		root.setPadding(new Insets(20, 0, 0, 20));
		BorderPane.setMargin(bGrid, new Insets(20));
		BorderPane.setMargin(rGrid, new Insets(0, 20, 20, 20));
		
		initCenter();
		root.setCenter(grid);
		
		initBottom();
		root.setBottom(bGrid);
		
		initRight();
		root.setRight(rGrid);
		
		Scene scene = new Scene(root, 800, 500, Color.BEIGE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
