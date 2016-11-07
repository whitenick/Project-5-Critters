package assignment5;

import java.util.List;
import java.util.*;

import assignment5.Params;
import javafx.stage.Stage;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	
	private static List<Critter> population = new ArrayList<Critter>();
	private static List<Critter> babies = new ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private int[] lookMov(int direction, int tempX, int tempY) {
		int[] coor = new int[2];
		switch(direction){
		    case 0: tempX++;
		        tempX = tempX%Params.world_width;
		        break;
		    case 1: tempX++;
		        tempX = tempX%Params.world_width;
		        tempY--;
		        tempY = tempY%Params.world_height;
		        break;
		    case 2: tempY--;
		     	tempY = tempY%Params.world_height;
		        break;
		    case 3: tempY--;
		        tempY = tempY%Params.world_height;
		        tempX--;
		        tempX = tempX%Params.world_width;
		        break;
		    case 4: tempX--;
		     	tempX = tempX%Params.world_width;
		        break;
		    case 5: tempY++;
		     	tempY = tempY%Params.world_height;
		        tempX--;
		        tempX = tempX%Params.world_width;
		        break;
		    case 6: tempY++;
		     	tempY = tempY%Params.world_height;
		        break;
		    case 7: tempX++;
		     	tempX = tempX%Params.world_width;
		     	tempY++;
		     	tempY = tempY%Params.world_height;
		        break;
		 }
		if(tempX < 0){
			tempX += Params.world_width;
		}
		if(tempY < 0){
			tempY += Params.world_height;
		}
		tempX %= Params.world_width;
		tempY %= Params.world_height;
		
		coor[0] = tempX;
		coor[1] = tempY;
		return coor;
	}
	
	protected String look(int direction, boolean steps) {
		this.energy -= Params.look_energy_cost;
		String returnString = null;
		int copyX = this.oldX;
		int copyY = this.oldY;
		int [] coor = null;
		if(steps){
			coor = lookMov(direction, copyX, copyY);
			coor = lookMov(direction, copyX, copyY);
		}else coor = lookMov(direction, copyX, copyY);
		if(coor != null){
			copyX = coor[0];
			copyY = coor[1];
			
			for(int i = 0; i<population.size(); i++) {
				if((population.get(i).energy > 0) && (copyX == population.get(i).oldX) 
						&& (copyY == population.get(i).oldY)) {
					returnString = population.get(i).toString();
					//returnString is the .toString of the last critter in the space looked at
				}
			}
		}
		
		
		return returnString;
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	public int x_coord;
	public int y_coord;
	
	private int oldX;
	private int oldY;
	private int numWalk;
	private int numRun;
	
	protected final void walk(int direction) {
		if((numWalk < 1) && (numRun < 1)){
			makeMov(direction);
		}
		numWalk++;
		energy -= Params.walk_energy_cost;
	}
	
	protected final void run(int direction) {
		if((numWalk < 1) && (numRun < 1)){
			makeMov(direction);
			makeMov(direction);
		}
		numRun++;
		energy -= Params.run_energy_cost;
	}
	
	protected final void makeMov(int direction) {
        switch (direction) {
            case 0: x_coord++;
                x_coord = x_coord%Params.world_width;
                break;
            case 1: x_coord++;
                x_coord = x_coord%Params.world_width;
                y_coord--;
                y_coord = y_coord%Params.world_height;
                break;
            case 2: y_coord--;
                y_coord = y_coord%Params.world_height;
                break;
            case 3: y_coord--;
                y_coord = y_coord%Params.world_height;
                x_coord--;
                x_coord = x_coord%Params.world_width;
                break;
            case 4: x_coord--;
                x_coord = x_coord%Params.world_width;
                break;
            case 5: y_coord++;
                y_coord = y_coord%Params.world_height;
                x_coord--;
                x_coord = x_coord%Params.world_width;
                break;
            case 6: y_coord++;
                y_coord = y_coord%Params.world_height;
                break;
            case 7: x_coord++;
                x_coord = x_coord%Params.world_width;
                y_coord++;
                y_coord = y_coord%Params.world_height;
                break;
        }
		if(x_coord < 0){
			x_coord += Params.world_width;
		}
		if(y_coord < 0){
			y_coord += Params.world_height;
		}
		x_coord %= Params.world_width;
		y_coord %= Params.world_height;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy > Params.min_reproduce_energy) {
			offspring.energy = this.energy/2;
			this.energy/=2;
			offspring.makeMov(direction);
			babies.add(offspring);
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	public static void worldTimeStep() {
		for(int i = 0; i < population.size(); i++){
			Critter crit = population.get(i);
			
			crit.energy -= Params.rest_energy_cost;
			crit.oldX = crit.x_coord;
			crit.oldY = crit.y_coord;
			crit.doTimeStep();
			
			if(crit.energy <= 0){
				population.remove(i);
			}
		}
		
		for(int i = 0; i<population.size(); i++){
			Critter aCritter = population.get(i);
			for(int j = 0; j<population.size(); j++){
				if(i != j){
					Critter bCritter = population.get(j);
					if((aCritter.x_coord == bCritter.x_coord)
					&& (aCritter.y_coord == bCritter.y_coord)
					&& (aCritter.energy > 0) && (bCritter.energy > 0)){
						handleFight(aCritter, bCritter, i, j);
					}
				}
			}
		}
		
		for(int i = 0; i<population.size(); i++){
			Critter thisCritter = population.get(i);
			thisCritter.numWalk = 0;
			thisCritter.numRun = 0;
			if(thisCritter.energy <= 0){
				population.remove(i);
			}
		}
		
		population.addAll(babies);
		babies.clear();
		
		for(int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				makeCritter("Algae");
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void displayWorld() {
		//TODO:Create once, refresh in subsequent calls
		CritterWorldGUI critterWorld = new CritterWorldGUI();
		Stage secondaryStage = new Stage();
		critterWorld.start(secondaryStage);
	}
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			String critterClass = myPackage + "." + critter_class_name;
			Critter obj = (Critter) Class.forName(critterClass).newInstance();
			obj.x_coord = getRandomInt(Params.world_width);
			obj.y_coord = getRandomInt(Params.world_height);
			obj.oldX = obj.x_coord;
			obj.oldY = obj.y_coord;
			obj.energy = Params.start_energy;
			
			population.add(obj);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> critInstances = new ArrayList<Critter>();
		try{
			String critterClass = myPackage + "." + critter_class_name;
			Critter obj = (Critter) Class.forName(critterClass).newInstance();
			for(Critter crit : population){
				if(crit.getClass().isInstance(obj)){
					critInstances.add(crit);
				}
			}
		} catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
			throw new InvalidCritterException(critter_class_name);
		}
		
		return critInstances;
	}
	
	public static void runStats(List<Critter> critters) {
		
		String outString = ("" + critters.size() + " critters as follows -- ");
		application.Main.outputResult.setText(application.Main.outputResult.getText()+outString+"\n");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			outString = (prefix + s + ":" + critter_count.get(s));
			application.Main.outputResult.setText(application.Main.outputResult.getText()+outString+"\n");
			prefix = ", ";
		}
		application.Main.outputResult.setText(application.Main.outputResult.getText()+"\n");
		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
	}
	
	public static List<Critter> runPopulation() {
		return population;
	}
	
	private static void handleFight(Critter a, Critter b, int aIndex, int bIndex){
		boolean aFight = a.fight(b.toString());
		boolean bFight = b.fight(a.toString());
		
		if(!aFight){
			if((a.x_coord != a.oldX) || (a.y_coord != a.oldY)){
				for(int i = 0; i < population.size(); i++){
					if((aIndex != i) && (population.get(i).energy > 0)){
						if((population.get(i).x_coord == a.x_coord)
							&&(population.get(i).y_coord == a.y_coord)){
							a.x_coord = a.oldX;
							a.y_coord = a.oldY;
							i = population.size();
						}
					}
				}
			}
		}
		
		if(!bFight){
			if((b.x_coord != b.oldX) && (b.y_coord != b.oldY)){
				for(int i = 0; i < population.size(); i++){
					if((bIndex != i) && (population.get(i).energy > 0)){
						if((population.get(i).x_coord == b.x_coord)
							&&(population.get(i).y_coord == b.y_coord)){
							b.x_coord = b.oldX;
							b.y_coord = b.oldY;
							i = population.size();
						}
					}
				}
			}
		}
		
		if((a.x_coord == b.x_coord) && (a.y_coord == b.y_coord) && (aFight || bFight)
				&&(a.energy > 0) && (b.energy > 0)){
			int aRoll = 0;
			int bRoll = 0;
			
			if(aFight){
				aRoll = getRandomInt(a.energy);
			}else
				aRoll = 0;
			
			if(bFight){
				bRoll = getRandomInt(b.energy);
			}else
				bRoll = 0;
			
			if(aRoll > bRoll){
				a.energy += b.energy/2;
				b.energy = 0;
			}
			else if(bRoll > aRoll){
				b.energy += a.energy/2;
				a.energy = 0;
			}
			else if(aRoll == bRoll){
				int ran = getRandomInt(2);
				if(ran == 0){
					a.energy += b.energy/2;
					b.energy = 0;
				}else{
					b.energy += a.energy/2;
					a.energy = 0;
				}
			}
		}
	}
}
