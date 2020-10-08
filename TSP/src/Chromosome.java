

import java.util.*;


/*the chromosome class contains a representation of a candidate solution to a specified problem
 * It can track the total number of fitness evaluations (to be used as a halting parameter) 
 * and contains methods to mutate, crossover, and generate chromosome attributes.
 */
public class Chromosome{
	private static int evaluations;
	private ArrayList<Node> solution;
	private double fitness;
	private boolean fitnessChecked = false;
	private Random rand = new Random();
	
	public Chromosome() {
		
	}
	
	public Chromosome(ArrayList<Node> solution) {
		this.solution = solution; 
		Collections.shuffle(this.solution, rand);
		//this.evaluateFitness();
	}
	
	public ArrayList<Node> getSolution(){
		return this.solution;
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
		for(Node n: this.solution) {
			n.printNode();
		}
		System.out.print("\n\n");
	}
		
	public void evaluateFitness() {
		double tourLength = 0;
		//this.printSolution();
		Node firstCity = this.solution.get(0);
		Node lastCity = this.solution.get(solution.size()-1);
		for(int i = 0; i < solution.size()-2; i++) {
			tourLength += solution.get(i).computeDistance(solution.get(i+1));
		}
		tourLength += lastCity.computeDistance(firstCity);
		this.fitness = tourLength;
		evaluations++;
		fitnessChecked = true;
	}
	
	public static int getEvaluations() {
		return evaluations;
	}
	
	/*public void mutate() {
		int mutateCheck = rand.nextInt(5);
		int start = rand.nextInt(solution.size()/2);
		int end = start + rand.nextInt(solution.size()-start);
		switch(mutateCheck) {
		case 0:
		case 3:	
			
			Collections.shuffle(this.solution.subList(start, end));
		break;
		case 1:
		case 2:	
		case 4:
			ArrayList<Node>reverseMe = new ArrayList<Node>(end - start);
			for(int i = start; i <= end; i ++) {
				reverseMe.add(this.solution.get(i));
			}
			Collections.reverse(reverseMe);
			for(int i = 0; i < reverseMe.size(); i++) {
				this.solution.set((i+start), reverseMe.get(i));
			}
		break;
		}
		this.evaluateFitness();
		
	}*/
	
	/*public static ArrayList<Chromosome> partiallyMappedCrossover(Chromosome p1, Chromosome p2) {
		//System.out.println("In Crossover");
		Random rand = new Random();
		ArrayList<Node> parent1 = p1.solution;
		ArrayList<Node> parent2 = p2.solution;
		ArrayList<Node> child1 = new ArrayList<Node>(p1.solution.size());
		ArrayList<Node> child2 = new ArrayList<Node>(p1.solution.size());
		ArrayList<Chromosome> children = new ArrayList<Chromosome>(2);
		int start = rand.nextInt(p1.solution.size());
		int end = start + rand.nextInt(p1.solution.size()-start);
		if((end - start) < 10) {
			while((end - start) < 10) {
			start = rand.nextInt(p1.solution.size());
			end = start + rand.nextInt(p1.solution.size()-start);
			}
		}
		for(int i = 0; i < p1.solution.size(); i++) {
			child1.add(null);
			child2.add(null);
		}

		
		for(int i = start; i < end; i++) {
				child1.set(i, parent2.get(i));
				child2.set(i, parent1.get(i));
			
			
		
		}
		while(child1.contains(null)) {
			for(int i = 0; i < p1.solution.size(); i++) {
				if(!child1.contains(parent1.get(i))) {
					child1.set(child1.indexOf(null), parent1.get(i));
				}
			}
		}
		while(child2.contains(null)) {
			for(int i = 0; i < p1.solution.size(); i++) {
				if(!child2.contains(parent2.get(i))) {
					child2.set(child2.indexOf(null), parent2.get(i));
				}
			}
		}

		children.add(new Chromosome(child1));
		children.add(new Chromosome(child2));
		if(rand.nextInt(10) > 2) {
			children.get(0).mutate();
			children.get(1).mutate();
		}
		

		
		return children;
	}*/
	
	void setSolution(ArrayList<Node> solution) {
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

	
	/*public static ArrayList<Chromosome> tournamentSelect(ArrayList<Chromosome> population){
		//System.out.println("In Selection");
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
	}*/
	
	public static void evaluatePopulation(ArrayList<Chromosome> population) {
		for(Chromosome c: population) {
			c.evaluateFitness();
		}
	}
	/*private Chromosome selectParent(Chromosome p1, Chromosome p2) {
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
	}*/

	
	public static Comparator<Chromosome> fitnessCompare = new Comparator<Chromosome>() {
		public int compare(Chromosome c1, Chromosome c2) {
			return (int)((c1.getFitness()*10000)-(c2.getFitness()*10000));
		}
	};
}



	
