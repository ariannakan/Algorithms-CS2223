package akan.hw3;

import edu.princeton.cs.algs4.StdRandom;

/**
 * This is the template code for question 1.
 *
 * Be sure to Explain whether the empirical results support the proposition.
 *
 */
public class Question1 {
	public static void main(String[] args) {
		
		// for N in 16 .. 512
//		System.out.println("N     MaxComp MaxExch MinComp MinExch");
		System.out.println("N     MaxComp MaxExch");
		for(int N = 16; N <= 512; N *= 2){
			int maxCmp = 0;
//			int minCmp = N*2;
//			int minExch = N;
			int maxExch = 0;
			for(int n = 0; n < 100; n++){
				Double[] test = new Double[N];
				for(int i = 0; i < N; i++){
					test[i] = StdRandom.uniform();
				}
				Heap.constructHeap(test);
				if(Heap.numCmp > maxCmp){
					maxCmp = Heap.numCmp;
				}
//				if(Heap.numCmp < minCmp){
//					minCmp = Heap.numCmp;
//				}
//				if(Heap.numExch < minExch){
//					minExch = Heap.numExch;
//				}
				if(Heap.numExch > maxExch){
					maxExch = Heap.numExch;
				}
			}
//			System.out.printf("%2d\t%2d\t%2d\t%2d\t%2d\n", N, maxCmp, maxExch, minCmp, minExch);
			System.out.printf("%2d\t%2d\t%2d\n", N, maxCmp, maxExch);

		}
		//   for each N, do T=100 trials you want to keep track of 
		//       what you believe to be the MOST number of exch invocations
		//       and most number of less invocations
		
		//       compute a random array of N uniform doubles
		
		//   Make sure you output for each N the maximum values you saw
		//   in a table like...
		//
		//       N   MaxComp    MaxExch
		//       16  22         8
		//     .....
	}
}
