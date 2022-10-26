package hw7;

public class BSTree2 extends BTreePrinter{
    Node root;
    
    // find the node that has key macth search key on this tree
    public Node find(int search_key){
        Node current = root;
        while(current != null){
            // found
            if(search_key == current.key){
                return current;
            // find on left child
            } else if(search_key < current.key){
                current = current.left;
            // find on left child
            } else {
                current = current.right;
            }
        }
        // case : node found
        //System.out.println("Key not found!!!");
        return null;
    }

    // find the node that has smallest key on this tree
    public Node findMin(){
        // case : empty tree
        if(root == null){
            return null;
        } else {
            Node current = root;
            while(current.left != null){
                current = current.left;
            }
            return current;
        }
    }

    // find the node that has most key on this tree
    public Node findMax(){
        // case : empty tree
        if(root == null){
            return null;
        } else {
            Node current = root;
            while(current.right != null){
                current = current.right;
            }
            return current;         
        }
    }
    
    // add new node with the key on this tree
    public void insert(int key) {
        // case : empty tree
        if(root == null){
            root = new Node(key);
            return;
        }
        
        Node current = root;
        while(current != null){
            // case : Duplicated key
            if(key == current.key){
                System.out.println("Duplicated key:" + key);
                return;
            // go left
            } else if(key < current.key){
                if(current.left == null){
                    current.left = new Node(key);
                    current.left.parent = current;
                    return;
                } else {
                    current = current.left;
                }
            // go right
            } else {
                if(current.right == null){
                    current.right = new Node(key);
                    current.right.parent = current;
                    return;
                } else {
                    current = current.right;
                }
            }
        }
    }
    
    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }
}