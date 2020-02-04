package algs.hw4;

import java.math.BigInteger;
import java.util.Iterator;

import akan.hw2.Node;

/**
 * Homework 4: Data Type Exercise
 * 
 * Copy this class into your USERID.hw4 package and complete.
 * 
 * Each composite number is to be represented as a binary tree of prime factors (with value as power).
 * 
 * Yes, the Composite Problem is Back!
 */
public class Composite {
 
	/**
	 * Keep track of the AVL tree of factor/exponents based at this root.
	 * 
	 * Each key is a BigInteger factor; each value is a power of that factor
	 */
	AVL<BigInteger, Integer> tree = new AVL<BigInteger, Integer>();    
	
	/**
	 * Constructs a Composite with the specified value, which may not be one, zero or negative.
	 */
	public Composite(long val) {
		this (BigInteger.valueOf(val));
	} 
	
	/**
	 * Constructs a Composite from the given Pair<BigInteger,Integer> powers.
	 * 
	 * Useful to be able to create a Composite object from pre-existing factors and exponents 
	 */
	public Composite(Iterable<Pair<BigInteger,Integer>> factors) {
		for (Pair<BigInteger,Integer> pair : factors) {
			tree.put(pair.key, pair.value);
		}
	}

	/** Helper value for factorize. */
	public final static BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

	/**
	 * Constructs a Composite with the specified value, which may not be zero, one or negative.
	 * 
	 */
	public Composite(BigInteger val) {
		if (val.compareTo(BigInteger.ZERO) <= 0) {
			throw new IllegalArgumentException ("Composite must be a non-negative value.");
		}
		if (val.compareTo(BigInteger.ONE) == 0) {
			throw new IllegalArgumentException ("Composite cannot be one.");
		}
		Composite comp = factorize(val);
		this.tree = comp.tree;
	}


	/**
	 * Returns string representation as a sequence of factors with primes
	 * 
	 * 125 returns "5^3"
	 * 36 returns "2^2 * 3^2"
	 * 
	 * Note that spaces appear between the * and the other factors
	 */
	public String toString() {
		String s = new String();
		for (Iterator<Pair<BigInteger,Integer>> pair = this.tree.pairs().iterator(); pair.hasNext();) {
			Pair<BigInteger,Integer> it = pair.next();
			s += it.key.toString();
			if(it.value > 1){
				s += "^";
				s += it.value.toString();
			}
			if(pair.hasNext()){
				s += " * ";
			}
		}
		return s;
	}

	/**
	 * Determine if two composite values are equal to each other.
	 * 
	 * Hint: Since you can't know the structure of the respective AVL trees, you should
	 * get their respective pairs as an iterator
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		return this.toString().equals(o.toString());
	}
	
	/** 
	 * Return value of Composite as a single BigInteger.
	 * 
	 * Useful for testing.
	 * 
	 * @return  a single BigInteger value representing actual value of Composite.
	 */
	public BigInteger value() {
		BigInteger num = BigInteger.ONE;
		for (Pair<BigInteger,Integer> pair : this.tree.pairs()) {
			long val = (long) Math.pow(pair.key.longValue(), pair.value);
			num = num.multiply(BigInteger.valueOf(val));
		}
		return num;
	}
	
	/** 
	 * Determine if Composite represents a prime number.
	 * 
	 * See https://en.wikipedia.org/wiki/Prime_number#Primality_of_one
	 */
	public boolean isPrime() {
		for (Iterator<Pair<BigInteger,Integer>> pair = this.tree.pairs().iterator(); pair.hasNext();) {
			Pair<BigInteger,Integer> it = pair.next();
			if(pair.hasNext() == false){
				if(it.value == 1){
					System.out.println(it.key + " " + it.value);
					System.out.println(pair.hasNext());
					return true;
				}
			}
			else{
				return false;
			}
		}
		return false;
	}

	/** 
	 * Determine whether composite is simply divisible by prime number factor.
	 * 
	 * You can assume factor is a prime number. If it is not, then this method is 
	 * not responsible for the result. 
	 */
	public boolean divisibleBy(long factor) {
		return divisibleBy(BigInteger.valueOf(factor));
	}
	
