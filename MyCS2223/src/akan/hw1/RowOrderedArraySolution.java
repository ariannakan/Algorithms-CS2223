package akan.hw1;

import algs.hw1.arraysearch.RowOrderedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class RowOrderedArraySolution extends RowOrderedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public RowOrderedArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length(); //number of rows
		int r_min = 0;
		int r_max = n-1;
		int r_mid = 0;
		while(r_min <= r_max){
			r_mid = (r_min + r_max)/2;
			int mid_element = inspect(r_mid,0);
			if(mid_element == target){
				return new int[]{r_mid, 0};
			}
			else if(mid_element > target){	
				r_max = r_mid - 1;
				if(r_min > r_max){
					r_mid = r_max;
				}
			}
			else if(mid_element < target){
				r_min = r_mid + 1;
			}
		}
		
		int c_first = 0;
		int c_last = n-1;
		while(c_first <= c_last){
			int c_mid = (c_first + c_last)/2;
			int c_element = inspect(r_mid, c_mid);
			if(c_element == target){
				return new int[]{r_mid, c_mid};
			}else if(c_element < target){
				c_first = c_mid + 1;
			}
			else if(c_element > target){
				c_last = c_mid - 1;
			}
		}
		return null;
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = RowOrderedArraySearch.create(13);
		new RowOrderedArraySolution(ar).trial();
	}
}
