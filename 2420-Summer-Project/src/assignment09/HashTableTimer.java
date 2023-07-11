package assignment09;

import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

public class HashTableTimer {
	
	
	public static void main(String[] args) {
		
		int timesToLoop = 1000;
		//badTimeHash(timesToLoop);
		//mediumTimeHash(timesToLoop);
		//goodTimeHash(timesToLoop);
		//stringHashTable(timesToLoop);
		stringHashMap(timesToLoop);
	}
	
	
	
	public static void badTimeHash (int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 16; n ++) {
			
			HashTable<Integer, StudentBadHash> studHash = new HashTable<Integer, StudentBadHash>();

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
					
			for(Integer i = 0; i < loopTime; i++) {
				
				StudentBadHash hashStudent = new StudentBadHash (Math.abs(rng.nextInt()), Integer.valueOf(Math.abs( rng.nextInt() )).toString(),
																Integer.valueOf(Math.abs( rng.nextInt() )).toString());
				
				studHash.put(hashStudent.hashCode(), hashStudent);
			}

			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000*n;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	
	
	public static void mediumTimeHash (int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 16; n ++) {
			
			HashTable<Integer, StudentMediumHash> studHash = new HashTable<Integer, StudentMediumHash>();

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
					
			for(Integer i = 0; i < loopTime; i++) {
				
				StudentMediumHash hashStudent = new StudentMediumHash (Math.abs(rng.nextInt()), Integer.valueOf(Math.abs( rng.nextInt() )).toString(),
																Integer.valueOf(Math.abs( rng.nextInt() )).toString());
				
				studHash.put(hashStudent.hashCode(), hashStudent);
			}

			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000*n;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	
	
	public static void goodTimeHash (int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 16; n ++) {
			
			HashTable<Integer, StudentGoodHash> studHash = new HashTable<Integer, StudentGoodHash>();

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
					
			for(Integer i = 0; i < loopTime; i++) {
				
				StudentGoodHash hashStudent = new StudentGoodHash(Math.abs(rng.nextInt()), Integer.valueOf(Math.abs( rng.nextInt() )).toString(),
																Integer.valueOf(Math.abs( rng.nextInt() )).toString());
				
				studHash.put(hashStudent.hashCode(), hashStudent);
			}

			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000*n;
			System.out.println(n + "\t" + averageTime);
		}
	}
	
	
	
	public static void stringHashTable (int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 16; n ++) {
			
			HashTable<Integer, String> studHash = new HashTable<Integer, String>();

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
					
			for(Integer i = 0; i < loopTime; i++) {
				
				studHash.put(i.toString().hashCode(), i.toString());
			}

			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000*n;
			System.out.println(n + "\t" + averageTime);
		}
	}



	public static void stringHashMap(int timesToLoop) {
		
		// For each problem size n . . .
		for(int n = 1; n < 16; n ++) {
			
			TreeMap<Integer, String> studHash = new TreeMap<Integer, String>();

			Random rng = new Random();
			double startTime = System.currentTimeMillis();
			while(System.currentTimeMillis() - startTime < 100) {} // empty block
					
			int loopTime = timesToLoop * n;
			startTime = System.currentTimeMillis();
					
					
			for(Integer i = 0; i < loopTime; i++) {
				
				studHash.put(i.toString().hashCode(), i.toString());
			}

			double stopTime = System.currentTimeMillis();
			double averageTime = stopTime - startTime;
			
			averageTime = averageTime / 1000*n;
			System.out.println(n + "\t" + averageTime);
		}
	}

}
