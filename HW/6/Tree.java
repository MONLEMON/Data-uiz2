
public class Tree extends BTreePrinter{ // inherit BTreePrinter
    Node root;
    
    // create tree with root node
    public Tree(Node root){
        this.root = root;
    }
    
    public Tree(){} // Dummy constructor
    
    // print this tree on console
    public void printTree(){ 
        if(root == null){
            System.out.println("Empty tree!!!");    
        } else {
            // call function in BTreePrinter class
            super.printTree(root);    
        }        
    }

    // print the inputted node on console
    public static void printNode(Node node){
        if(node != null){
            System.out.println(node.key);
        }    
    }
    
    // find the node that has key macth search key on this tree
    public Node find(int search_key){
        return find(root, search_key);
    }
    
    // find the node that has key macth search key on inputted tree
    public static Node find(Node node, int search_key){
        if(node == null){
            System.out.println("Key not found!!!");
            return null;
        } else if(search_key == node.key){
            // found
            return node;
        } else if(search_key > node.key){
            // find on right
            return find(node.right, search_key);
        } else {// (search_key < node.key)
            // find on left
            return find(node.left, search_key);
        }
    }
    
    // find the node that has smallest key on this tree
    public Node findMin(){
        return findMin(root);
    }
    
    // find the node that has smallest key on inputted tree
    public static Node findMin(Node node){
        // case: empty tree
        if(node == null){
            return null;
        // case: can not go left    
        } else if(node.left == null){
            return node;
        // case: can go left (node.left != null)
        } else return findMin(node.left);
    }
    
    // find the node that has most key on this tree
    public Node findMax(){
        return findMax(root);
    }
    
    // find the node that has most key on inputted tree
    public static Node findMax(Node node){
        // case: empty tree
        if(node == null){
            return null;
        // case: can not go right    
        } else if(node.right == null){
            return node;
        // case: can go right (node.right != null)
        } else return findMax(node.right);
    }
    
    // find left node on this tree that has key closest search key
    public Node findClosestLeaf(int search_key){
        return findClosestLeaf(root, search_key);
    }
    
    // find left node on inputted tree that has key closest search key
    public static Node findClosestLeaf(Node node, int search_key){
        // case: empty tree
        if(node == null){
            return null;
        // case: find on left
        } else if(search_key < node.key){
            // case: can find on left 
            if(node.left != null){
                return findClosestLeaf(node.left, search_key);
            // case: can not find on left 
            } else {
                return node;
            }
        // case: find on right
        } else {
            // case: can find on right
            if(node.right != null){
                return findClosestLeaf(node.right, search_key);
            // case: can not find on right
            } else {
                return node;
            }
        }
    }
    
    // find the node that has key closest search key on this tree
    public Node findClosest(int search_key){
        Node current, closest;
        closest = current = root;
        int min_diff = Integer.MAX_VALUE;
        int diff;
        
        while(current != null){
            diff = Math.abs(current.key - search_key);
            // if new diff less then old diff keep this node and update diff
            if(diff < min_diff){
                closest = current;
                min_diff = diff;
            }
            // find on left
            if(search_key < current.key){
                current = current.left;
            // find on right (search_key > current.key)
            } else current = current.right;
        }
        return closest;
    }
    
    // add new node with the key on this tree
    public void insert(int key) {
        Node r = findClosestLeaf(key);
        // case: empty tree
        if(r == null){
            root = new Node(key);
        // case: non-empty tree
        } else {
            // case: have this key on tree
            if(key == r.key){
               System.out.println("Duplicated key!!!");
               return;
            }
            // case: added on left
            if(key < r.key){
               r.left = new Node(key);
            }
            // case: added on right
            if(key > r.key){
               r.right = new Node(key);
            }       
        }
    }
    
    // print this tree follow Preorder DFT method
    public void printPreOrderDFT(){
        System.out.print("PreOrder DFT node sequence [ ");
        printPreOrderDFT(root);
        System.out.println("]");
    }
    
    // print inputted tree follow Preorder DFT method
    public static void printPreOrderDFT(Node node){
        // case: empty tree or empty sub tree 
        if(node == null){
            return;
        // case: non-empty
        } else {
            System.out.print(node.key + " ");
            printPreOrderDFT(node.left);
            printPreOrderDFT(node.right);
        }
    }
    
    // print this tree follow Inorder DFT method
    public void printInOrderDFT(){
        System.out.print("InOrder DFT node sequence [ ");
        printInOrderDFT(root);
        System.out.println("]");
    }
    
    // print inputted tree follow Inorder DFT method
    public static void printInOrderDFT(Node node){
        // case: empty tree or empty sub tree   
        if(node == null){
            return;
        // case: non-empty
        } else {
            printInOrderDFT(node.left);
            System.out.print(node.key + " ");
            printInOrderDFT(node.right);
        }
    }
    
    // print this tree follow Postorder DFT method
    public void printPostOrderDFT(){
        System.out.print("PostOrder DFT node sequence [ ");
        printPostOrderDFT(root);
        System.out.println("]");
    }
    
    // print inputted tree follow Postorder DFT method
    public static void printPostOrderDFT(Node node){
        // case: empty tree or empty sub tree   
        if(node == null){
            return;
        // case: non-empty
        } else {
            printPostOrderDFT(node.left);
            printPostOrderDFT(node.right);
            System.out.print(node.key + " ");
        }
    }
    
