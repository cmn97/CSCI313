/**
 *  * Student: Christina Hintopoulos
 *   */

public class A1LinkedListHC{
   	public static void main(String argc[]) throws Exception{
		LinkedList<Integer> sl = new LinkedList<>();
		PolynomialLinkedList sum = new PolynomialLinkedList();
		PolynomialLinkedList prod = new PolynomialLinkedList();
		
		for (int i = 1000; i > 0; i-=3) sl.add(i);
		
		System.out.println("Mid Element is " + sl.midElement());
		
		try {
			sl.insert(111, sl.getNode(50), sl.getNode(51));
			if (sl.detectLoop()) System.out.println("Loop!");
			else System.out.println("No loop.");
		
		
			sl.insert(123, sl.getNode(51), sl.getNode(50));
			if (sl.detectLoop()) System.out.println("Loop!");
			else System.out.println("No loop.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		PolynomialLinkedList p1, p2, p3, p4;
		p1 = new PolynomialLinkedList(2,3);
		p2 = new PolynomialLinkedList(3,2);
		p3 = p1.add(p2);
		p1 = new PolynomialLinkedList(3,2);
		p2 = new PolynomialLinkedList(1,0);
		p4 = p1.add(p2);
		sum = p3.add(p4);
		prod = p3.multiply(p4);
		p3.print();
		p4.print();
		sum.print();
		prod.print();
	}
}
class LinkedList<E>{
	private static class Node<E>{
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> n){
			element = e;
			next = n;
		}
		public E getElement(){
			return element;
		}
		public Node<E> getNext(){
			return next;
		}
		public void setElement(E e){
			element = e;
		}
		public void setNext(Node<E> n){
			next = n;
		}
	}
	private Node<E> head;
	public LinkedList(){
		head = null;
	}
	public void add(E e){
		Node<E> temp = new Node<>(e, head);			
		head = temp;
	}
	public void insert(E e, Node<E> p, Node<E> n){
		p.setNext(new Node<>(e, n));      
	}
	public Node<E> getNode(int i) throws Exception{
		Node<E> temp = head;
		while (i > 0){
			if (temp == null) throw new Exception("Out of bound");
			temp = temp.getNext();
			i--;
		}
		return temp;
	}
	public E midElement() throws Exception{
		Node prev = this.getNode(0);
		Node next = prev.getNext();
		if(head != null) {
			while (next != null && next.getNext() != null) {
				prev = prev.getNext();
				next = next.getNext().getNext();
				if (next.getNext() == null) return (E) prev.getNext().getElement();
			}
		}
		return (E) prev.getElement();
	}
	public boolean detectLoop() throws Exception{
		if(this.getNode(0) == null) return false;
		Node prev = this.getNode(0);
		Node next = prev.getNext();
		while (prev != next) {
			if (next == null || next.getNext() == null) return false;
			prev = prev.getNext();
			next = next.getNext().getNext();
		}
		return true; 
	}
}

class PolynomialLinkedList{
	private static class PNode{
		private int coe;
		private int exp;
		private PNode next;
		public PNode(int c, int e){
			this(c, e, null);
		}
		public PNode(int c, int e, PNode n){
			coe = c;
			exp = e;
			next = n;
		}
		public void setCoe(int c) {
			coe = c;
		}
		public void setExp(int e){ exp = e;}
		public void setNext(PNode n){ next = n;}
		public int getCoe(){ return coe;}
		public int getExp(){ return exp;}
		public PNode getNext(){ return next;}
	}
	private PNode first;
	private PNode last;
	    public PolynomialLinkedList(){
			first = last = null;
		}
		public PolynomialLinkedList(int c, int e){
			PNode tempn = new PNode(c, e);
			first = last = tempn;
		}
		
		// Disregard --------------------------
		/*public void append(int c, int e) {
			if (first != null) {
				PNode n = new PNode(c,e,null);
				this.last.setNext(n);
				this.last = n;
			}
		} */
		//---------------------------------------------
		
