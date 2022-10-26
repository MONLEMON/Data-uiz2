package hw8;

// This node contains information about an investor (investorID) who submits an order (sell order = minHeap, buy order = maxHeap)
// at a certain time (timestamp) and a certain price (price)
public class Node {
    
    double price;
    int investorID;
    int amount;
    int timestamp;
    boolean isMinHeap;
    
    public Node(double price, int investorID, int amount, boolean isMinHeap){
        this.price = price;
        this.investorID = investorID;
        this.amount = amount;
        this.isMinHeap = isMinHeap;
    } 
    
    // compare : Priority(thisNode) > Priority(rightNode) ?
    public boolean compare(Node rightNode){
        // If same price, check timestamp
        if (this.price == rightNode.price) {
            return (this.timestamp < rightNode.timestamp) ? true : false;
        }else{
            // minHeap : lower price higher priority
            if (isMinHeap){
                return (this.price < rightNode.price) ? true : false;
            // maxHeap : high price higher priority
            }else{
                return (this.price > rightNode.price) ? true : false;
            }
        }
    }
    
    public Node(){} // Dummy constructor, no need to edit
}
