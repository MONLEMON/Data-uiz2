package hw5;

public class Stack {
    Node[] arr; // regular array
    int capacity;
    int size;

    public Stack(int cap){
        capacity = cap;
        size = 0;
        arr = new Node[cap];
    }
    
    public void push(Node node){
        if (!isFull()){
            // same pushBack()
            arr[size] = node;
            size++;
        }else{
            System.out.println("Stack Overflow!!!");
        }
    }
    
    public Node pop(){
        Node temp = null;
        if (!isEmpty()){
            // same popBack()
            temp = arr[size - 1];
            size--;
        }else{
            System.out.println("Stack Underflow!!!");
        }
        return temp;
    }
    
    public boolean isFull(){
        return size == capacity ? true : false;
    }
    
    public boolean isEmpty(){
        return size == 0 ? true : false;
    }
    
    public void printStack(){
        if (!isEmpty()) {
            System.out.print("[Bottom] ");
            
            // print the button to the top
            for(int i=0; i<size; i++){
                System.out.print(arr[i].data + " ");
            }
            
            System.out.println("[Top]");
        } else {
            System.out.println("Empty Stack!!!");
        }
    }
}
