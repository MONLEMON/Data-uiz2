package hw5;

public class Queue {
    Node[] arr; // circular Queue
    int capacity;
    int front;
    int back;
    int size;
    
    public Queue(int cap){
        capacity = cap;
        size = 0;
        front = 0;
        back = 0;
        arr = new Node[cap];

    }
    
    public void enqueue(Node node){
        if (!isFull()){
            // same pushBack()
            arr[back] = node;
            back = (back + 1) % capacity;
            size++;
        }else{
            System.out.println("Queue Overflow!!!");
        }
    }
    
    public Node dequeue(){
        Node temp = null;
        if (!isEmpty()){
            // same popFront()
            temp = arr[front];
            front = (front + 1) % capacity;
            size--;
        }else{
            System.out.println("Queue Underflow!!!");
        }
        return temp;
    }
    
    public boolean isEmpty(){
        return size == 0 ? true : false;
    }
    
    public boolean isFull(){
        return size == capacity ? true : false;
    }
    
    public void printCircularIndices(){
        System.out.println("Front index = " + front + " Back index = " + back);
    }
    
    public void printQueue(){
        if (!isEmpty()){
            System.out.print("[Front] ");
            
            // print the front to before the back
            for(int i=0; i<size; i++){
                int current = front + i;
                System.out.print(arr[current].data + " ");
            }
            
            System.out.println("[Back]");
        }else{
            System.out.println("Empty Queue!!!");
        }
    }
}
