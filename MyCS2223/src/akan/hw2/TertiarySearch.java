package akan.hw2;

import edu.princeton.cs.algs4.StdOut;

/**
 * This cousin to BinarySearch attempts to locate a value by dividing the array into "thirds"
 * and checking within each third for the target value.
 */
public class TertiarySearch {

	/** Record the number of inspections. */
	static int numInspections = 0;
	
	/**
	 * Request tertiary array search on the given collection and return whether value was found.
	 * 
	 * After this function completes, the static value numInspections records the total number
	 * of array inspections needed.
	 * 
	 * Note numInspections is reset each time this function is called.
	 * 
	 * DO NOT MODIFY THIS METHOD.
	 * 
	 * @param collection
	 * @param target
	 * @return
	 */
	public static boolean find (int[] collection, int target) {
		numInspections = 0;
		return find(collection, target, 0, collection.length - 1);
	}
	
	/**
	 * Given an array of ascending values, subdvide into "thirds" and attempt to locate
	 * recursively using a Tertiary Array Search.
	 * 
	 * You do not need to modify this method. You should not call this method directly.
	 * Rather call find(collection, target).
	 *  
	 * @param collection    ascending order collection
	 * @param target        target to be sought
	 * @param lo            low end of range within which search proceeds (inclusive)
	 * @param hi            high end of range within which searhc proceeds (inclusive)
	 */
	static boolean find (int[] collection, int target, int lo, int hi) {
		while (lo <= hi) {
			int len = (hi-lo)/3;
			int left = lo + len;
					
			int rc = collection[left] - target; numInspections++;
			if (rc > 0) {
				return find (collection, target, lo, left-1);         // lower third
			} else if (rc < 0) {
				int right = left + len + 1;
				rc = collection[right] - target; numInspections++;

				if (rc > 0) {
					return find(collection, target, left+1, right-1); // middle third
				} else if (rc < 0) {
					return find(collection, target, right+1, hi);     // upper third
				} else {
					return true;                                      // found at right
				}
			} else {
				return true;                                          // found at original left
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		StdOut.println("N\tMax\tTotal\tFull Counts");
		for (int n = 1; n <= 20; n++) {
			int[] a = new int[n];
			int[] num = new int[n];
			int total = 0;
			int max = 0;
			for(int i = 0; i < n; i++){
				a[i] = i+1;
			}
			for(int j = 1; j <= n; j++){
				find(a,j);
				num[j-1] = numInspections;
				int last = numInspections;
				if(last > max){
					max = last;
				}
				total += numInspections;
			}
			System.out.print(n + "\t" + max + "\t" + total + "\t");
			for(int k = 0; k < n; k++){
				System.out.print(num[k]);
				if(k != n-1){
					System.out.print(", ");
				}
			}
			System.out.println("\n");
		}
	}
}
