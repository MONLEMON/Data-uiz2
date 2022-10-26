package hw7;

public class AVLTree extends BTreePrinter{
    Node root;

    public AVLTree(Node root){
        this.root = root;
        root.parent = null; // Clear parent of the root (Important for spliting)
    }
    
    // single rotate from left at inputted node
    public void singleRotateFromLeft(Node y) {
        if(y != null){
            Node w = y.parent;
            Node x = y.left;            
            if(x != null){
                // move right child (inner subtree) of x to y
                y.left = x.right;
                if(y.left != null){
                    y.left.parent = y;
                }
                // move x up to parent and y down to child
                x.right = y;
                y.parent = x;
                // node y was root node
                if(w == null){
                    root = x;
                    x.parent = null;
                } else {
                    // node y was left child of node w
                    if(y.key < w.key){
                        w.left = x;
                        x.parent = w;
                    // node y was right child of node w
                    } else {
                        w.right = x;
                        x.parent = w;
                    }
                }
            }            
        }
    }

    // single rotate from right at inputted node
    public void singleRotateFromRight(Node y) {
        if(y != null){
            Node w = y.parent;
            Node x = y.right;
            if(x != null){
                // move left child (inner subtree) of x to y
                y.right = x.left;
                if(y.right != null){
                    y.right.parent = y;
                }
                // move x up to parent and y down to child
                x.left = y;
                y.parent = x;
                // node y was root node
                if(w == null){
                    root = x;
                    x.parent = null;
                } else {
                    // node y was left child of node w
                    if(y.key < w.key){
                        w.left = x;
                        x.parent = w;
                        // node y was right child of node w
                    } else {
                        w.right = x;
                        x.parent = w;
                    }
                }
            }
        }
    }

    // double rotate from left at inputted node
    public void doubleRotateFromLeft(Node y) {
        if(y != null){
            Node w = y.parent;
            Node x = y.left;
            // if not has node x and y do nothing
            if(x != null){
                Node z = x.right;
                if(z != null){
                    // move left child (inner subtree) of z to x
                    x.right = z.left;
                    if(x.right != null){
                        x.right.parent = x;
                    }
                    // move right child (inner subtree) of z to y
                    y.left = z.right;
                    if(y.left != null){
                        y.left.parent = y;
                    }
                    // move node z up to parent of node x and y
                    z.left = x;
                    x.parent = z;
                    z.right = y;
                    y.parent = z;
                    // node y was root node
                    if(w == null){
                        root = z;
                        z.parent = null;
                    } else {
                        // node y was left child of node w
                        if(y.key < w.key){
                            w.left = z;
                            z.parent = w;
                        // node y was right child of node w
                        } else {
                            w.right = z;
                            z.parent = w;
                        }
                    }
                }
            } 
        }
    }

    // double rotate from right at inputted node
    public void doubleRotateFromRight(Node y) {
        if(y != null){
            Node w = y.parent;
            Node x = y.right;
            // if not has node x and y do nothing
            if(x != null){
                Node z = x.left;
                if(z != null){
                    // move left child (inner subtree) of z to y
                    y.right = z.left;
                    if(y.right != null){
                        y.right.parent = y;
                    }
                    // move right child (inner subtree) of z to x
                    x.left = z.right;
                    if(x.left != null){
                        x.left.parent = x;
                    }
                    // move node z up to parent of node y and x
                    z.left = y;
                    y.parent = z;
                    z.right = x;
                    x.parent = z;
                    // node y was root node
                    if(w == null){
                        root = z;
                        z.parent = null;
                    } else {
                        // node y was left child of node w
                        if(y.key < w.key){
                            w.left = z;
                            z.parent = w;
                        // node y was right child of node w
                        } else {
                            w.right = z;
                            z.parent = w;
                        }
                    }
                }
            }  
        }
    }

    // rebalance inputted node
    public static void rebalance(AVLTree tree, Node node){
        if(node != null){
            // Calculate balanceFactor
            int balanceFactor = height(node.left) - height(node.right);              
            // check : is unbalanced?
            if (Math.abs(balanceFactor) > 1){
                // check : is left heavy?
                if (balanceFactor > 0){
                    // check : is Outer node?
                    if (height(node.left.left) > height(node.left.right)){                  
                        System.out.println("Perform SingleRotationFromLeft(Node " + node.key +")");
                        tree.singleRotateFromLeft(node);
                    // check : is Inner node?
                    }else{
                        System.out.println("Perform DoubleRotationFromLeft(Node " + node.key +")");
                        tree.doubleRotateFromLeft(node);
                    }
                // check : is right heavy?
                }else{  
                    // check : is Inner node?
                    if (height(node.right.left) > height(node.right.right)){                  
                        System.out.println("Perform DoubleRotationFromRight(Node " + node.key +")");
                        tree.doubleRotateFromRight(node);
                    // check : is Outer node?
                    }else{
                        System.out.println("Perform SingleRotationFromRight(Node " + node.key +")");
                        tree.singleRotateFromRight(node);
                    }
                }
            }
        }
    }  
    
