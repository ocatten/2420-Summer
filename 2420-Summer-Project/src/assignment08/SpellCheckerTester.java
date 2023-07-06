package assignment08;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpellCheckerTester {
	
	SpellChecker mySC = new SpellChecker();
	SpellChecker mySCSimple = new SpellChecker();
	ArrayList<String> letters = new ArrayList<String>();
	
	public void setup() {
		letters = new ArrayList<String>();
		letters.add("A");
		letters.add("B");
		letters.add("C");
		letters.add("D");
		letters.add("E");
		letters.add("F");
		letters.add("G");
		letters.add("H");
		letters.add("I");
		letters.add("J");
		

		mySC = new SpellChecker(new File("src/assignment08/dictionary.txt"));
		mySCSimple = new SpellChecker(letters);

//		runSpellCheck(mySC, "src/assignment08/hello_world.txt");
//		runSpellCheck(mySC, "src/assignment08/good_luck.txt");
	}

	/**
	 * Runs the given spell checker (with dictionary already added) on the specified 
	 * file.
	 * 
	 * @param sc - the given spell checker
	 * @param documentFilename - name of the file to be spell checked
	 */
	private static void runSpellCheck(SpellChecker sc, String documentFilename) {

		File doc = new File(documentFilename);
		List<String> misspelledWords = sc.spellCheck(doc);
		if (misspelledWords.size() == 0) {
			System.out.println("There are no misspelled words in file " + doc + ".");
		}
		else {
			System.out.println("The misspelled words in file " + doc + " are:");
			for (String w : misspelledWords) {
				System.out.println("\t" + w);
			}
		}
	}
	
	@Test
	public void testRemove() {
		setup();
		mySC.removeFromDictionary("speculation");
		assertEquals(false, mySC.dictionaryContains("speculation"));
	}
	
	@Test
	public void testRemoveMultipleTimes() {
		setup();
		mySC.removeFromDictionary("speculation");
		assertEquals(false, mySC.dictionaryContains("speculation"));
	}
	
	@Test
	public void testRemoveAllOnSmallList() {
		setup();
		mySCSimple.removeAll(letters);
		assertEquals(0, mySCSimple.dictionaryContains("A"));
	}
	
	
}


