package akan.hw1;

import algs.hw1.arraysearch.SpiralArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class SpiralArraySolution extends SpiralArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public SpiralArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length();
		int r_init = 0;
		int c_init = 0;
		int r_final = n - 1;
		int c_final = n - 1;
		while(target <= n*n){
			if(inspect(r_final, c_init) <= target){
				for(int c = c_init; c <= c_final; c++){
					if(inspect(r_final, c) == target){
						return new int[]{r_final, c};
					}
				}
			}
			else if(inspect(r_init, c_init) <= target){
				for(int r = r_init; r <= r_final; r++){
					if(inspect(r, c_init) == target){
						return new int[]{r, c_init};
					}
				}
			}
			else if(inspect(r_init, c_final) <= target){
				for(int c = c_init; c <= c_final; c++){
					if(inspect(r_init, c) == target){
						return new int[]{r_init, c};
					}
				}
			}
			else if(inspect(r_final - 1, c_final) <= target){
				for(int r = r_init; r <= r_final - 1; r++){
					if(inspect(r, c_final) == target){
						return new int[]{r, c_final};
					}
				}
			}
			else{
				r_final--;
				c_final--;
				r_init++;
				c_init++;
			}
		}
		return null;
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = SpiralArraySearch.create(13);
		new SpiralArraySolution(ar).trial();
	}
}
