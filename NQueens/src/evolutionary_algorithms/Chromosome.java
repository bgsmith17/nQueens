package evolutionary_algorithms;

import java.util.Random;

public class Chromosome {
	private int[] solution;
	private double fitness;
	private boolean fitnessChecked = false;
	private Random rand = new Random();
	
	public Chromosome(int queens){
		
		this.solution = new int[queens];
		
		for(int i = 0; i < queens; i++) {
			this.solution[i] = rand.nextInt(queens);
		}
	}
	
	public void getFitness() {
		if(fitnessChecked) {
			System.out.println(this.fitness + "\n");
		}
		else {
			this.evaluateFitness();
			System.out.println(this.fitness + "\n");
		}
	}
	
	public void printSolution() {
		for(int i = 0; i < this.solution.length; i++) {
			System.out.println(solution[i] + " ");
		}
	}
	
	public void evaluateFitness() {
		double conflicts = 0;
		for(int i = 0; i < this.solution.length-1; i++) {
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
		this.fitnessChecked = true;
		this.fitness =  (1/(1+conflicts));
	}
	
	//checkX methods are all helper methods for evaluateFitness
	private boolean checkRight(int index){ 
		for(int i = index+1; i < solution.length; i++) {
			if(solution[i]==solution[index]) {
				return true;
			}//if
		}//for
		return false;
	}
	private boolean checkDiagonalUp(int index){
		
		/*for(int i = index+1; i < solution.length; i++) {
			if((solution[i] + variance < solution.length) && (solution[index] == (solution[i]+variance))){
				return true;
			}//if
			variance++;
		}//for*/
		//start checking immediately to the right of specified column 
		for(int i = index + 1; i < solution.length; i++) {
			if((solution[index] - index)==(solution[i] - i)){
				return true;
			}
		}
	
		return false;
	}//checkdiagup
	private boolean checkDiagonalDown(int index){
		
		for(int i = index+1; i < solution.length; i++) {
			if((solution[i] + i) == (solution[index]+index)){
				return true;
			}//if
		}//for
	
		return false;
	}
	
	
}

