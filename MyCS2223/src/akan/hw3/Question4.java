package akan.hw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.StdIn;

/** 
 * If you place the "tale.txt" file as a top-level file in your MyCS2223 project, it will be found
 */
public class Question4 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream(new File ("tale.txt")));
		String[] strings = StdIn.readAllStrings();

		// First Construct the Binary Search Tree from these Strings where
		// the associated value is the total number of times the key appeared
		// in "The Tale Of Two Cities".
		BST<String,Integer> bt = new BST<String,Integer>();
        for(int i = 0; i < strings.length; i++){
        	String word = strings[i];
        	if(!bt.contains(word)){
        		bt.put(word, 1);
        	}
        	else{
        		int num = bt.get(word);
        		bt.put(word, ++num);
        	}
        }

		// Now output the number of leaves in the tree.
		System.out.println(bt.numLeaves() + " leaves in the tree.");
		System.out.println("Top ten most common words:");
		for(int i = 0; i < 10; i++){
			String max = bt.maxValue();
			System.out.print(max + "\t");
			System.out.println(bt.get(max));
			bt.delete(max);
		}
	
		// Now output the top ten most common words. You can do this by repeatedly deleting
		// the key value that has the maxValue in the Tree.
	}
	
}
