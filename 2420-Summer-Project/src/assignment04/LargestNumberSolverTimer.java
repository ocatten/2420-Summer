package assignment04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LargestNumberSolverTimer {
	public static void main(String[] args) {

		
		
		// Do 10000 lookups and use the average running time
		int timesToLoop = 200;
		
		// For each problem size n . . .
		for(int n = timesToLoop; n <= timesToLoop * 15; n += timesToLoop) {
			
			// Starts with a new number solver that's empty.
			LargestNumberSolver numberSolver = new LargestNumberSolver();
			
			Random rng = new Random();
			
			int arraySize = n+50;
			int kNum = rng.nextInt(9) + 1;
			
			List<Integer[]> givenList = new ArrayList<Integer[]>();
			System.out.println("running");
			
			for(int i = 0; i < n+100; i++) {
				
				//Random rng = new Random();
				Integer[] givenArray = new Integer[arraySize];
				for (int j = 0; j < arraySize; j++) {
					givenArray[j] = rng.nextInt(100) + 1;
				}
				givenList.add(givenArray);
			}

			//System.out.println("Finished setup");
			Random randomNumberGenerator = new Random();

			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) { // empty block 
			}
			//System.out.println("Starting next loop");
			// Now, run the test
			
			
			startTime = System.currentTimeMillis();

			Integer[] largestArray = numberSolver.findKthLargest(givenList, kNum);
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