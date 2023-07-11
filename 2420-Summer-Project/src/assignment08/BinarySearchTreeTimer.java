package assignment08;

import java.util.Random;
import java.util.TreeSet;

public class BinarySearchTreeTimer {
	
	
	public static void main(String[] args) {
		
		boolean useBinaryTree = false;
		int timesToLoop = 1000;
		
		if(useBinaryTree) {
			timeWithBinaryTree(timesToLoop);
		}
		else {
			timeWithTreeSet(timesToLoop);
		}
	}
	
	
	
	public static void timeWithBinaryTree(int givenTimesToLoop) {
		
		// Do 10000 lookups and use the average running time
		int timesToLoop = givenTimesToLoop;
				
		// For each problem size n . . .
		for(int n = 1; n < 15; n ++) {

			// Starts with a new binary search tree
			BinarySearchTree<Integer> binaryTree = new BinarySearchTree<Integer>();
					
			Random rng = new Random();
					
			// First, spin computing stuff until one second has gone by
			
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
			
			//System.out.println("Starting next loop");
			// Now, run the test
					
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
					
			for(Integer i = 0; i < loopTime; i++) {
						
				int num = rng.nextInt();
				num = Math.abs(num);
				String numToString = i.toString();
	
				binaryTree.add(num);
						
				//System.out.println("iterated thru binary tree add one time");
			}
			//System.out.println(largestArray[1]);

			double stopTime = System.currentTimeMillis();
					

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = stopTime - startTime;
			//Convert time to seconds
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	
	
	public static void timeWithTreeSet(int givenTimesToLoop) {
		
		// Do 10000 lookups and use the average running time
		int timesToLoop = givenTimesToLoop;
				
		// For each problem size n . . .
		for(int n = 1; n < 16; n ++) {

			// Starts with a new binary search tree
					
			TreeSet<Integer> treeSet = new TreeSet<Integer>();
					
			Random rng = new Random();
					
					
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
			
			//System.out.println("Starting next loop");
			// Now, run the test
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
			for(Integer i = 0; i < loopTime; i++) {
			
				int num = rng.nextInt();
				num = Math.abs(num);
				String numToString = i.toString();
	
				treeSet.add(num);
						
				//System.out.println("iterated thru binary tree add one time");
			}
			
			//System.out.println(largestArray[1]);

			double stopTime = System.currentTimeMillis();
					

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			
			// Average it over the number of runs
			double averageTime = stopTime - startTime;
			//Convert time to seconds
			averageTime = averageTime / 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
}
