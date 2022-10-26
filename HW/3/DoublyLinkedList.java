package hw3;

public class DoublyLinkedList {
    Node head;
    Node tail;
    String listName;
    
    //set doublylinkedlist name
    public DoublyLinkedList(String name){
        listName = name;
    }
    
    public void popBack() {
      if (isEmpty()){
          System.out.println("ERROR");
        }else{
            // check linkedlist has 1 node ?
            if(head == tail){
                head = null;
                tail = null;
            }else{
                // move to before last node
                tail = tail.previous; 
                // delete last node
                tail.next = null;                
            }
        }
    }
    
    public void popFront(){
        if (isEmpty()){
            System.out.println("ERROR");
        }else{
            // check linkedlist has 1 node ?
            if(head == tail){
                head = null;
                tail = null;
            }else{
                // move head to second Node
                head = head.next;
                // delete first Node
                head.previous = null;                
            }
        }
    }
    
    public Node topFront(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            // return first Node
            return head;
        }
    }
    
    public Node topBack(){
        if (isEmpty()){
            System.out.println("ERROR");
            return new Node("Empty List!");
        } else {
            // return last node
            return tail;
        }
    }
    
    public void pushFront(Node node){
        if (isEmpty()){
            // add first Node
            head = node;
            tail = node;
        }else{
            node.next = head;
            head.previous = node;
            head = node;
        }
    }
    
    public void pushBack(Node node) {
        if (isEmpty()) {
            // add first node
            head = node;
            tail = node;
        } else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
    }

    public Node findNode(int id){
        if (isEmpty()){
            return new Node("Empty List!");
        } else {
            Node current = head;
            // find student has this id
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
            Node temp;
            // check is first student node ?
            if(head.student_id == id){
                // check linkedlist has 1 node ?
                if(head == tail){
                    temp = head;
                    head = null;
                    tail = null;
                    return temp;
                }else{
                    temp = head;
                    // move to second node
                    head = head.next;
                    // set first node's next to null
                    head.previous.next = null;
                    // delete first node
                    head.previous = null;
                    return temp;
                }
            // check is last student node ?
            }else if(tail.student_id == id){
                temp = tail;
                // move to before last node
                tail = tail.previous;
                // delete student node
                tail.next = null;
                return temp;
            // other case
            }else{
               Node current = head;
               // find student has this id
               while(current.next != null){
                   if(current.next.student_id == id){
                       temp = current.next;
                       // connect node between the student node (next)
                       current.next = temp.next;
                       // move to back student node
                       current = temp.next;
                       // connect node between the student node (previous)
                       current.previous = temp.previous;
                       return temp;
                   }
                   current = current.next;
               } 
            }
            return new Node("Student Not Found!");
        }
    }
    
    public void addNodeAfter(Node node1, Node node2){
        // check node1 is last node ?
        if(node1 == tail){
            // connect node1 and node2
            node1.next = node2;
            node2.previous = node1;
            // move tail to last node
            tail = node2;
        }else{
            // set node2 point to node1 and node at back node1
            node2.next = node1.next;
            node2.previous = node1;
            // set node at back node1 point to node2
            node1.next.previous = node2;
            // set node1 point to node2
            node1.next = node2;            
        }
    }
    
    public void addNodeBefore(Node node1, Node node2){
        // check node1 is first node ?
        if(node1 == head){
            // connect node1 and node2
            node2.next = node1;
            node1.previous = node2;
            // move head to first node
            head = node2;
        }else{
            // set node2 point to node1 and node at front node1
            node2.previous = node1.previous;
            node2.next = node1;
            // set node at front node1 point to node2
            node1.previous.next = node2;
            // set node1 point to node 2
            node1.previous = node2;
        }
    }
    
    public boolean isEmpty(){
        return head == null ? true : false;
    }
    
    public void merge(DoublyLinkedList list){
        // connect next pointer
        tail.next = list.head;
        // connect previous pointer
        list.head.previous = tail;
        // move tail to last
        tail = list.tail;
    }
    
    public void printStructure(){
        System.out.print(listName + ": head <-> ");
        Node current = head;
        while(current != null){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.next;
        }
        System.out.println("tail");
    }
    
    // This may be useful for you for implementing printStructure()
    public void printStructureBackward(){ 
        Node current=tail;
        System.out.print(listName + ": tail <-> ");
        while(current!=null){
            System.out.print("{" + current.student_id + "} <-> ");
            current = current.previous;
        }
        System.out.println("head");
    }
    
    public Node whoGotHighestGPA(){
        if (isEmpty()) {
            return new Node("Empty List!");
        } else {
            Node current = head;
            Node temp = null;
            double top = -1;
            // find top student
            while(current != null){
                if(current.gpa >= top){
                    top = current.gpa;
                    temp = current;
                }
                current = current.next;
            }
            return temp;
        }
    }
}
