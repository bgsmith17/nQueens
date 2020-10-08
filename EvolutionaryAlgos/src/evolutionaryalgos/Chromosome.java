package evolutionaryalgos;

import java.util.*;

/*each chromosome contains a candidate solution to the nqueens problem
 To limit how long the algorithm may execute, evaluations has been made static.
 * 
 */
public class Chromosome{
	//how many chromosomes have been evaluated
	private static int evaluations;
	//container for a candidate solutions
	//each index is an X value and contains a Y value
	private ArrayList<Integer> solution;
	//the quality of the candidate solution
	private double fitness;
	
	//has it been checked
	private boolean fitnessChecked = false;
	private Random rand = new Random();
	private double selectionAdvantage;
	private boolean valid;
	
	//are there any empty indices in the solution?
	public boolean containsNull() {
		return this.solution.contains(-1);
	}
	
	//default constructor
	public Chromosome() {
		
	}
	
	/*single parameter constructor
	 * @param int queens
	 * @returns a new Chromosome object
	 */
	public Chromosome(int queens){
		
		this.solution = new ArrayList<Integer>(queens);
		//fill the list with the desired number of queens
		for(int i = 0; i < queens; i++) {
			solution.add(i);
		}
		//shuffle the list
		Collections.shuffle(solution, rand);
		//evalute the quality of the resulting solution.
		this.evaluateFitness();
	}
	//single parameter constructor, used only for offspring generation.
	public Chromosome(ArrayList<Integer> solution) {
		this.solution = solution;
		this.evaluateFitness();
	}
	
	//returns the fitness of an individual
	public double getFitness() {
		if(fitnessChecked) {
			return this.fitness;
		}
		else {
			this.evaluateFitness();
			return this.fitness;
		}
	}
	
