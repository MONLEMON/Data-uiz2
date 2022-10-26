package hw5;

public class Node extends BTreePrinter{

    Node left;
    Node right;
    int data;

    public Node(int data) {
        // store data
        this.data = data;
    }

    public void printTree() {
        // uncomment the following line and fix the errors
        super.printTree(this);
    }

    public void printBFT() {
        Queue q = new Queue(50);
        System.out.print("BFT node sequence [ ");
        
        q.enqueue(this);
        // do until queue is empty
        while(!q.isEmpty()){
            Node node = q.dequeue();
            System.out.print(node.data + " ");
            if(node.left != null)
                q.enqueue(node.left);
            if(node.right != null)
                q.enqueue(node.right);
        }
        
        System.out.println("]");
    }

    public void printDFT() { // PreOrder
        Stack s = new Stack(50);
        System.out.print("DFT node sequence [ ");
        
        s.push(this);
        // do until stack is empty
        while(!s.isEmpty()){
            Node node = s.pop();
            System.out.print(node.data + " ");
            if(node.right != null)
                s.push(node.right);
            if(node.left != null)
                s.push(node.left);
        }
        
        System.out.println("]");
    }
}
