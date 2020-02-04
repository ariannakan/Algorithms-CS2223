package akan.hw3;

/**
 * The declaration of SequentialSearchST now declares that each key can be compared
 * with each other key via the Comparable interface
 * 
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchSortedST<Key extends Comparable<Key>, Value> {
	int N;           // number of key-value pairs
	Node first;      // the linked list of key-value pairs

	// Nodes now store (key and value)
	class Node {
		Key   key;
		Value value;
		Node  next;

		public Node (Key key, Value val, Node next)  {
			this.key  = key;
			this.value  = val;
			this.next = next;
		}
	}

	public int size()                 { return N;  }
	public boolean isEmpty()          { return size() == 0; }
	public boolean contains(Key key)  { return get(key) != null; }


	/**
	 * Be sure to modify this method to stop once you have found a key that is
	 * larger than the key you are looking for.
	 * 
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		Node n = first;
		while (n != null) {
			if (key.compareTo(n.key) < 0){
				return null;
			}
			else if (key.equals (n.key)) {
				return n.value;
			}

			n = n.next;
		}

		return null;  // not present
	}

	/**
	 * Be sure to modify this method to insert the key into its proper place
	 * in ascending sorted order.
	 * 
	 * @param key
	 * @return
	 */
	public void put(Key key, Value val) {
		if (val == null) {
			delete (key);
			return;
		}
		Node prev = null;
		Node n = first;

//		if(n == null){
//			first = new Node(key, val, null);
//			last = first;
//			N = 1;
////			System.out.print("first  ");
////			System.out.println(first.key);
//			return;
		//		}

		while (n != null) {
			if (key.equals (n.key)) {
				n.value = val;
				return;
			}
			//			if(n.next != null){
			if((key.compareTo(n.key) < 0)){
				if(prev == null){
					first = new Node(key, val, n);
					N++;
					return;
				}
				Node temp = new Node(key, val, n);
				prev.next = temp;
				N++;
				//				System.out.print("inserted");
				//				System.out.println(n.next.key);
				return;
			}
			else if(n.next == null){
				n.next = new Node(key, val, null);
				N++;
				return;
			}

			//			}
			prev = n;
			n = n.next;
		}
		
		first = new Node(key, val, first);
		N++;
		// add as new node at beginning
//		Node oldlast = last;
//		last = new Node (key, val, null);
//		oldlast.next = last;
//		N++;
	}

	/**
	 * Can short-circuit this method once you hit a key value that is larger 
	 * than the target key being deleted.
	 * 
	 * @param key
	 */
	public void delete(Key key) {
		Node prev = null;
		Node n    = first;
		while (n != null) {
			if (key.compareTo(n.key) > 0){
				return;
			}
			else if (key.equals (n.key)) {
				if (prev == null) {       // no previous? Must have been first
					first = n.next;
				} else {
					prev.next = n.next;   // have previous one linke around
				}
				//N--;
				return;
			}

			prev = n;                     // don't forget to update!
			n = n.next;
		}
	}
}