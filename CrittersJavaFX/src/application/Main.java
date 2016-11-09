package application;
//import assignment5;
	
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import javax.swing.event.ChangeListener;

import assignment5.Critter;
import assignment5.InvalidCritterException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
//import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;


public class Main extends Application {
	private static final Pos CENTER = null;
	private static BorderPane root = new BorderPane();
	public static GridPane grid = new GridPane();
	private static GridPane bGrid = new GridPane();
	private static GridPane rGrid = new GridPane();
	private static BorderPane bottomPane = new BorderPane();
	public static Label outputResult = new Label();
	public static Number sliderValue = 0;
	public static TextField globalStatsText = new TextField();
	
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
		
		Label errorProcessing = new Label();
		errorProcessing.setFont(Font.font("Tahoman", FontWeight.NORMAL, 20));
		grid.add(errorProcessing, 0, 11);
		errorProcessing.setAlignment(Pos.CENTER);
		
		
		//Button for making and preparing data for critters 
		
		makeButton.setText("Make!");
		makeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				errorProcessing.setText(null);
				String className = makeTextField.getText();
				String number = numberTextField.getText();
				Scanner numberScan = new Scanner(number);
				int critterCount = 0;
				if(makeTextField.getText() != null && !makeTextField.getText().isEmpty()) {
					
					if (numberTextField.getText() != null && !numberTextField.getText().isEmpty() && numberScan.hasNextInt()) {
						if(Integer.parseInt(number)>= 1) {
							critterCount = Integer.parseInt(number);
						}
					}
					
					else if (numberTextField.getText() == null) {
						critterCount = 1;
					}
					
					else {
						errorProcessing.setText("Invalid Input");
					}
					
					
				}
				else {
					errorProcessing.setText("Invalid Input");
				}
				for(int i = 0; i<critterCount; i++) {
					try {
						Critter.makeCritter(className);
					} catch (InvalidCritterException j) {
						errorProcessing.setText(j.toString());
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
				errorProcessing.setText(null);
				int stepCount = 1;
				String unparsedNumber = stepTextField.getText();
				Scanner numberScan = new Scanner(unparsedNumber);
				if(stepTextField.getText() != null && !stepTextField.getText().isEmpty() && numberScan.hasNextInt()) {
					if(Integer.parseInt(unparsedNumber)>0) {
						stepCount = Integer.parseInt(unparsedNumber);
					}
	
					else {
						errorProcessing.setText("Invalid Input");
					}
				}
				
				for(int i = 0; i<stepCount; i++) {
					Critter.worldTimeStep();
//					if(sliderValue.intValue() <= stepCount && sliderValue.intValue()!=0) {
//						if(i%sliderValue.intValue() == 0) {
//							Critter.displayWorld();
//						}
//					}
				}
				List<Critter> newCritterList = new ArrayList<Critter>();
				Critter.displayWorld();
				if(globalStatsText.getText() != null && !globalStatsText.getText().isEmpty()) {
					String statsclass = globalStatsText.getText();
					try {
						newCritterList = Critter.getInstances(statsclass);
						
						//Critter.runStats(critterList);
					} catch(InvalidCritterException r) {
						String returnString = r.toString();
						outputResult.setText(returnString);
					}
				}
				else {
					newCritterList = Critter.runPopulation();
					Critter.runStats(newCritterList);
				}
			}
		});
	}
	
	private static void initBottom(){
		Button showButton = new Button();
		showButton.setPadding(new Insets(10, 10, 10, 10));
		showButton.setAlignment(Pos.CENTER);
		//bottomPane.setPadding(new Insets(20, 0, 0, 20));
		bottomPane.setTop(showButton);
		BorderPane.setAlignment(showButton, Pos.CENTER);
		
		
		
		Slider stepSlide = new Slider();
		bottomPane.setBottom(stepSlide);
		BorderPane.setAlignment(stepSlide, Pos.CENTER);
		
		stepSlide.setMin(0);
		stepSlide.setMax(100);
		stepSlide.setMajorTickUnit(20);
		stepSlide.setMinorTickCount(9);
		stepSlide.setBlockIncrement(2);
		stepSlide.setSnapToTicks(true);
		stepSlide.setShowTickLabels(true);
		stepSlide.setShowTickMarks(true);
		stepSlide.getValue();
		stepSlide.scaleShapeProperty();
		stepSlide.setMaxWidth(500);
		stepSlide.setPadding(new Insets(10, 20, 10, 20));
		Label sliderLabel = new Label("0");
		
		bottomPane.setRight(sliderLabel);
		
		stepSlide.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				sliderLabel.setText(new_val.toString());
				sliderValue = new_val;
			}
		});
		
		//bottomPane.setCenter(stepSlide);
		
		
	
	
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
		globalStatsText = statsTextField;
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
						
						//Critter.runStats(critterList);
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
		
		Button quit = new Button();
		quit.setText("Quit");
		
		quit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});
		
		rGrid.add(subGrid, 0, 0);
		rGrid.add(statsBt, 0, 1);
		rGrid.add(scroll, 0, 2);
		rGrid.add(quit, 0, 3); 
		
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("CRITTER COMMANDS");
		root.setPadding(new Insets(20, 0, 0, 20));
		BorderPane.setMargin(bottomPane, new Insets(20));
		BorderPane.setMargin(rGrid, new Insets(0, 20, 20, 20));
		root.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
		//root.setStyle("-fx-background-image: url("Space.jpg");-fx-background-size : " + root.getScaleX() + ", " + root.getScaleY() + ";-fx-background-repeat: no-repeat;");
		
		initCenter();
		root.setCenter(grid);
		
		//ObjectOutputStream oos = new ObjectOutputStream(outputResult);
		
		//System.setOut(outputResult);
		
		initBottom();
		root.setBottom(bottomPane);
		
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
