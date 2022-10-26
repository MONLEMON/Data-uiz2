package hw8;

public class Heap {
    
    int capacity;
    Node[] arr;
    int size;
    
    boolean isMinHeap;  // true if minHeap, false if maxHeap
    
    int timer;    // For each push, the timer will increase by 1
    
    // create Heap follow inputted type and capacity
    public Heap(boolean isMinHeap, int cap){
        this.isMinHeap = isMinHeap;
        timer = 0;
        // start from index 1
        arr = new Node[cap + 1];
        capacity = cap;
        size = 0;
    }
    
    // return front node (index 1)
    public Node top(){
        return (size == 0) ? null : arr[1];
    }
    
    // add new node to Heap
    public void push(Node node){
        
        timer++;
        node.timestamp = timer; // Stamp the time number to the node
        
        // check : Heap is full ?
        if(size < capacity){
            arr[size + 1] = node;
            int k = size + 1;
            
            while(k > 1){
                 // find parent
                 int p = (int) Math.floor(k/2);
                 if(arr[k].compare(arr[p])){
                     // percolate up and update k
                     swap(k, p);
                     k = p;
                 } else {
                     break;
                 }
            }
           size++;
        }
    }
    
    // pop front node (index 1) out from Heap
    public Node pop(){
        Node temp = null;
        
        // check : Heap is empty ?
        if(size != 0){
            temp = arr[1];
            // move last Node to root (front)
            arr[1] = arr[size];
            arr[size] = null;
            size--;

            int k = 1;
            while(k  <= size){
                // find left and right child
                int left = 2*k;
                int right = 2*k + 1;
                
                // check : is last Node ?
                if(arr[left] == null && arr[right] == null){
                    break;
                    
                // check : has only right child ?
                } else if(arr[left] == null){
                    // check : priority of k more then right ?
                    if(arr[k].compare(arr[right])){
                        break;
                    } else {
                        // percolate down
                        swap(k, right);
                        k = right;
                    }
                    
                // check : has only left child ?
                } else if(arr[right] == null){
                    // check : priority of k more then left ?
                    if(arr[k].compare(arr[left])){
                        break;
                    } else {
                        // percolate down
                        swap(k, left);
                        k = left;
                    }
                    
                // check : has both child ?
                } else {
                    
                    if(arr[left].compare(arr[right])){
                        // check : priority of k more then left ?
                        if(arr[k].compare(arr[left])){
                            break;
                        } else {
                            // percolate down
                            swap(k, left);
                            k = left;                           
                        }
                    } else {
                        // check : priority of k more then right ?
                        if(arr[k].compare(arr[right])){
                            break;
                        } else {
                            // percolate down
                            swap(k, right);
                            k = right;                            
                        }
                    }
                }
            }            
        }
        
        return temp;
    }

    // This is an optional function, you may use it if you know what it is
    // This function is complete, no need to edit
    public void swap(int index1, int index2){
        Node temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}
