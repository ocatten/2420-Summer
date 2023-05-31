package assignment03;

import java.util.Random;

public class PriorityQueueTimer<E> {
	public static void main(String args[]) {
		
		
		
		
		Random randomNumberGenerator = new Random();
		
		// Do 10000 lookups and use the average running time
		int timesToLoop = 10;
		
		// For each problem size n . . .
		for(int n = 10000; n <= 200000; n += 10000) {
			
			// Starts with a new priority queue that's empty.
			SimplePriorityQueue priorityQueue = new SimplePriorityQueue();
			
			priorityQueue.backingArray = new Object[n];
			for(Integer i = 0; i < n; i++) {
				priorityQueue.backingArray[i] = i;

				
			}

			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 1000) { // empty block 
			}
			System.out.println("Starting next loop");
			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			priorityQueue.findMax();

			double stopTime = System.currentTimeMillis();
			

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = stopTime - startTime;
			averageTime = averageTime * 1000;
			System.out.println(n + "\t" + averageTime);
		}
	}
}
