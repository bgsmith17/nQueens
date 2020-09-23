import java.util.*;
public class NQueens {
	
	public static void main(String[] args) {
		int queens;
		int[][] population;
		Scanner kbd = new Scanner(System.in);
		if(args.length == 1) {
			try {
				queens = Integer.parseInt(args[0]);
				population = initializePopulation(queens);
			}
			catch(Exception e) {
				System.out.println("Invalid Queens entry, please select an integer value");
				queens = kbd.nextInt();
				population = initializePopulation(queens);
			}
		}//if args passed
		else {
			System.out.println("For how many Queens should we find a solution?");
			queens = kbd.nextInt();
			population = initializePopulation(queens);
		}
		
		population[1][population[1].length-1] = (int)(100*evaluateFitness(population[1]));
		
		System.out.println(population[1][population[1].length-1]);
		kbd.close();
		return;
	}//main


	public static int[][] initializePopulation(int queens) {
		int[][] population = new int[queens+1][queens+1];
		Random rand = new Random();
		for(int i = 0; i < queens; i++) {
			for(int j = 0; j < queens; j++) {
				population[i][j] = rand.nextInt(queens);
			}//for j
		}//for i
		return population;
	}//initialize
	
	//search the individual for conflicts from left to right, don't double count right to left.
	public static double evaluateFitness(int[] individual) {
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
