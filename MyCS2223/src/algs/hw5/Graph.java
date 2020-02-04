package algs.hw5;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/** Standard undirected Graph implementation, as starting point for Q2 and Q3 on HW5. */
public class Graph {
    
    final int V;
    int E;
    Bag<Integer>[] adj;
    
	static final int INFINITY = Integer.MAX_VALUE;
    static boolean[] marked;  // marked[v] = is there an s-v path
	static int[] edgeTo;      // edgeTo[v] = w means (w,v) on s-v path
	static int[] distTo;      // distTo[v] = number of edges shortest s-v path

    
    /**
     * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
//        bfs(this, V);
    }

    /** Added this method for day20 to load graph from file. */
    public Graph (In in) {
    	this (in.readInt());
    	int E = in.readInt();
    	for (int i = 0; i < E; i++) {
    		int v = in.readInt();
    		int w = in.readInt();
    		addEdge (v,w);
    	}
    }
    
    static void bfs(Graph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++) { distTo[v] = INFINITY; }
		distTo[s] = 0;
		marked[s] = true;
		q.enqueue(s);

		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}

    public int V() { return V; }
    public int E() { return E; }


    /** Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    /** Returns the vertices adjacent to vertex <tt>v</tt>. */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /** Returns the degree of vertex <tt>v</tt>. */
    public int degree(int v) {
        return adj[v].size();
    }

    /** Fill in this method to determine if undirected graph is connected. */
    public boolean connected() {
    	marked = new boolean[V];
		distTo = new int[V];
		edgeTo = new int[V];
    	bfs(this, 0);
    	for(int i = 0; i < marked.length; i++){
    		if(!marked[i]){
    			return false;
    		}
    	}
    	return true;
    }

    /** 
     * The diameter of graph is the maximum distance between any pair of vertices. 
     * 
     * If a graph is not connected, then Integer.MAX_VALUE must be returned.
     * @return
     */
    public int diameter() {
    	int maxDistTo = 0;
    	if(!this.connected()){
    		return Integer.MAX_VALUE;
    	}
    	else{
    		for(int i = 0; i < V; i++){
    			marked = new boolean[V];
    			distTo = new int[V];
    			edgeTo = new int[V];
    			bfs(this,i);
    			for(int j = 0; j < V; j++){
    				if(distTo[j] > maxDistTo){
    					maxDistTo = distTo[j];
    				}
    			}
    		}
    	}
//    	System.out.println(maxDistTo);
    	return maxDistTo;
    }
    
    /** 
     * The status of a given vertex, v.
     * 
     * If the graph is not connected, then this method is not responsible for return value.
     */
    public int status(int v) {
    	// TODO: REPLACE WITH YOUR CODE
    	int vertices = V();
    	int edges = E();
    	int sum = 0;
    	marked = new boolean[V];
		distTo = new int[V];
		edgeTo = new int[V];
//    	if((vertices * (vertices - 1) / 2) != edges){
//    		return -1; //not connected
//    	}
    	bfs(this, v);
    	for(int i = 0; i < marked.length; i++){
    		if(!marked[i]){
    			return -1;
    		}
    	}
    	for(int i = 0; i < distTo.length; i++){
    		sum += distTo[i];
    	}
//    	System.out.println(sum);
    	return sum;
    }
    
    /**
     * Determine if all status(v) values within the graph represent different values.
     * 
     */
    public boolean statusInjective() {
    	int[] list = new int[V];
    	for(int i = 0; i < V; i++){
    		int s = status(i);
    		for(int j = 0; j < list.length; j++){
    			if(s == list[j]){
    				return false;
    			}
    		}
    		list[i] = s;
    	}
    	
    	return true;
    }
    
    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
