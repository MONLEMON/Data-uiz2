package hw4;

public class Queue implements List{
    // Implement Queue using Linked List with tail
    Node head;
    Node tail;
    
    public void push(Node node){
        if (head == null){
            // add first node
            head = node;
            tail = node;
        }else{
            // add other node
            tail.next = node;
            tail = node;
        }
    }
    
    public void pop(){
        if (head != null){
            // check do not have 1 node ?
            if (head != tail){
                // delete first node
                head = head.next;
            }else{
                // delete last node
                head = null;
                tail = null;
            }
        }else{
            System.out.println("Error: Queue Underflow");
        }
    }
    
    public Node top(){
        return head;
    }
    
}
