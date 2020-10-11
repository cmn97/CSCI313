import java.util.*;
public class A2TreeHC{
   public static void main(String argc[]){
	  ExpressionTree et = new ExpressionTree();

      System.out.println("Enter an algebraic expression: ");
      Scanner s = new Scanner(System.in);
      String alg =  s.nextLine();
      s.close();
    
      et = new ExpressionTree(alg);
      et.print();
      et = new ExpressionTree("3+4*5");
      et.print(); //print (3+(4*5))
      et = new ExpressionTree("((1-(2+3))+(4*5))");
      et.print(); //print above
      et = new ExpressionTree("(3(4+5))");
      et.print(); //no output - missing operator
      et = new ExpressionTree("(4+5)*3");
      et.print(); //print ((4+5)*3)
      et = new ExpressionTree("10*((4+5)*2+1)/6");
      et.print(); //print "((10*(((4+5)*2)+1))/6)"
      et = new ExpressionTree("((4+5)*3))");
      et.print(); //no output - parenthesis not match
      et = new ExpressionTree("34+5");
      et.print(); //no output - single value only
      
      Tree t1 = new Tree();
      Tree t2 = new Tree();
      t1.makeTree();
      t1.levelOrder();	//0 1 2 3 4 5 6 13 14 15 7 8 9 10 11 12
      t2.makeTree2();
      t2.levelOrder();	//0 1 2 3
      System.out.println("sub tree t1 and t1 " + t1.isSubTree(t1));
      System.out.println("sub tree t1 and t2 " + t1.isSubTree(t2)); //t2 is not a subTree of t1
      Tree t3 = new Tree();
      t3.makeTree3();
      t3.levelOrder();
      System.out.println("sub tree t1 and t3 " + t1.isSubTree(t3)); //t3 is a subTree of t1
      Tree t4 = new Tree();
      t4.makeTree4();
      t4.levelOrder();
      System.out.println("sub tree t1 and t4 " + t1.isSubTree(t4)); //t4 is a subTree of t1

   }
}
      
class ExpressionTree{
   private static class BTNode{
      char value;
      BTNode parent, left, right;
      public BTNode(char e){
        this(e, null, null, null);
      }
      public BTNode(char e, BTNode p, BTNode l, BTNode r){
		value = e;
		parent = p;
		left = l;
		right = r;
      }
   }
   BTNode root;
   public ExpressionTree(){
      root = null;
      /*implement*/
   }
   public boolean isEmpty(){
      return root == null;
   }
  
   public void print(){
      /*implement*/
   }

}
class Tree{
   private static class TNode{
      private int value;
      private TNode parent;
      private List<TNode> children;
      public TNode(){
         this(0, null);
      }
      public TNode(int e){
         this(e, null);
      }
      public TNode(int e, TNode p){
         value = e;
         parent = p;
         children = new ArrayList<TNode>();
      }
   }
   private TNode root;
   private int size;
   Tree(){
      root = null;
      size = 0;
   }
    public TNode createNode(int e, TNode p){
       return new TNode(e, p);
    }
    public TNode addChild(TNode n, int e){
       TNode temp = createNode(e, n);
       n.children.add(temp);
       size++;
       return temp;
    }
    public void makeTree(){
       root = createNode(0, null);
       size++;
       buildTree(root, 3);
    }
    public void makeTree2(){
       root = createNode(0, null); 
       size++; 
       buildTree(root, 1);
    }
    public void makeTree3(){
       root = createNode(3, null); 
       size++; 
    }
    public void makeTree4(){
       root = createNode(2, null); 
       size += 13; 
       buildTree(root, 1);
       size -= 12;
    }
    private void buildTree(TNode n, int i){
       if (i <= 0) return;
       TNode fc = addChild(n, size);
       TNode sc = addChild(n, size);
       TNode tc = addChild(n, size);
       buildTree(fc, i - 1);
       buildTree(sc, i - 2);
       if (i % 2 == 0)
          buildTree(tc, i - 1);
   }
}
