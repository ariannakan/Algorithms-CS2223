

import algs.hw1.arraysearch.BandedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class BandedArraySolution3 extends BandedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public BandedArraySolution3(int[][] a) {
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
		int first_element = 0;
		while(min <= max){
			mid = (min + max)/2;
			first_element = inspect(mid,0);
			if(first_element == target){
				return new int[]{mid, 0};
			}
			else if(first_element > target){	
				max = mid - 1;
				if(min > max){
					mid = max;
				}
			}
			else if(first_element < target){
				min = mid + 1;
				if(min > max){
					mid = min;
				}
			}
		}
		int mid_element = inspect(mid, mid);
		if(target == mid_element){
			return new int[]{mid, mid};
		}
		else if(target > inspect(0, n-1)){
			return null;
		}
		else if(target < mid_element){
//			for(int c = 0; c < mid; c++){
//				if(inspect(mid, c) == target){
//					return new int[]{mid,c};
//				}
//			}
			int c_min = 0;
			int c_max = mid;
			while(c_min <= c_max){
				int c_mid = (c_min + c_max)/2;
				int element = inspect(mid,c_mid);
				if(element == target){
					return new int[]{mid, c_mid};
				}
				else if(element < target){
					c_min = c_mid + 1;
				}
				else if(element > target){
					c_max = c_mid - 1;
				}
			}
		}
		else{
//			for(int r = 0; r < mid; r++){
//				if(inspect(r, mid) == target){
//					return new int[]{r, mid};
//				}
//			}
			int r_min = 0;
			int r_max = mid;
			while(r_min <= r_max){
				int r_mid = (r_min + r_max)/2;
				int element = inspect(r_mid,mid);
				if(element == target){
					return new int[]{r_mid, mid};
				}
				else if(element < target){
					r_min = r_mid + 1;
				}
				else if(element > target){
					r_max = r_mid - 1;
				}
			}
		}
		return null;
	}
	
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = BandedArraySearch.create(3);
		new BandedArraySolution3(ar).trial();
	}
}