	/** 
	 * Determine whether composite is simply divisible by prime number factor.
	 * 
	 * You can assume factor is a prime number. If it is not, then this method is 
	 * not responsible for the result. 
	 */
	public boolean divisibleBy(BigInteger factor) {
		if (!factor.isProbablePrime(25)) {
			throw new IllegalArgumentException ("Factor is not prime:" + factor);
		}
		
		for (Pair<BigInteger,Integer> pair : this.tree.pairs()) {
			BigInteger num = BigInteger.ONE;
			num = num.multiply(pair.key.pow(pair.value));
			if(num.mod(factor) == BigInteger.ZERO){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Computes product of two composite numbers.
	 * 
	 * @param comp
	 * @return
	 */
	public Composite multiply(Composite comp) {
		// make copy of my AVL
//		AVL<BigInteger,Integer> copy = new AVL<BigInteger,Integer>();
		Composite copy = new Composite(this.tree.pairs());
		
		// for every pair in comp
		for (Pair<BigInteger, Integer> p : comp.tree.pairs()) {
			if (copy.tree.contains(p.key)) {
				// know something and do something
				Integer power = copy.tree.get(p.key);
				copy.tree.put(p.key, power + p.value);
			} else {
				// just want to insert into copy
				copy.tree.put(p.key, p.value);
			}
		}
		return copy;
	}

	/**
	 * Computes greatest common divisor with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Greatest_common_divisor
	 * 
	 * When there is no common divisor (other than the value 1) this method returns null
	 * 
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite gcd(Composite comp) {
		BigInteger num = BigInteger.ONE;
		int pow = 0;
		for (Pair<BigInteger,Integer> p : this.tree.pairs()) {
			for (Pair<BigInteger,Integer> c : comp.tree.pairs()) {
				if(p.key.equals(c.key)){
					if(p.value > c.value){
						pow = c.value;
					}
					else{
						pow = p.value;
					}
					num = num.multiply(p.key.pow(pow));
				}
			}
		}
		if(num.equals(BigInteger.ONE)){
			return null;
		}
		
		return new Composite(num);
	}
	
	/**
	 * Computes least common multiple with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Least_common_multiple
	 * 
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite lcm(Composite comp) {
//		Composite gcd = this.gcd(comp);
//		BigInteger num = this.value().multiply(comp.value());
//		num = num.divide(gcd.value());
//		return new Composite(num);
//		AVL<BigInteger,Integer> copy = new AVL<BigInteger,Integer>();
		Composite copy = new Composite(this.tree.pairs());
		for (Pair<BigInteger, Integer> p : comp.tree.pairs()) {
			if (copy.tree.contains(p.key)) {
				// know something and do something
				if(copy.tree.get(p.key) < p.value){
					copy.tree.put(p.key, p.value);
				}
				else{
					copy.tree.put(p.key, copy.tree.get(p.key));
				}
			} else {
				// just want to insert into copy
				copy.tree.put(p.key, p.value);
//				continue;
			}
		}
		return copy;
	}
	
	/**
	 * Return Composite number with linked list of factors in ascending order. 
	 *  
	 * @param num
	 */
	public static Composite factorize(BigInteger num) {
		AVL<BigInteger,Integer> factors = new AVL<BigInteger,Integer>();
		BigInteger end = num;
		for(BigInteger i = BigInteger.valueOf(2); i.multiply(i).compareTo(end) <= 0; i = i.add(BigInteger.ONE)){
//		for(BigInteger i = BigInteger.valueOf(2); i.compareTo(end) <= 0; i = i.add(BigInteger.ONE)){
			if(num.mod(i).equals(BigInteger.ZERO)){	
				int power = 0;
				while(num.mod(i).equals(BigInteger.ZERO)){
					power++;
					num = num.divide(i);
				}
				Pair<BigInteger,Integer> temp = new Pair<BigInteger,Integer>(i, power);
				factors.put(temp.key, temp.value);
			}
		}
		if(!num.equals(BigInteger.ONE)){
			factors.put(num, 1);
		}
		if(factors.isEmpty()){
			factors.put(num, 1);
		}
		return new Composite(factors.pairs());
	}
}
