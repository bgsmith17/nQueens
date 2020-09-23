package evolutionary_algorithms;

import java.util.Random;

public class Chromosome {
	private int[] solution;
	private double fitness;
	private Random rand = new Random();
	
	public Chromosome(int queens){
		
		this.solution = new int[queens];
		
		for(int i = 0; i < queens; i++) {
			this.solution[i] = rand.nextInt(queens);
		}
	}
	
	public double evaluateFitness() {
		double conflicts = 0;
		for(int i = 0; i < this.solution.length; i++) {
			if(checkRight(i)) {
				conflicts++;
			}
			if(checkDiagonalUp(i)) {
				conflicts++;
			}
			if(checkDiagonalDown(i)) {
				conflicts++;
			}
		}
		return (1/(1+conflicts));
	}
	
	private boolean checkRight(int index){
		for(int i = index+1; i < solution.length; i++) {
			if(solution[i]==solution[index]) {
				return true;
			}//if
		}//for
		return false;
	}
	
	private boolean checkDiagonalUp(int index){
		for(int i = index+1; i < solution.length; i++) {
			
		}
		return false;
	}
	private boolean checkDiagonalDown(int index){
		return false;
	}
	
}

