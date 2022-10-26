package hw3;

public class SinglyLinkedList {
    Node head;
    String listName;
    
    // set linkedlist name
    public SinglyLinkedList(String name){
        listName = name;
    }
    
    public void popBack() {
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            // check linkedlist has 1 node ?
            if(head.next == null){
                head = null;
            }else{
                Node current = head;
                // move to before the last Node
                while(current.next.next != null)    current = current.next;
                current.next = null;                
            }
            
        }
    }
    
    public void popFront(){
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            // check linkedlist has 1 node ?    
            if(head.next == null){
                head = null;
            }else{
                // move head to second Node
                head = head.next;                
            }
        }
    }
    
    public Node topFront(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");     
        } else {
            //return first Node
            return head;                        
        }
    }
    
    public Node topBack(){ 
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            Node current = head;
            // move to last Node
            while(current.next != null)     current = current.next;
            return current;
        }
    }
    
    public void pushFront(Node node){
        if (isEmpty()){
            // add first Node
            head = node;
        }else{
            node.next = head;
            head = node;
        }
    }
    
    public void pushBack(Node node) {
        if (isEmpty()){
            // add first Node
            head = node;
        } else {
            Node current = head;
            // move to last Node
            while(current.next != null)     current = current.next;
            // add new Node
            current.next = node;
        }
    }

    public Node findNode(int id){
        if (isEmpty()){
            return new Node("Empty List!");
        } else {
            Node current = head;
            // find the student who has this id
            while(current != null){
                if(current.student_id == id)    return current;
                current = current.next;
            }
            return new Node("Student Not Found!");
        }        
    }
    
    public Node eraseNode(int id){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            // this id is first Node ?
            if(head.student_id == id){
                Node temp = head;
                head = head.next;
                return temp;
            }else{
                Node current = head;
                // find the student who has this id
                while(current.next != null){
                    if(current.next.student_id == id){
                        Node temp = current.next;
                        // delete the student Node
                        current.next = current.next.next;
                        return temp;
                    }
                    current = current.next;
                }
                
                return new Node("Student Not Found!");                
            }
        }
    }
    
    public void addNodeAfter(Node node1, Node node2){
        // set node2 point to back node1
        node2.next = node1.next;
        // set node1 point to node2
        node1.next = node2; 
    }
    
    public void addNodeBefore(Node node1, Node node2){
        // check node1 is first Node ?
        if(node1 == head){
            node2.next = node1;
            head = node2;
        }else{
            Node current = head;
            // find node that before node1
            while(current != null){
                if(current.next == node1){
                    // set node2 point to node1
                    node2.next = node1;
                    // set the before node1 point to node2
                    current.next = node2;
                    break;
                }
                current = current.next;
            }            
        }
    }
    
    // check linkedlist is empty ?
    public boolean isEmpty(){
        return head == null ? true : false;
    }
    
    public void merge(SinglyLinkedList list){
        Node current = head;
        // move to last Node
        while(current.next != null)     current = current.next;
        // merge 
        current.next = list.head;
    }
    
    public void printStructure(){
        System.out.print(listName + ": head -> ");
        Node current = head;
        while(current != null){
            System.out.print("{" + current.student_id + "} -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    public Node whoGotHighestGPA(){
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node current = head;
            Node topStudent = current;
            // find the top student
            while(current != null){
                if(current.gpa >= topStudent.gpa){
                    topStudent = current;
                }
                current = current.next;
            }
            return topStudent;
        }
    }
}