    // find height of inputted node
    public static int height(Node node){
        // case: empty node
        if(node == null){
            return -1;
        // case: non-empty node
        } else {
            // height of left child and right child
            int HL = height(node.left);
            int HR = height(node.right);
            // same 1 + max(HL, HR)
            return 1 + ((HL >= HR)? HL : HR);
        }
    }
    
    // find size of inputted tree
    public static int size(Node node){
        // case: empty tree
        if(node == null){
            return 0;
        // case: non-empty tree
        } else {
            // same: root + size left sub + size right sub
            return 1 + size(node.left) + size(node.right);
        }
    }
    
    // find depth of inputted node
    public static int depth(Node root, Node node){  // do not use root
        // case: empty node
        if(node == null){
            return -1;
        // case: root node
        } else if(node.parent == null){
            return 0;
        // case: non-empty node
        } else {
            return 1 + depth(root, node.parent);
        }
    }
    
    // find height of this tree
    public int height(){
        return height(root);
    }
    
    // find size of this tree
    public int size(){
        return size(root);
    }
    
    // find depth of this tree (equal height)
    public int depth(){
        return height(root);
    }
    
    // find node on this tree that has kth smallest key
    public Node findKthSmallest(int k){
        return findKthSmallest(root, k);
    }
    
    // find node on inputted tree that has kth smallest key
    public static Node findKthSmallest(Node node, int k){
        // case: non-empty tree
        if(node != null){
            int l = size(node.left);
            // is this node ?
            if(k == l+1){
                return node;
            }
            if(k < l+1){
                // find on left
                return findKthSmallest(node.left, k);
            }
            if(k > l+1){
                // find on right with new k
                return findKthSmallest(node.right, k-l-1);
            }             
        }
        
        // case: empty tree
        return null;
    }
    
    // find node on tree that has key more than inputted node 1 step
    public static Node findNext(Node node){
        // case: empty node
        if(node == null){
            return null;
        // case: non-empty node
        } else {
            // case: has right sub tree (right child)
            if(node.right != null){
                return leftDescendant(node.right);
            // case: not has right sub tree
            } else {
                return rightAncestor(node);
            }            
        }
    }
    
    // same findMin(node)
    public static Node leftDescendant(Node node){// Case 1 (findMin)
        // case: can not go left    
        if(node.left == null){
            return node;
        // case: can go left        
        } else {
            return leftDescendant(node.left);
        }
    }
    
    // find first right parent of inputted node
    public static Node rightAncestor(Node node) {// Case 2 (first right parent)
        // case: this node is root (last parent)
        if(node.parent == null){
            return null;
        // case: this node is first right parent
        } else if(node.key < node.parent.key){
            return node.parent;
        // case: is not first right parent, find up
        } else {
            return rightAncestor(node.parent);
        }
    }
    
    // find node on this tree that has key between x and y
    public List rangeSearch(int x, int y){
        List l = new List(100);
        Node n = findClosest(x);
        
        while(n.key <= y){
            // if node in range add to list
            if(n.key >= x){
                l.append(n);
            }
            n = findNext(n);
            // if can not find next node, stop find
            if(n == null){
                break;
            }
        }
        return l;
    }
    
    // delete node on this tree that has key macth inputted key
    public void delete(int key) {
        // case: delete empty tree
        if(root == null){
            System.out.println("Empty Tree!!!");
            return;
        }
        
        Node n = find(key);
        // case: not find the key on tree
        if(n == null){
            return;
        // case: find the key on tree and it was root node
        } else if(n == root){
            // case: delete root that has not child
            if(root.left == null && root.right == null){
                root = null;
            // case: delete root that has left child
            } else if(root.left != null && root.right == null){
                root.left.parent = null;
                root = root.left;
            // case: delete root that has right child
            } else if(root.left == null && root.right != null){
                root.right.parent = null;
                root = root.right;
            // case: delete root that has both child
            } else {
                Node MR = findMin(root.right);
                delete(MR);
                root.key = MR.key;
            }
        // case: find the key on tree ,but it was non-root node
        } else {
            delete(n);
        }
    }

    // delete inputted node on this tree
    public static void delete(Node node){
        // case: delete node that has not child
        if(node.left == null && node.right == null){
            // case: the node was left child
            if(node.key < node.parent.key){
                node.parent.left = null;
            // case: the node was right child
            } else {
                node.parent.right = null;
            }
        // case: delete node that has left child
        } else if(node.left != null && node.right == null){
            // case: the node was left child
            if(node.key < node.parent.key){
                node.parent.left = node.left;
                node.left.parent = node.parent;
            // case: the node was right child
            } else {
                node.parent.right = node.left;
                node.left.parent = node.parent;
            }
        // case: delete node that has right child
        } else if(node.left == null && node.right != null){
            // case: the node was left child
            if(node.key < node.parent.key){
                node.parent.left = node.right;
                node.right.parent = node.parent;
            // case: the node was right child
            } else {
                node.parent.right = node.right;
                node.right.parent = node.parent;
            }
        // case: delete node that has both child
        } else {
            Node MR = findMin(node.right);
            delete(MR);
            node.key = MR.key;
        }
    }
}
