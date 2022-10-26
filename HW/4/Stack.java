package hw4;

public class Stack implements List{
    // Implement Stack using Linked List without tail
    Node head;
    
    public void push(Node node){
        if (head == null){
            // add first node
            head = node;
        }else{
            // add other node
            node.next = head;
            head = node;
        }
    }
    
    public void pop(){
        // check is not empty ?
        if (head != null){
            // delete first node
            head = head.next;
        }else{
            System.out.println("Error: Stack Underflow");
        }
    }
    
    public Node top(){
        return head;
    }
    
}
