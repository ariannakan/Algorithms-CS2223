package akan.hw1;

import algs.hw1.arraysearch.BandedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class BandedArraySolution extends BandedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public BandedArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length(); //number of rows
		int min = 0;
		int max = n-1;
		int mid = 0;
		int mid_element = 0;
		while(min <= max){
			mid = (min + max)/2;
			mid_element = inspect(mid,mid);
			if(mid_element == target){
				return new int[]{mid, mid};
			}
			else if(mid_element < target){
				min = mid + 1;
			}
			else if(mid_element > target){
				max = mid - 1;
			}
		}
		if(target < mid_element){
			if(target <= inspect(0, max)){
				for(int r = 0; r < max; r++){
					if(inspect(r, max) == target){
						return new int[]{r,max};
					}
				}
			}
			else{
				for(int c = 0; c <= mid; c++){
					if(inspect(mid, c) == target){
						return new int[]{mid,c};
					}
				}
			}
		}
		else{
			if(target > inspect(0, n-1)){
				return null;
			}
			else if(target <= inspect(0, max)){
				for(int r = 0; r < max; r++){
					if(inspect(r, max) == target){
						return new int[]{r, max};
					}
				}
			}
			else{
				for(int c = 0; c < min; c++){
					if(inspect(min, c) == target){
						return new int[]{min, c};
					}
				}
			}
		}
		return null;
	}
	
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = BandedArraySearch.create(13);
		new BandedArraySolution(ar).trial();
	}
}
