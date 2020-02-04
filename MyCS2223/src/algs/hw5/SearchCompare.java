package algs.hw5;

import algs.days.day19.Graph;
import edu.princeton.cs.algs4.*;

/**
 * Compute the BreadthFirst distance and DepthFirst distance for each vertex from the initial vertex 0.
 * Call these bfsDistTo[v] and dsfDistTo[v]. 
 * 
 * Observe that bfsDistTo[v] is always smaller than or equal to dfsDistTo[v].
 * Now define excess[v] = dfsDistTo[v] - bfsDistTo[v]. This assignment asks you to compute the
 * sum total of excess[v] for all vertices in the graph G.
 * 
 * Note that it is possible that some vertices are unreachable from s, and thus the dfsDistTo[v] and
 * bfsDistTo[v] would both be INFINITY. 
 */
public class SearchCompare {
	
	static boolean[] dfs_marked;    // marked[v] = is there an s-v path?
	static int[] dfs_distTo;        // distTo[v] = number of edges shortest s-v path
	
	
	static final int INFINITY = Integer.MAX_VALUE;
    static boolean[] bfs_marked;  // marked[v] = is there an s-v path
	static int[] bfs_distTo;      // distTo[v] = number of edges shortest s-v path
	
	
	// depth first search from v
	static void dfs(Graph G, int v) {
		dfs_marked[v] = true;
		for (int w : G.adj(v)) {
			if (!dfs_marked[w]) {
				dfs_distTo[w] = dfs_distTo[v] + 1;
				dfs(G, w);
			}
		}
	}
	
	static void bfs(Graph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++) { bfs_distTo[v] = INFINITY; }
		bfs_distTo[s] = 0;
		bfs_marked[s] = true;
		q.enqueue(s);

		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!bfs_marked[w]) {
					bfs_distTo[w] = bfs_distTo[v] + 1;
					bfs_marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}
	
	
	public static int excess(Graph G, int s) {
		int sum = 0;
//			dfs_edgeTo = new int[G.V()];
			dfs_distTo = new int[G.V()];
			dfs_marked = new boolean[G.V()];
			bfs_marked = new boolean[G.V()];
			bfs_distTo = new int[G.V()];
			for(int i = 0; i < G.V(); i++){
				dfs_distTo[i] = INFINITY;
			}
			dfs_distTo[s] = 0;
			dfs(G,s);
			bfs(G,s);
			//System.out.println(dfs_edgeTo[i]);
			for(int i = 0; i < G.V(); i++){
				sum += (dfs_distTo[i] - bfs_distTo[i]);
			}
		return sum;
	}
	

	public static void main(String[] args) {
		String input;
		if (args.length != 0) {
			input = args[0];
		} else {
			input = "tinyG.txt";
		}
		In in = new In(input);
		Graph g = new Graph(in);

		// Compute and report Excess on tinyG.txt by default
		System.out.println("Excess:" + SearchCompare.excess(g, 0));
		
		for (int N = 4; N <= 1024; N *= 2) {
			System.out.print(N + "\t" );
			Graph gr = new Graph(N);
				
				// every possible edge exists with probability p
				for (int i = 0; i < N-1; i++) {
					for (int j = i+1; j < N; j++) {
						gr.addEdge(i, j);
					}
				}
				
				System.out.println(SearchCompare.excess(gr, 0) + "\t");
			}
			
		}
	}

