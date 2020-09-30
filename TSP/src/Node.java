/*Node class represents a node for an adjacency problem.  
 * 
 */
	class Node{
		//private class members
		private int nodeNum;
		private int x;
		private int y;
		
		//default constructor
		public Node() {
			
		}
		
		//single parameter constructor
		public Node(int[] vals) {
			this.nodeNum = vals[0];
			this.x = vals[1];
			this.y = vals[2];
		}
		
		//prints the nodes values
		public void printNode() {
			System.out.print("\nNumber: " + this.nodeNum + "\nX: " + this.x + "\nY: " + this.y);
		}
		
		//returns the nodes x value
		public int getX() {
			return this.x;
		}
		
		//returns the nodes y value
		public int getY() {
			return this.y;
		}
		
		//returns the nodes numerical value
		public int getNum() {
			return this.nodeNum;
		}
		//returns the euclidean distance between two nodes.
		public double computeDistance(Node n) {
			return Math.sqrt(Math.pow((this.getX() - n.getX()), 2) + Math.pow(this.getY() - n.getY(), 2));		
		}
}


