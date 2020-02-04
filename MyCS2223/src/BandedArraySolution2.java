

import algs.hw1.arraysearch.BandedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class BandedArraySolution2 extends BandedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public BandedArraySolution2(int[][] a) {
		super(a);
	}
	
	public int[] ba_search(int _min, int _max, int target, int event, boolean bounds){ //int bounds: true for <=; false for <
		int mid = 0;
		int min = _min;
		if(bounds){
			int max = _max;
		}
		int max = _max - 1;
		int element = 0;
		int x = 0; int y = 0;
		while(min <= max){
			mid = (min + max)/2;
			if(event == 1){
				element = inspect(mid, max);
				x = mid;
				y = max;
			}
			else if(event == 2){
				element = inspect(max, mid);
				x = max;
				y = mid;
			}
			else if(event == 3){
				element = inspect(mid, max);
				x = mid;
				y = max;
			}
			else if(event == 4){
				element = inspect(max, mid);
				x = max;
				y = mid;
			}
			if(element == target){
				return new int[]{x, y};
			}else if(element < target){
				min = mid + 1;
			}
			else if(element > target){
				max = mid - 1;
			}
		}
		return null;
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
		int event = 0;
		if(target < mid_element){
			if(target <= inspect(0, max)){
//				for(int r = 0; r < max; r++){
//					if(inspect(r, max) == target){
//						return new int[]{r,max};
//					}
//				}
				event = 1;
				return ba_search(0, max, target, event, false);
			}
			else{
//				for(int c = 0; c <= mid; c++){
//					if(inspect(mid, c) == target){
//						return new int[]{mid,c};
//					}
//				}
				event = 2;
				return ba_search(0, mid, target, event, true);
			}
		}
		else{
			if(target > inspect(0, n-1)){
				return null;
			}
			else if(target <= inspect(0, max)){
//				for(int r = 0; r < max; r++){
//					if(inspect(r, max) == target){
//						return new int[]{r, max};
//					}
//				}
				event = 3;
				return ba_search(0, max, target, event, false);
			}
			else{
//				for(int c = 0; c < min; c++){
//					if(inspect(min, c) == target){
//						return new int[]{min, c};
//					}
//				}
				event = 4;
				return ba_search(0, min, target, event, false);
			}
			
		}
	}
	
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = BandedArraySearch.create(4);
		new BandedArraySolution2(ar).trial();
	}
}
