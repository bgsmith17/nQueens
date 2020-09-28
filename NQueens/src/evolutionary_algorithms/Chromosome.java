package evolutionary_algorithms;

import java.util.*;

public class Chromosome {
	private ArrayList<Integer> solution;
	private double fitness;
	private boolean fitnessChecked = false;
	private Random rand = new Random();
	
	public Chromosome(int queens){
		
		this.solution = new ArrayList<Integer>(queens);
		
		for(int i = 0; i < queens; i++) {
			solution.add(i);
		}
		Collections.shuffle(solution, rand);
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
		for(int i = 0; i < this.solution.size(); i++) {
			System.out.println(solution.get(i) + " ");
		}
	}
	
	public void evaluateFitness() {
		double conflicts = 0;
		for(int i = 0; i < this.solution.size()-1; i++) {
			/*if(checkRight(i)) {
				conflicts++;
			}*/
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
	/*private boolean checkRight(int index){ 
		for(int i = index+1; i < solution.size(); i++) {
			if(solution.get(i)==solution.get(index)) {
				return true;
			}//if
		}//for
		return false;
	}*/
	private boolean checkDiagonalUp(int index){
		
		/*for(int i = index+1; i < solution.length; i++) {
			if((solution[i] + variance < solution.length) && (solution[index] == (solution[i]+variance))){
				return true;
			}//if
			variance++;
		}//for*/
		//start checking immediately to the right of specified column 
		for(int i = index + 1; i < solution.size(); i++) {
			if((solution.get(index) - index)==(solution.get(i) - i)){
				return true;
			}
		}
	
		return false;
	}//checkdiagup
	private boolean checkDiagonalDown(int index){
		
		for(int i = index+1; i < solution.size(); i++) {
			if((solution.get(i) + i) == (solution.get(index)+index)){
				return true;
			}//if
		}//for
	
		return false;
	}
	
	private Chromosome[] partiallyMappedCrossover(Chromosome p1, Chromosome p2, Random rand, int queens) {
		Chromosome[] children = new Chromosome[2];
		Integer[] parent1 = p1.solution.toArray(new Integer[p1.solution.size()]);
		Integer[] parent2 = p2.solution.toArray(new Integer[p2.solution.size()]);
		int[] child1 = new int[p1.solution.size()];
		int[] child2 = new int[p1.solution.size()];
		int start = rand.nextInt(queens);
		int end = rand.nextInt(queens);
		
		if(start > end) {
			for(int i = start; i < end; i++) {
				child1[i] = parent1[i];
				child2[i] = parent2[i];
			}
		}
		else if(start == end) {
			child1[start] = parent1[start];
			child2[start] = parent2[start];
		}
		else {
			for(int i = end; i < start; i++) {
				child1[i] = parent1[i];
				child2[i] = parent2[i];
			}
		}
		return children;
	}
	
	public boolean equalTo(Chromosome chrome) {
		for(int i = 0; i < chrome.solution.size(); i++) {
			if(this.solution.get(i) != chrome.solution.get(i)) {
				return false;
			}
		}
		return true;
	}
}

