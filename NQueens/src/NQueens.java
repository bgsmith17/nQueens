import java.util.*;
public class NQueens {
	
	public static void main(String[] args) {
		int queens;
		Scanner kbd = new Scanner(System.in);
		if(args.length == 1) {
			try {
				queens = Integer.parseInt(args[0]);
			}
			catch(Exception e) {
				System.out.println("Invalid Queens entry, please select an integer value");
				queens = kbd.nextInt();
			}
		}//if args passed
		else {
			System.out.println("For how many Queens should we find a solution?");
			queens = kbd.nextInt();
		}
		nQueens(queens);
		kbd.close();
		
		return;
	}//main

	class nQueens{
		int[][] population;
		nQueens(){
			
		}
		nQueens(int queens){
			initializePopulation(queens);
		}
		private void initializePopulation(int queens) {
			population = new int[queens][queens];
			Random rand = new Random();
			for(int i = 0; i < queens; i++) {
				for(int j = 0; j < queens; j++) {
					population[i][j] = rand.nextInt(queens);
				}//for j
			}//for i
		}//initialize
	}//nQueens
	
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
