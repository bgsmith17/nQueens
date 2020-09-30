
	class Node{
		private int nodeNum;
		private int x;
		private int y;
		public Node() {
			
		}
		
		public Node(int[] vals) {
			this.nodeNum = vals[0];
			this.x = vals[1];
			this.y = vals[2];
		}
		
		public void printNode() {
			System.out.print("\nNumber: " + this.nodeNum + "\nX: " + this.x + "\nY: " + this.y);
		}
		
		public int getX() {
			return this.x;
		}
		
		public int getY() {
			return this.y;
		}
		public int getNum() {
			return this.nodeNum;
		}
		public double computeDistance(Node n) {
			return Math.sqrt(Math.pow((this.getX() - n.getX()), 2) + Math.pow(this.getY() - n.getY(), 2));		
		}
}


