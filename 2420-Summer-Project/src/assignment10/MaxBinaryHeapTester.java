package assignment10;

/**
 * MaxBinaryHeapTester
 * 
 * @author Parker Catten & Everett Oglesby
 * @version 07:13:23 CS-2420_001 SUM-2023
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MaxBinaryHeapTester {	
	
/*====================================================== CONSTRUCTOR TESTS ===================================================================*/
	
	private MaxBinaryHeap<Integer> numHeap;
	
	
	@Test
	public void smallTableSetUp() {
		
		numHeap = new MaxBinaryHeap<Integer>();
		numHeap.add(1);
		
		assertEquals(new Integer(1), numHeap.peek());
	}
		
}
