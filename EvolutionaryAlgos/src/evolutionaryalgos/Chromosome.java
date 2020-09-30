package evolutionaryalgos;

import java.util.*;


public class Chromosome{
	private static int evaluations;
	private ArrayList<Integer> solution;
	private double fitness;
	private boolean fitnessChecked = false;
	private Random rand = new Random();
	
	public Chromosome() {
		
	}
	
	public Chromosome(int queens){
		
		this.solution = new ArrayList<Integer>(queens);
		
		for(int i = 0; i < queens; i++) {
			solution.add(i);
		}
		Collections.shuffle(solution, rand);
		this.evaluateFitness();
	}
	public Chromosome(ArrayList<Integer> solution) {
		this.solution = solution;
		this.evaluateFitness();
	}
	
	
	public double getFitness() {
		if(fitnessChecked) {
			return this.fitness;
		}
		else {
			this.evaluateFitness();
			return this.fitness;
		}
	}
	
	public void printSolution() {
		for(int i = this.solution.size()-1; i >= 0; i--) {
			int print = solution.indexOf(i);
			for(int j = 0; j < this.solution.size(); j++) {
				if(j == print) {
					System.out.print(" Q ");
				}
				else {
					System.out.print(" . ");
				}
			}
			System.out.print("\n");
		}
		for(int i = 0; i < this.solution.size(); i++) {
			System.out.print(" " + solution.get(i) + " ");
		} 
		System.out.print("\n\n");
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
		evaluations++;
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
	
	public static int getEvaluations() {
		return evaluations;
	}
	
	private void mutate() {
		int mutateCheck = rand.nextInt(solution.size());
		if(mutateCheck >= solution.size()*.5) {
			Collections.swap(this.solution, rand.nextInt(this.solution.size()), rand.nextInt(this.solution.size()));
		}
	}
	
	public static ArrayList<Chromosome> partiallyMappedCrossover(Chromosome p1, Chromosome p2) {
		Random rand = new Random();
		ArrayList<Integer> parent1 = p1.solution;
		ArrayList<Integer> parent2 = p2.solution;
		//Integer[] parent1 = p1.solution.toArray(new Integer[p1.solution.size()]);
		//Integer[] parent2 = p2.solution.toArray(new Integer[p2.solution.size()]);
		ArrayList<Integer> child1 = new ArrayList<Integer>(p1.solution.size());
		ArrayList<Integer> child2 = new ArrayList<Integer>(p1.solution.size());
		ArrayList<Chromosome> children = new ArrayList<Chromosome>(2);
		int start = rand.nextInt(p1.solution.size());
		int end = start + rand.nextInt(p1.solution.size()-start);
		for(int i = 0; i < p1.solution.size(); i++) {
			child1.add(-1);
			child2.add(-1);
		}
		/*int val1 = 0;
		int val2 = 0;
		int swapIndex1 = 0;
		int swapIndex2 = 0;
		for(int i = 0; i < p1.solution.size(); i++) {
			child1.add(-1);
			child2.add(-1);
		}
		int start = rand.nextInt(p1.solution.size());
		int end = start + rand.nextInt(p1.solution.size()-start);
		for(int i = 0; i < p1.solution.size(); i++) {
			child1.set(i, parent1.get(i));
			child2.set(i, parent2.get(i));
		}
		
		for(int i = start; i <= end; i++) {
			val1 = parent1.get(i);
			val2 = parent2.get(i);
			
			for(int j = 0; j < p1.solution.size(); j++) {
				if(child1.get(j) == val1) {
					swapIndex1 = j;
				}
				else if(child1.get(j) == val2) {
					swapIndex2 = j;
				}
			}
			
			if(val1 != val2) {
				child1.set(swapIndex1, val2);
				child1.set(swapIndex2, val1);
			}
			
			for(int k = 0; k < p1.solution.size(); k++) {
				if(child2.get(k) == val2) {
					swapIndex1 = k;
				}
				else if(child2.get(k) == val1) {
					swapIndex2 = k;
				}
			}
			if(val1 != val2) {
				child2.set(swapIndex1, val1);
				child2.set(swapIndex2, val1);
			}
		}
		
		children.add(new Chromosome(child1));
		children.add(new Chromosome(child2));
		children.get(0).mutate();
		children.get(1).mutate();*/
		
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

	/*private Chromosome rouletteSelect(ArrayList<Chromosome> population) {
		
	}*/
	
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
	/*@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}*/
	
	/*public int compareTo(Chromosome chrome) {
		return (int)((this.getEvaluations()*10000) - (chrome.getFitness()*10000));
	}*/
	
	public static Comparator<Chromosome> fitnessCompare = new Comparator<Chromosome>() {
		public int compare(Chromosome c1, Chromosome c2) {
			return (int)((c2.getFitness()*10000)-(c1.getFitness()*10000));
		}
	};
}

	
