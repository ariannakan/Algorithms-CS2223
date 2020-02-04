package algs.hw4;

import java.util.stream.IntStream;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * All you have to do for Question 1 is rearrange the values in line 17.
 */
public class Question1 {

	public static void main(String[] args) {
		
		// find a different order to insert the numbers from 1 to 12 such that 
		// you have no rotations. You ONLY have to modify the order in which these
		// twelve numbers appears below.
//		int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
//		int[] values = { 6, 3, 9, 2, 5, 8, 11, 1, 4, 7, 10, 12};
		System.out.println("N    MaxHt.  MaxRot");
		for(double n = 1.0; n <= 12; n++){
			int N = (int)Math.pow(2.0, n) - 1;
			int maxRot = 0;
			int maxHeight = 0;
			for(int i = 0; i < 1000; i++){
				AVL<Integer,Boolean> tree = new AVL<Integer,Boolean>();
				int[] values = IntStream.range(0,N).toArray();
				StdRandom.shuffle(values);
				for (int j : values) {
					tree.put(j, true);               // for this question, the value is ignored....
				}
				if(tree.rotations > maxRot){
					maxRot = tree.rotations;
				}
				if(tree.height() > maxHeight){
					maxHeight = tree.height();
				}
			}
			System.out.println(N + "\t" + maxHeight + "\t" + maxRot);
			
		}
		
		
//		StdOut.println("Number of rotations:" + tree.rotations);
//		StdOut.println("Height of tree:" + tree.height());
		
		// validate all values are there
//		for (int i = 1; i <= 12; i++) {
//			if (!tree.contains(i)) {
//				StdOut.println ("Error:  doesn't contain " + i);
//			}
//		}
	}
}
