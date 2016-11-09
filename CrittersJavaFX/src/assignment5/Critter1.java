package assignment5;

import javafx.scene.paint.Color;

/* CRITTERS <Critter1.java>
 * EE422C Project 4 submission by
 * Nicholas White
 * NWW295
 * 16545
 * Javier Cortes
 * jc74593
 * 16445
 * Slip days used: <0>
 * Fall 2016
 */

public class Critter1 extends Critter {
	
	
	@Override 
	public String toString() {
		return "1";
	}
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	private boolean encounter;
	
	
	public Critter1() {
		for (int k = 0; k < 8; k++) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
		
	}
	
	public boolean fight(String opponent) {
		
		String look = this.look(dir, true);
		
		if(getEnergy()>100) {
			encounter = true;
			return true;
		}
		
		if(opponent.equals("@")){
			return true;
		}
		if(look.equals(this.toString())){
			return true;
		}
		dir = Critter.getRandomInt(8);
		encounter = true;
		run(dir);
		return false;
		
	}
	
	@Override 
	public void doTimeStep() {
		run(dir);
		run(dir);
		
		if (getEnergy() > (Params.start_energy - 20)) {
			Critter1 child = new Critter1();
			for (int k = 0; k < 8; k++) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g]--;
			g = Critter.getRandomInt(8);
			child.genes[g]++;
			reproduce(child, Critter.getRandomInt(8));
			reproduce(child, Critter.getRandomInt(8));
		}
		
		/* new direction based on our genes */
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
		
	}
	
	public static void runStats(java.util.List<Critter> craigs) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : craigs) {
			Critter1 c = (Critter1) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
			//System.out.println("" + c.getEnergy() + "" + c.encounter);
		}
		System.out.print("" + craigs.size() + " total Horses    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * craigs.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * craigs.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * craigs.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * craigs.size()) + "% left   ");
		System.out.println();
	}

	@Override
	public CritterShape viewShape() {
		return Critter.CritterShape.DIAMOND;
	}
	
	@Override
	public javafx.scene.paint.Color viewFillColor(){
		return Color.GOLD;
	}
}

	
	

