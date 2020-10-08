import java.util.*;

public class GeneticAlgorithm {
	static double mutationRate = .01;
	static int tournamentSize = 10;
	static Random rand = new Random();
	//wrapper method to execute crossover and mutation.  will never change fittest individual (elitism).
	public static ArrayList<Chromosome> nextGeneration(ArrayList<Chromosome> population){
		ArrayList<Chromosome> children = new ArrayList<Chromosome>(population.size()/2);
		
		
		for(int i = 1; i < population.size()/2; i++) {
			Chromosome p1 = tournamentSelect(population);
			Chromosome p2 = tournamentSelect(population);
			children.add(new Chromosome(crossOver(p1, p2)));
		}
		mutate(children);
		int index = population.size()-1;
		return children;
	}//nextGeneration
	
	//if a randomly generated number is below a threshold, mutate children
	public static void mutate(ArrayList<Chromosome> children) {
		if(Math.random() < mutationRate) {
			//two types of mutation possible.
			//if(rand.nextInt(10) > 2) {
				swapMutation(children.get(0));
				//swapMutation(children.get(1));
			/*}
			else {
				inversionMutation(children.get(0));
				inversionMutation(children.get(1));
				}*/
			}
		children.get(0).evaluateFitness();
		//children.get(1).evaluateFitness();
	}
	
	//swaps two elements in the tour
	private static void swapMutation(Chromosome child) {
		ArrayList<Node> mutateMe = child.getSolution();
		int index1 = rand.nextInt(child.getSolution().size());
		int index2 = rand.nextInt(child.getSolution().size());
		
		Collections.swap(mutateMe, index1, index2);
		child.setSolution(mutateMe);
	}

	private static void inversionMutation(Chromosome child) {
		ArrayList<Node> mutateMe = child.getSolution();
		int start = rand.nextInt(mutateMe.size()/2);
		int end = start + rand.nextInt(mutateMe.size()-start);
		ArrayList<Node>reverseMe = new ArrayList<Node>(end - start);
		for(int i = start; i <= end; i ++) {
			reverseMe.add(mutateMe.get(i));
		}
		Collections.reverse(reverseMe);
		for(int i = 0; i < reverseMe.size(); i++) {
			mutateMe.set((i+start), reverseMe.get(i));
		}
		child.setSolution(mutateMe);
	}
	
	public static Chromosome tournamentSelect(ArrayList<Chromosome> population){
		Random rand = new Random();
		ArrayList<Chromosome> tourney = new ArrayList<Chromosome>(tournamentSize);
		for(int i = 0; i < tournamentSize; i++) {
			tourney.add(population.get(rand.nextInt(population.size())));
		}
		Collections.sort(tourney, Chromosome.fitnessCompare);
		return tourney.get(0);

	}


	public static ArrayList<Node> crossOver(Chromosome p1, Chromosome p2) {
		//System.out.println("In Crossover");
		Random rand = new Random();
		ArrayList<Node> parent1 = p1.getSolution();
		ArrayList<Node> parent2 = p2.getSolution();
		ArrayList<Node> child1 = new ArrayList<Node>(parent1.size());
		//ArrayList<Node> child2 = new ArrayList<Node>(parent2.size());
		ArrayList<Chromosome> children = new ArrayList<Chromosome>(2);
		for(int i = 0; i < parent1.size(); i++) {
			child1.add(null);
			//child2.add(null);
		}
		
		int start = rand.nextInt(parent1.size());
		int end = start + rand.nextInt(parent1.size()-start);
		for(int i = 0; i < parent1.size(); i++) {
			if(i > start && i < end) {
				child1.set(i, parent2.get(i));
			}
		}
		for(int i = 0; i < parent2.size(); i++) {
			if(!child1.contains(parent1.get(i))) {
				for(int j = 0; j < parent2.size(); j++) {
					if(child1.get(j) == null) {
						child1.set(j, parent1.get(i));
						break;
					}
				}
			}
		}
		
		return child1;
	}//crossover
}//class