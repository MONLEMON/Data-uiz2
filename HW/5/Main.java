package hw5;

public class Main {
    
    public static Node constructTree1(){
        Node root = new Node(3);
        
        // create left subtree
        Node subTree1 = new Node(6);
        subTree1.left = new Node(1);
        subTree1.right = new Node(8);
        
        Node subTree2 = new Node(7);
        subTree2.left = new Node(2);
        subTree2.right = subTree1;
        
        root.left = subTree2;
        
        // create right subtree
        subTree1 = new Node(9);
        subTree1.left = new Node(4);
        
        subTree2 = new Node(5);
        subTree2.right = subTree1;
        
        root.right = subTree2;

        return root;
    }
    
    public static Node constructTree2(){
        Node root = new Node(1);
        
        // create left subtree
        Node subTree1 = new Node(5);
        subTree1.left = new Node(7);
        subTree1.right = new Node(8);
        // node(8).right
        subTree1.right.right = new Node(10);
        
        Node subTree2 = new Node(2);
        subTree2.left = new Node(4);
        subTree2.right = subTree1;
        
        root.left = subTree2;
        
        // create right subtree
        subTree1 = new Node(6);
        subTree1.left = new Node(9);
        
        subTree2 = new Node(3);
        subTree2.right = subTree1;
        
        root.right = subTree2;
        
        return root;
    }
    
    public static void main(String[] args) {
        
        //Node tree = constructTree1();
        //tree.printTree();
        
        
    }
}
