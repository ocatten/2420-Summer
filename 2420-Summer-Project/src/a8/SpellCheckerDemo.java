package assign08;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A small demonstration of the SpellChecker class.
 * 
 * @author Erin Parker Parker Catten & Everett Oglesby
 * @version March 17, 2022
 */
public class SpellCheckerDemo {

	public static void main(String[] args) {
		
		SpellChecker mySC = new SpellChecker(new File("src/assign08/dictionary.txt"));
		
		runSpellCheck(mySC, "src/assign08/hello_world.txt");
		runSpellCheck(mySC, "src/assign08/good_luck.txt");
	}

	/**
	 * Runs the given spell checker (with dictionary already added) on the specified 
	 * file.
	 * 
	 * @param sc - the given spell checker
	 * @param documentFilename - name of the file to be spell checked
	 */
	private static void runSpellCheck(SpellChecker sc, String documentFilename) {
		
<<<<<<< HEAD
		BinarySearchTree<Integer> dict = new BinarySearchTree<Integer>();
		/*
		dict.add("m");
		dict.add("a");
		dict.add("b");
		dict.add("c");
		dict.add("d");
		dict.add("e");
		dict.add("f");
		dict.add("g");
		dict.add("h");
=======
		BinarySearchTree<String> dict = new BinarySearchTree<String>();
		
		dict.add("man");
		dict.add("hate");
		dict.add("coding");
		dict.add("fuck");
		dict.add("tricky");
		dict.add("cant");
		dict.add("understand");
		dict.add("why");
>>>>>>> 14664ff9c84e7b66644a44f7327a92dd3bc70395
		dict.add("i");
		dict.add("do");
		dict.add("this");
		dict.add("too");
		dict.add("myself");
		dict.add("its");
		dict.add("too");
		dict.add("fucking");
		dict.add("tricky");
		dict.add("which");
		dict.add("absolutely");
		dict.add("kills");
		dict.add("my");
		dict.add("happiness");
		dict.add("x");
		dict.add("y");
		dict.add("z");
		*/
		
<<<<<<< HEAD
		
		dict.add(5);
		dict.add(10);
		dict.add(8);
		dict.add(1);
		dict.add(3);
		dict.add(2);
		dict.add(7);
		dict.add(6);
		dict.add(4);
		dict.add(9);
		
		ArrayList<Integer> removed = new ArrayList<Integer>();
		removed.add(1);
		removed.add(4);
		removed.add(10);
		
		//System.out.println(dict.isEmpty());
		dict.removeAll(removed);
		//System.out.println(dict.isEmpty());
		
		
		
		ArrayList<Integer> dictList = dict.toArrayList();
		dictList = dict.toArrayList();
		
		for(Integer word : dictList) { 
			System.out.println(word);
=======
		System.out.println(dict.contains("fuck"));
		ArrayList<String> dictList = sc.getDictionary().toArrayList();
		
		for(String word : dictList) {
			if(word.equals("tricky")) {
				//System.out.println("completed");
			}
>>>>>>> 14664ff9c84e7b66644a44f7327a92dd3bc70395
		}
		System.out.println();
		
		//dict.binaryTreeToString(dict.getHead());
<<<<<<< HEAD
		
		// Test statements for miscellaneous methods.
		//dict.size();
		//dict.last();
		
=======
>>>>>>> 14664ff9c84e7b66644a44f7327a92dd3bc70395
		
		File doc = new File(documentFilename);
		List<String> misspelledWords = sc.spellCheck(doc);
		if(misspelledWords.size() == 0) 
			System.out.println("There are no misspelled words in file " + doc + ".");
		else {
			System.out.println("The misspelled words in file " + doc + " are:");
			for(String w : misspelledWords) 
				System.out.println("\t" + w);
		}
	}
}
