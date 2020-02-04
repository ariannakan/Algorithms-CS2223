package algs.hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import algs.days.day12.SeparateChainingHashST;

// Note that the Day18 implementation of AVL removes <Key,Value> and only uses <Key>
import algs.days.day18.AVL;

/**
 * Modify this class for problem 1 on HW5.
 */
public class WordLadder {

	/**
	 * Represent the mapping of (uniqueID, 4-letter word)
	 */
	static SeparateChainingHashST<String,Integer> table = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();

	private static final int INFINITY = Integer.MAX_VALUE;
	static boolean[] marked;  // marked[v] = is there an s-v path
	static int[] edgeTo;      // edgeTo[v] = w means (w,v) on s-v path
	static int[] distTo;      // distTo[v] = number of edges shortest s-v path
	/**
	 * Determine if the two same-sized words are off by just a single character.
	 */
	public static boolean offByOne(String w1, String w2) {
		// FILL IN ...
		int off = 0;
		for(int i = 0; i < 4; i++){
			if(w1.charAt(i) != w2.charAt(i)){
				off++;
			}
		}
		if(off == 1){
			return true;
		}
		return false;
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
	
	static Iterable<Integer> pathTo(int v) {
		if (!marked[v]) return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}


	/**
	 * Main method to execute.
	 * 
	 * From console, enter the start and end of the word ladder.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// Use this to contain all four-letter words that you find in dictionary
		AVL<String> avl = new AVL<String>();
		
		// create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		// Note: you will have to copy this file into your project to access it, unless you
		// are already writing your code within the SedgewickAlgorithms4ed project.
		Scanner sc = new Scanner(new File ("words.english.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.length() == 4) {
				// fill in here...
				int val = table.size();
				table.put(s, val);
				reverse.put(val, s);
				avl.insert(s);
			}
		}
		sc.close();
		

		// now construct graph, where each node represents a word, and an edge exists between 
		// two nodes if their respective words are off by a single letter. Hint: use the
		// keys() method provided by the AVL tree.
		// fill in here...
		Graph g = new Graph(table.size());

		StdOut.println("Enter word to start from (all in lower case):");
		String start = StdIn.readString().toLowerCase();
		StdOut.println("Enter word to end at (all in lower case):");
		String end = StdIn.readString().toLowerCase();

		// need to validate that these are both actual four-letter words in the dictionary
		if (!avl.contains(start)) {
			StdOut.println (start + " is not a valid word in the dictionary.");
			System.exit(-1);
		}
		if (!avl.contains(end)) {
			StdOut.println (end + " is not a valid word in the dictionary.");
			System.exit(-1);
		}

		// Once both words are known to exist in the dictionary, then create a search
		// that finds shortest distance (should it exist) between start and end.
		// be sure to output the words in the word ladder, IN ORDER, from the start to end.

		// fill in here...
		String min = avl.min();
		for(String key: avl.keys()){
			for (String key2 : avl.keys(min, key)) {
				 if(offByOne(key,key2)){
				    g.addEdge(table.get(key), table.get(key2));
				 }
			}
		}
		g.toString();


		// conduct a BFS over entire graph
		marked = new boolean[g.V()];
		distTo = new int[g.V()];
		edgeTo = new int[g.V()];
		bfs(g,table.get(start));

		// recover path
		Iterable<Integer> path = pathTo(table.get(end));
		for(int i :pathTo(table.get(end))){
			StdOut.print(reverse.get(i) + " - ");
		}
		

	}
}
