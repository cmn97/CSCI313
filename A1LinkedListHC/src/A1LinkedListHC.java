/**
 *  * Student:
 *   */

// TODO: add function results in endless loop
// add function adding random terms to the end
// maybe something regarding checking if the iterators ns and nthis are null or not
public class A1LinkedListHC{
   	public static void main(String argc[]){
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
	public E midElement(){
		//implement this method
		return null; 
	}
	public boolean detectLoop(){
		//implement this method
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
		public PolynomialLinkedList add(PolynomialLinkedList s){ // Must be in linear time
			
			PolynomialLinkedList sum = new PolynomialLinkedList();
			PNode nthis = this.first;
			PNode ns = s.first;
			
		while(nthis != null && ns != null) {
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
					//System.out.println("Appended term is: " + sum.last.coe + "x^" + sum.last.exp);
				}
				else if(nthis.getExp() < ns.getExp()) {
					PNode nsum = new PNode(ns.getCoe(), ns.getExp(), null);
					sum.first = sum.last = nsum;
					ns = ns.getNext();
				}
				
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
						//System.out.println("Appended term is: " + sum.last.coe + "x^" + sum.last.exp);
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
			while(ns != null) {
				PNode nsum = new PNode(ns.getCoe(), ns.getExp(), null);
				sum.last.setNext(nsum);
				sum.last = nsum;
				ns = ns.getNext();
			}
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
			PolynomialLinkedList productTemp = new PolynomialLinkedList();
			
			PNode nthis = this.first;
			PNode ns = s.first;
			
			while(ns != null) {
				productTemp.first = null;
				productTemp.last = null;
				nthis = this.first;
				while(nthis != null) {
					PNode tempNode = new PNode(nthis.getCoe() * ns.getCoe(), nthis.getExp() + ns.getExp());
					if(product.first == null) {
						product.first = product.last = tempNode;
					}
					else {
						product.last.next = tempNode;
						product.last = tempNode;
					}
					nthis = nthis.getNext();
				}
				//product = product.add(productTemp);
				ns = ns.getNext();
			}
			return product;
		} 
}