	//prints the solution
	public void printSolution() {
		for(int i = this.solution.size()-1; i >= 0; i--) {
			int print = solution.indexOf(i);
			for(int j = 0; j < this.solution.size(); j++) {
				if(j == print) {
					System.out.print("Q");
				}
				else {
					System.out.print("  .  ");
				}
			}
			System.out.print("\n");
		}
		for(int i = 0; i < this.solution.size(); i++) {
			System.out.print(" " + solution.get(i));
		} 
		System.out.print("\n\n");
	}
	

	
	//evaluates the fitness by searching for diagonal conflicts
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
		evaluations++;
	}
	
	//checkX methods are all helper methods for evaluateFitness
	//checkDiagonalUp looks for conflicts upwards
	private boolean checkDiagonalUp(int index){
		//start checking immediately to the right of specified column 
		for(int i = index + 1; i < solution.size(); i++) {
			if((solution.get(index) - index)==(solution.get(i) - i)){
				return true;
			}
		}
	
		return false;
	}//checkdiagup
	//checkDiagonalDown looks for conflicts downwards
	private boolean checkDiagonalDown(int index){
		for(int i = index+1; i < solution.size(); i++) {
			if((solution.get(i) + i) == (solution.get(index)+index)){
				return true;
			}//if
		}//for
	
		return false;
	}
	
	public static int getEvaluations() {
		return evaluations;
	}
	
	//mutates an individual using swap mutation
	private void mutate() {
		//generates a random number
		int mutateCheck = rand.nextInt(solution.size());
		//50% chance of mutation
		if(mutateCheck <= solution.size()*.5) {
			Collections.swap(this.solution, rand.nextInt(this.solution.size()), rand.nextInt(this.solution.size()));
		}
	}
	//crossover operator
	public static ArrayList<Chromosome> partiallyMappedCrossover(Chromosome p1, Chromosome p2) {
		Random rand = new Random();
		ArrayList<Integer> parent1 = p1.solution;
		ArrayList<Integer> parent2 = p2.solution;
		ArrayList<Integer> child1 = new ArrayList<Integer>(p1.solution.size());
		ArrayList<Integer> child2 = new ArrayList<Integer>(p1.solution.size());
		ArrayList<Chromosome> children = new ArrayList<Chromosome>(2);
		int start = rand.nextInt(p1.solution.size());
		int end = start + rand.nextInt(p1.solution.size()-start);
		for(int i = 0; i < p1.solution.size(); i++) {
			child1.add(-1);
			child2.add(-1);
		}
		
		//copy a region from parent2 to child 1 and parent1 to child 2
		for(int i = start; i < end; i++) {
			if(!child1.contains(parent1.get(i))){
				child1.set(i, parent2.get(i));
			}
			if(!child2.contains(parent2.get(i))) { 
				child2.set(i, parent1.get(i));
			}
			
			else {
				if(!child1.contains(parent1.get(i))) {
					child1.set(child1.indexOf(-1), parent1.get(i));
				}
				if(!child2.contains(parent2.get(i))) {
					child2.set(child2.indexOf(-1), parent2.get(i));
				}
			}
		}
		//fills child 1 and child 2 with values from parent 1 and parent2 respectively,
		//checking to see if there are any unfilled spots and no duplicates as it goes.
		if(child1.contains(-1)) {
			for(int i = 0; i < p1.solution.size(); i++) {
				if(!child1.contains(parent1.get(i))) {
					child1.set(child1.indexOf(-1), parent1.get(i));
					if(!child1.contains(-1)) {
						break;
					}
				}
			}
		}
		if(child2.contains(-1)) {
			for(int i = 0; i < p1.solution.size(); i++) {
				if(!child2.contains(parent2.get(i))) {
					child2.set(child2.indexOf(-1), parent2.get(i));
					if(!child2.contains(-1)) {
						break;
					}
				}
			}
		}
		//adds new children to the progeny list, and gives them a chance to mutate
		children.add(new Chromosome(child1));
		children.add(new Chromosome(child2));
		children.get(0).mutate();
		children.get(1).mutate();
		

		
		return children;
	}
	
	private void setSolution(ArrayList<Integer> solution) {
		this.solution = solution;
	}
	
	public boolean equalTo(Chromosome chrome) {
		for(int i = 0; i < chrome.solution.size(); i++) {
			if(this.solution.get(i) != chrome.solution.get(i)) {
				return false;
			}
		}
		return true;
	}


	public boolean containedBy(ArrayList<Chromosome> population) {
		for(int i = 0; i < population.size(); i++) {
			if(this.equalTo(population.get(i))) {
				return true;
			}
		}
		return false;
	}


	//selection for parents to go into mating pool
	public static ArrayList<Chromosome> tournamentSelect(ArrayList<Chromosome> population){
		Random rand = new Random();
		ArrayList<Chromosome> matingPool = new ArrayList<Chromosome>(population.size()/2);
		ArrayList<Chromosome> children = new ArrayList<Chromosome>(population.size()/2);
		for(int i = 0; i < population.size()/2; i++) {
			boolean chosen = false;
			while(!chosen) {
				int indexA = rand.nextInt(population.size());
				int indexB = rand.nextInt(population.size());
				if(!matingPool.contains(population.get(indexA)) && !matingPool.contains(population.get(indexA))) {
					if(population.get(indexA).getFitness() > population.get(indexB).getFitness()) {
					matingPool.add(population.get(indexA));
					chosen = true;
					}
					else {
						matingPool.add(population.get(indexB));
						chosen = true;
					}
				}
			}
		}
		while(children.size() < population.size()/2) {
			children.addAll(partiallyMappedCrossover(matingPool.get(rand.nextInt(matingPool.size())), matingPool.get(rand.nextInt(matingPool.size()))));
		}
		
		return children;
	}
	
	public static void evaluatePopulation(ArrayList<Chromosome> population) {
		for(Chromosome c: population) {
			c.evaluateFitness();
		}
	}
	private Chromosome selectParent(Chromosome p1, Chromosome p2) {
		if(p1.getFitness() > p2.getFitness()) {
			return p1;
		}
		else if(p2.getFitness() > p1.getFitness()) {
			return p2;
		}
		else {
			if(rand.nextInt(2) == 1) {
				return p1;
			}
			else {
				return p2;
			}
		}
	}

	//utility to compare chromosomes, used for sorting.
	public static Comparator<Chromosome> fitnessCompare = new Comparator<Chromosome>() {
		public int compare(Chromosome c1, Chromosome c2) {
			return (int)((c2.getFitness()*10000)-(c1.getFitness()*10000));
		}
	};
}

	
