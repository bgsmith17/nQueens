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
		
		
		kbd.close();
		return;
	}//main


	public static int[][] initializePopulation(int queens) {
		int[][] population = new int[queens][queens];
		population = new int[queens][queens];
		Random rand = new Random();
		for(int i = 0; i < queens; i++) {
			for(int j = 0; j < queens; j++) {
				population[i][j] = rand.nextInt(queens);
			}//for j
		}//for i
		return population;
	}//initialize
	
	public static double evaluateFitness(int[] individual) {
		int conflicts = 0;
		for(int i = 0; i < individual.length; i++) {
			
		}
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