    // add new node with the key on inputted tree
    public static void insert(AVLTree tree, Node node, int key) {
        if (key == node.key) {
            System.out.println("Duplicated key:" + key);
        }else if (key < node.key) {//Go left
            if (node.left == null) {
                node.left = new Node(key);
                node.left.parent = node;
                
                // rebalance up to parent
                Node w = node;
                while(w != null){
                    w = w.parent;
                    rebalance(tree, w);
                }
                
            }else {
                insert(tree, node.left, key);
            }
        }else{  // Go right
            if (node.right == null) {
                node.right = new Node(key);
                node.right.parent = node;
                
                // rebalance up to parent
                Node w = node;
                while(w != null){
                    rebalance(tree, w);
                    w = w.parent;
                }
                
            }else {
                insert(tree, node.right, key);
            }
        }
    }
    
    // This function is complete, no need to edit
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            insert(this, root, key);
        }
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
                // rebalance root node one time
                rebalance(this, root);
            // case: delete root that has right child
            } else if(root.left == null && root.right != null){
                root.right.parent = null;
                root = root.right;
                // rebalance root node one time
                rebalance(this, root);
            // case: delete root that has both child
            } else {
                Node MR = findMin(root.right);
                root.key = MR.key;
                delete(this, MR);
            }
        // case: find the key on tree ,but it was non-root node
        } else {
            delete(this, n);
        }
    }
    
    // delete inputted node on this tree
    public static void delete(AVLTree tree, Node node){
        Node w = node.parent;
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
            node.key = MR.key;
            delete(tree, MR);
        }
        
        // rebalance up to parent
        while(w != null){
            rebalance(tree, w);
            w = w.parent;
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
    
    // check : inputted trees can merge ?
    public static boolean isMergeable(Node r1, Node r2){
        if(r1 != null && r2 != null){
            // check : all keys in r1 < r2 
            return (findMax(r1).key < findMin(r2).key)? true : false;
        } else {
            // if r1 ro r2 was null return true
            return true;
        }
    }

    // merge inputted trees with inputted node
    public static Node mergeWithRoot(Node r1, Node r2, Node t){
        if (isMergeable(r1, r2)) {
            if(Math.abs(height(r1) - height(r2)) <= 1){
                // merge r1 and r2 with node t
                t.left = r1;
                t.right = r2;
                if(r1 != null){
                    r1.parent = t;
                }
                if(r2 != null){
                    r2.parent = t;
                }            
                return t;
                
            } else if(height(r1) > height(r2)) {
                // merge with right subtree of r1
                Node x = mergeWithRoot(r1.right, r2, t);
                // connect merged subtree
                r1.right = x;
                x.parent = r1;
                // for use rebalance()
                AVLTree tree = new AVLTree(r1);
                //tree.root = r1;
                rebalance(tree, tree.root);
                return tree.root;   // r1
                
            } else {
                // merge with left subtree of r2
                Node x = mergeWithRoot(r1, r2.left, t);
                // connect merged subtree
                r2.left = x;
                x.parent = r2;
                // for use rebalance()
                AVLTree tree = new AVLTree(r2);
                //tree.root = r2;
                rebalance(tree, tree.root);
                return tree.root;   // r2
            }

        } else {
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
            return null;
        }
    }
    
    // merge inputted tree with this tree
    public void merge(AVLTree tree2){
        if (isMergeable(this.root, tree2.root)){
            // find root node
            Node t = findMax(this.root);
            delete(t.key);
            // merge
            this.root = mergeWithRoot(this.root, tree2.root, t);
        }else{
            System.out.println("All nodes in T1 must be smaller than all nodes from T2");
        }
    }
    
    // split this tree with inputted key
    public Node[] split(int key){
        return split(this.root, key);
    }
    
    // split inputted tree with inputted key
    public static Node[] split(Node r, int key){ 
        Node[] arr = new Node[2];
        arr[0] = null;
        arr[1] = null;
        
        // check : empty tree or empty subtree ?
        if(r == null){
            return arr;
        } else if(key < r.key){
            // split on left subtree
            arr = split(r.left, key);
            Node r3 = mergeWithRoot(arr[1], r.right, r);
            arr[1] = r3;
            //arr[1] = mergeWithRoot(arr[1], r.right, r);
            return arr;
        } else {
            // split on right subtree
            arr = split(r.right, key);
            Node r4 = mergeWithRoot(r.left, arr[0], r); 
            arr[0] = r4;
            //arr[0] = mergeWithRoot(r.left, arr[0], r);                          
            return arr;
        }
    }
    
    // Use this function to check the node height
    // This function is complete, no need to edit
    public static int height(Node node){
        if (node == null)
            return -1;
        else
            return 1 + Math.max(height(node.left), height(node.right));
    }
    
    // This function is complete, no need to edit
    public void printTree() {
        if (root == null) {
            System.out.println("Empty tree!!!");
        } else {
            super.printTree(root);
        }
    }
    
    public AVLTree() {} // Dummy Constructor, no need to edit
}