package evolutionaryalgos;
import java.util.*;


public class NQueens {
	
	public static void main(String[] args) {
		int queens;
		int popSize;
		ArrayList<Chromosome> population;
		ArrayList<Chromosome> matingPool = null; 
		Scanner kbd = new Scanner(System.in);
		Random rand = new Random();
		if(args.length == 1) {
			try {
				queens = Integer.parseInt(args[0]);
				popSize = Integer.parseInt(args[1]);
				population = initializePopulation(queens, popSize);
			}
			catch(Exception e) {
				System.out.println("Invalid Queens entry, please select an integer value");
				queens = kbd.nextInt();
				System.out.println("How big is the initial population?");
				popSize = kbd.nextInt();
				population = initializePopulation(queens, popSize);
			}
		}//if args passed
		else {
			System.out.println("For how many Queens should we find a solution?");
			queens = kbd.nextInt();
			System.out.println("How big is the initial population?");
			popSize = kbd.nextInt();
			population = initializePopulation(queens, popSize);
			
		}
		
		/*for(int i = 0; i < queens; i ++) {
			System.out.println("\nSolution");
			population[i].printSolution();
			System.out.println("\nFitness");
			population[i].getFitness();
		}*/
		Collections.sort(population, Chromosome.fitnessCompare);
		while(Chromosome.getEvaluations() < 1000000 && population.get(0).getFitness() < 1) {
			matingPool = Chromosome.tournamentSelect(population);
			int index = 0;
			for(int i = population.size()-1; i > population.size()/2; i--) {
				if(index > matingPool.size()) {
					break;
				}
				population.set(i, matingPool.get(index));
				index++;
			}
			Collections.sort(population, Chromosome.fitnessCompare);
			System.out.println(Chromosome.getEvaluations());
		
		}
		population.get(0).printSolution();
		System.out.println(population.get(0).getFitness());
		/*for(Chromosome c: population) {
			System.out.println(c.getFitness());
		}
		
		for(Chromosome c : matingPool) {
			c.printSolution();
			System.out.print(c.getFitness() + "\n\n");
			
		}
		*/
		kbd.close();
		return;
	}//main
	

	public static ArrayList<Chromosome> initializePopulation(int queens, int popSize) {
		ArrayList<Chromosome> population = new ArrayList<Chromosome>(popSize);
		for(int i = 0; i < popSize; i++) {
			population.add(new Chromosome(queens));
		}//for i
		return population;
	}//init
		

	//search the individual for conflicts from left to right, don't double count right to left.
	/*public static double evaluateFitness(int[] individual) {
		//updated as conflicts discovered
		double conflicts = 0;
	
		for(int i = 0; i < individual.length-2; i++) {
			//variance allows me to check diagonally from left to right (+/- value)
			int variance = 1;
			for(int j = i+1; j < individual.length-2; j++) {
				//if there's another queen in the same row
				if(individual[i]==individual[j]) {
					conflicts++;
				}
				//if we aren't checking out of bounds, and there's another queen on the upwards diagonal
				else if(individual[j] + variance < (individual.length-1) && individual[j] + variance == individual[i]) {
					conflicts++;
				}
				//if we aren't checking out of bounds and there's another queen on the downwards diagonal
				else if(individual[j] - variance > 0 && individual[j] - variance == individual[i]) {
					conflicts++;
				}
				variance++;
			}
		}
		System.out.println(1/(1+conflicts));
		return (1 / (1 + conflicts));
	}

	*/
	/*public static void nQueens(int queens) {
		int[][] population = new int[queens][queens];
		Random rand = new Random();
		for(int i = 0; i < queens; i++) {
			for(int j = 0; j < queens; j++) {
				population[i][j] = rand.nextInt(queens);
				System.out.print(population[i][j] + " ");
			}//for j
			//System.out.println("\n");
		}//for i
		//System.out.println("This all works");
	}//nQueens */
	
	
}
