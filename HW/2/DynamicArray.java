package hw2;

public class DynamicArray {
    private int[] arr;
    private int capacity;
    private int size; // Last element can be indexed at size-1
    
    public DynamicArray(int cap){ // Class Constructor
        arr = new int[cap];
        capacity = cap;
        size = 0;
    }
    
    public void pushBack(int data){
        if(size >= capacity){
            int[] temp = new int[capacity * 2];
            for(int i = 0; i < capacity; i++)   temp[i] = arr[i];
            arr = temp;
            capacity *= 2;
        }
        arr[size] = data;
        size++;
    }
    
    public int popBack(){
        if(this.isEmpty()){
            System.out.println("ERROR");
            return 0;
        }else{
            int temp = arr[size-1];
            size--;
            return temp;
        }
    }

    public int get(int i){
        if(i >= 0 && i < size){
            return arr[i];
        }else{
            System.out.println("ERROR");
            return 0;
        }
    }
    
    public void set(int i, int value){
        if(i >= 0 && i < size)  arr[i] = value;
        else                    System.out.println("ERROR");
    }
    
    public void remove(int i){
        if(i >= 0 && i < size){
            size--;
            for(int j = i; j < size; j++)   arr[j] = arr[j+1];
        }else{
            System.out.println("ERROR");
        }
    }
    
    public boolean isEmpty(){
        return size > 0 ? false : true;
    }
    
    public int getSize(){
        return size;
    }
    
    public void printStructure(){
        System.out.print("Size = " + size + ", Cap = " + capacity + ", arr = [ ");
        for(int i = 0; i < size; i++){
            System.out.print(arr[i]); 
            if(i < size-1)      System.out.print(", ");
            else                System.out.print(" ");
        }
        System.out.print("]");
        System.out.println("");
    }
}
