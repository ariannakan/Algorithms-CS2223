package akan.hw2;

import java.math.BigInteger;

import edu.princeton.cs.algs4.Stack;

/**
 * Homework 2, Question 3: Data Type Exercise
 * 
 * Copy this class into your USERID.hw2 package and complete.
 * 
 * Note: You should not attempt to store as a single BigInteger the value of the composite number.
 * That is, only keep track of the factor/exponents linked list only.
 */
public class Composite {
 
	/** Keep track of the linked list of factor/exponents starting with this head. */
	Node head;    
	
	/**
	 * Constructs a Composite with the specified value, which may not be one, zero or negative.
	 */
	public Composite(long val) {
		this (BigInteger.valueOf(val));
	} 

	/**
	 * Constructs a Composite with the specified value, which may not be zero or negative.
	 * 
	 * Must handle unit case properly.
	 */
	public Composite(BigInteger val) {
		if (val.compareTo(BigInteger.ZERO) <= 0) {
			throw new IllegalArgumentException ("Composite must be a non-negative value.");
		}
		else if (val.compareTo(BigInteger.ONE) == 0){
			this.head = new Node(1,1);
		}
		else{
			Composite comp = factorize(val);
			this.head = comp.head;
		}
	}

	/**
	 * Helper constructor only by use within this class, for creating a new (empty) composite
	 * object. You will find this useful in multiply.
	 * 
	 * Must be private to ensure no one outside this class calls it directly. Note that the 
	 * Composite object returned is treated like the value 1.
	 */
	Composite() {

	}

	/**
	 * Returns string representation as a sequence of factors with primes
	 * 
	 * 125 returns "5^3"
	 * 36 returns "2^2 * 3^2"
	 * 
	 * 1 returns "1" (special case)
	 */
	public String toString() {
//		if(this.head == null){
//			return "1";
//		}
		Node current_node = this.head;
		String s = current_node.toString();
		while(current_node.next != null){
			s += " * ";
			s += current_node.next.toString();
			current_node = current_node.next;
		}
		return s;
	}

	/**
	 * Determine if two composite values are equal to each other.
	 */
	public boolean equals (Object o) {
//		if(o == this){
//			return true;
//		}
//		if (o == null) { return false; }
//		
//		if (o instanceof Composite) {
//			Composite other = (Composite) o;
//			Node current_node = this.head;
//			while(current_node != null){
//				if((current_node.factor != other.head.factor) || (current_node.power != other.head.power)){
//					return false;
//				}
//				else{
//					current_node = current_node.next;
//					other.head = other.head.next;
//				}
//			}
//			return true;
//		}
//		
//		return false;
		if (o == null) { return false; }
		return this.toString().equals(o.toString());
	}
	
	/** 
	 * Return value of Composite as a single BigInteger.
	 * 
	 * Necessary when adding two composite numbers a+b when gcd(a,b) != a and gcd(a,b) != b.
	 * Note this is typically the case with two random numbers.
	 * 
	 * Also useful for testing.
	 * 
	 * @return  a single BigInteger value representing actual value of Composite number.
	 */
	public BigInteger value() {
		BigInteger val = BigInteger.ONE;
		Node current_node = this.head;
		while(current_node != null){
			val = val.multiply(BigInteger.valueOf(current_node.factor).pow(current_node.power));
			current_node = current_node.next;
		}
		return val;
	}
	
	/** 
	 * Determine if Composite represents a prime number.
	 * 
	 * Note: the unit composite number (i.e., 1) is not a prime number.
	 * 
	 * See https://en.wikipedia.org/wiki/Prime_number#Primality_of_one
	 */
	public boolean isPrime() {
		Node current_node = this.head;
		if(current_node.next == null) {
			if(current_node.factor != 1){
				if(current_node.power == 1){
					return true;
				}
			}
		}
		return false;
	}

	/** 
	 * Determine if Composite represents the unit number 1.
	 */
	public boolean isUnit() {
		boolean unit = false;
		if(this.head == null){
			return true;
		}
		if(Math.pow(this.head.factor, this.head.power) == 1.0){
			unit = true;
		}
		return unit;
	}

	/**
	 * Computes sum of two composite numbers.
	 * 
	 * For full credit, performance of code must be directly proportional to N and M (where
	 * N is number of factors in 'this' and M is number of factors in comp) PLUS the 
	 * extra cost of factoring the BigInteger (this + comp)/gcd(this,comp).
	 *  
	 * In other words, for full credit, your code must attempt to do the following:
	 *   a) Find a common factor to both and then add together the remaining terms. 
	 * 
	 * (2^3 * 3^2 * 5 * 7) + (2^2 * 5^2 * 11) =  2520 + 1100
	 * 
	 * In the above, the common factor is (2^2 * 5) which is extracted, leaving behind 
	 * the remainder of this (2 * 3^2 * 7) and the remainder of comp (5 * 11).
	 * 
	 * (2^2 * 5) * ( 2 * 3^2 * 7 + 5*11)      = 20 * ( 126 + 55 )
	 * 
	 * The following logic can be used to reflect the result above. That is, once you
	 * are able to compute the 'common' Composite number, you multiply it by the 
	 * Composite value which is the result of adding the two remainders together, each
	 * converted first to a BigInteger.
	 * 
	 * common.multiply(factorize(remainderComp.value().add(remainder.value())));
	 * 
	 * @param comp
	 * @return
	 */
	public Composite add(Composite comp) {
		// REPLACE WITH WORKING CODE
		//do last
//		Composite common_factor = new Composite();
//		common_factor = this.lcm(comp);
//		long factor = 0;
//		while(common_factor != null){
//			factor *= Math.pow(common_factor.head.factor, common_factor.head.power);
//			common_factor.head = common_factor.head.next;
//		}
		long t = 1;
		while(this.head != null){
			t *= Math.pow(this.head.factor, this.head.power);
			this.head = this.head.next;
		}
//		t = t - factor;
		long c = 1;
		while(comp.head != null){
			c *= Math.pow(comp.head.factor, comp.head.power);
			comp.head = comp.head.next;
		}
//		c = c - factor;
//		long remainder = c + t;
		long sum = c + t;
		
		return new Composite(BigInteger.valueOf(sum));
	}
	