		public void print(){
			if (first == null){
				System.out.println();
				return;
			}
			PNode temp = first;
			String ans = "";
			while (temp != null){
				if (temp.getCoe() > 0) {
					if (temp != first) ans = ans + " + ";
					ans = ans + temp.getCoe();
				}
				else if (temp.getCoe() < 0) ans = ans + " - " + temp.getCoe() * -1;
				if (temp.getExp() != 0){
					ans = ans + "X^" + temp.getExp();
				}
				temp = temp.getNext();
			}
			System.out.println(ans);
		}
		public PolynomialLinkedList add(PolynomialLinkedList s){
			PolynomialLinkedList sum = new PolynomialLinkedList();
			PNode nthis = this.first; // Node Iterator of this polynomial / p1.
			PNode ns = s.first; // Node Iterator of s polynomial / p2.
			while(nthis != null && ns != null) {
				// If sum is empty, we put in a new first node.
				if (sum.first == null && sum.last == null) {
					if(nthis.getExp() == ns.getExp()) {
						PNode nsum = new PNode(nthis.getCoe() + ns.getCoe(), nthis.getExp(), null);
						sum.first = sum.last = nsum;
						nthis = nthis.getNext();
						ns = ns.getNext();
					}
					else if(nthis.getExp() > ns.getExp()) {
						PNode nsum = new PNode(nthis.getCoe(), nthis.getExp(), null);
						sum.first = sum.last = nsum;
						nthis = nthis.getNext();
					}
					else if(nthis.getExp() < ns.getExp()) {
						PNode nsum = new PNode(ns.getCoe(), ns.getExp(), null);
						sum.first = sum.last = nsum;
						ns = ns.getNext();
					}
					// Extra check to ensure nthis and ns are not null.
					if (nthis != null && ns != null) {
						if(nthis.getExp() == ns.getExp()) {
							PNode nsum = new PNode(nthis.getCoe() + ns.getCoe(), nthis.getExp(), null);
							sum.last.setNext(nsum);
							sum.last = nsum;
							nthis = nthis.getNext();
							ns = ns.getNext();
						}
						else if(nthis.getExp() > ns.getExp()) {
							PNode nsum = new PNode(nthis.getCoe(), nthis.getExp(), null);
							sum.last.setNext(nsum);
							sum.last = nsum;
							nthis = nthis.getNext();
						}
						else if(nthis.getExp() < ns.getExp()) {
							PNode nsum = new PNode(ns.getCoe(), ns.getExp(), null);
							sum.last.setNext(nsum);
							sum.last = nsum;
							ns = ns.getNext();
						}
					}
				}
			}
			
			if (ns == null || nthis == null) {
				// Append terms of ns when nthis is null.
				while(ns != null) {
					PNode nsum = new PNode(ns.getCoe(), ns.getExp(), null);
					sum.last.setNext(nsum);
					sum.last = nsum;
					ns = ns.getNext();
				}
				// Append terms of nthis when ns is null.
				while (nthis != null) {
					PNode nsum = new PNode(nthis.getCoe(), nthis.getExp(), null);
					sum.last.setNext(nsum);
					sum.last = nsum;
					nthis = nthis.getNext();
				}
			} 
			return sum; 
		} 
		
		public PolynomialLinkedList multiply(PolynomialLinkedList s){ // Use nested loops
			PolynomialLinkedList product = new PolynomialLinkedList();
			
			PNode nthis = this.first; // Node iterator of this polynomial
			PNode ns = s.first; // Node iterator of parameter polynomial
			
			while(ns != null) {
				nthis = this.first;
				while(nthis != null) {
					PNode tempNode = new PNode(nthis.getCoe() * ns.getCoe(), nthis.getExp() + ns.getExp());
					// Check if product linked list is empty.
					if(product.first == null) {
						product.first = product.last = tempNode;
					}
					else {
						product.last.next = tempNode;
						product.last = tempNode;
					}
					nthis = nthis.getNext();
				}
				ns = ns.getNext();
			}
			return product;
		} 
}
