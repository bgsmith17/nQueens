import java.util.*;


import java.io.*;
public class TSP {

	
	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		ArrayList<Chromosome> population;
		ArrayList<Chromosome> matingPool;
		ArrayList<Node> initial = null;
		String fileName = "TSPDATA.txt";
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(fileName)).useDelimiter("\\D+");
			int[] vals = new int[3];
			int index = 0;
			int size = inputStream.nextInt();
			initial = new ArrayList<Node>(size);
			while(inputStream.hasNextInt()) {
				for(int i = 0; i < 3; i++) {
					vals[i] = inputStream.nextInt();
				}
				initial.add(new Node(vals));
				
			}
			inputStream.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found, please add TSPDATA.txt to the directory of this project");
			
		}
		System.out.println("How large is the initial population?");
		int popSize = kbd.nextInt();
		population = new ArrayList<Chromosome>(popSize);
		for(int i = 0; i < popSize; i++) {
			population.add(new Chromosome(initial));
			
		}
		Collections.sort(population, Chromosome.fitnessCompare);
		while(Chromosome.getEvaluations() < 1000000 && population.get(0).getFitness() > 118282) {
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
			System.out.println(population.get(0).getFitness());
			
			//System.out.println(population.get(0).getFitness());
			//System.out.println(Chromosome.getEvaluations());
		
		}
		population.get(0).printSolution();
		System.out.println(population.get(0).getFitness());

	kbd.close();
		
	return;
	}
}