	/**
	 * Computes product of two composite numbers.
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in 
	 * self and M is the number of factors in comp.
	 * 
	 * Simply returns 'this' when asked to multiply by 1 (the unit Composite number).
	 * Alternatively, if the unit composite number is asked to be multiplied by another
	 * composite number, then that number is simply returned.
	 * 
	 * @param comp
	 * @return
	 */
	public Composite multiply(Composite comp) {
		if(comp.isUnit()){
			return this;
		}
		if(this.isUnit()){
			return comp;
		}
		Node head = comp.head;
		Node current_node = this.head;
		//long product = 1;
		BigInteger product = BigInteger.ONE;
		while (current_node != null){
			long factor = (long) Math.pow(current_node.factor, current_node.power);
			product = product.multiply(BigInteger.valueOf(factor));
			current_node = current_node.next;
		}
		while (comp.head != null){
			long factor = (long)Math.pow(comp.head.factor, comp.head.power);
			product = product.multiply(BigInteger.valueOf(factor));
			comp.head = comp.head.next;
		}
		Composite p = new Composite();
		Node n = new Node(product.longValue(), 1);
		p.head = n;
		comp.head = head;
		return p;
	}
	

	/**
	 * Computes greatest common divisor with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Greatest_common_divisor
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in 
	 * self and M is the number of factors in comp.
	 * 
	 * The greatest common divisor of (comp,1) is 1
	 * 
	 * When there is no common divisor (other than the value 1) this method returns
	 * a unit composite number.
	 * 
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite gcd(Composite comp) {
		int pow = 0;
		Node head = new Node(0,0);
		Node divisor = head;
		Node current_node = this.head;
		Node first = comp.head;
		Node comp_head = first;
		while(current_node != null){
			while(comp.head != null){
				if(current_node.factor == comp.head.factor){
					if(current_node.power > comp.head.power){
						pow = comp.head.power;
					}
					else{
						pow = current_node.power;
					}
					divisor.next = new Node(current_node.factor, pow);

					divisor = divisor.next;
				}
				
				comp.head = comp.head.next;
			}
			comp.head = comp_head;
			current_node = current_node.next;
		}
		Composite head_comp = new Composite();
		if(head.next == null){
			head_comp.head = new Node(1,1);
		}
		else{
			head_comp.head = head.next;
		}
		comp.head = first;
		return head_comp;
	}
	
	/**
	 * Computes least common multiple with given composite number.
	 * 
	 * https://en.wikipedia.org/wiki/Least_common_multiple
	 * 
	 * Resulting code must be O(N + M) where N is the number of factors in 
	 * self and M is the number of factors in comp.
	 * 
	 * The least common multiple of (comp,1) is comp.
	 * 
	 * @param comp    other number to compute gcd
	 * @return the greatest common divisor between this and comp.
	 */
	public Composite lcm(Composite comp) {
		if(this.head.factor == 1){
			return comp;
		}
		if(comp.head.factor == 1){
			return this;
		}
		Composite multiple = new Composite();
		multiple = this.multiply(comp);
		long num = multiple.head.factor;
		long d = 1;
		Composite com_divisor = this.gcd(comp);
		while(com_divisor.head != null){
			d *= Math.pow(com_divisor.head.factor, com_divisor.head.power);
			com_divisor.head = com_divisor.head.next;
		}
		long ans = num / d;
		BigInteger ans_b = BigInteger.valueOf(ans);
		
		return new Composite(ans_b);
	}
	
	
	/** Helper value for factorize. */
	public final static BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

	/**
	 * Return Composite number with linked list of factors in ascending order. 
	 *  
	 * @param num
	 * @return
	 */
	public static Composite factorize(BigInteger num) {
		Composite comp = new Composite();
		Node head = new Node(0,0);
		Node tail = head;
		//long val = num.longValue();
		BigInteger end = num;
		for(BigInteger i = BigInteger.valueOf(2); i.multiply(i).compareTo(end) <= 0; i = i.add(BigInteger.ONE)){
			if(num.mod(i).equals(BigInteger.ZERO)){
				tail.next = new Node(i.longValue(), 0);			
				while(num.mod(i).equals(BigInteger.ZERO)){
					tail.next.power++;
					num = num.divide(i);
				}
				tail = tail.next;
			}
		}
		if(!num.equals(BigInteger.ONE)){
			tail.next = new Node(num.longValue(), 1);
			tail = tail.next;
		}
		
		if(tail.factor == 0){
			head.factor = num.longValue();
			head.power = 1;
			comp.head = head;
		}
		else{
			comp.head = head.next;
		}
		comp.toString();
		return comp;
	}
}
