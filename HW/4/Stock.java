package hw4;

public class Stock {
    private List list;
    private int totalShares;
    
    public Stock(String costBasis){
        switch (costBasis) {
            case "FIFO":
                list = new Queue();
                break;
            case "LIFO":
                list = new Stack();
                break;
            default:
                System.out.println("Invalid cost basis. Choose FIFO or LIFO");
                break;
        }
    }
    
    public void buy(int boughtShares, double boughtPrice){
        // add bought shares and update total shares
        list.push(new Node(boughtShares, boughtPrice));
        totalShares += boughtShares;
    }
    
    public void sell(int soldShares, double soldPrice){
        if (soldShares <= totalShares){
            double realizedGain = 0.0;
            double unrealizedGain = 0.0;
            
            // sell shares and calculate realizedGain from the sold shares
            while(list.top() != null && soldShares > 0){
                if(soldShares >= list.top().shares){
                    realizedGain += (soldPrice - list.top().price) * list.top().shares;
                    soldShares -= list.top().shares;
                    list.pop();
                }else{
                    realizedGain += (soldPrice - list.top().price) * soldShares;   
                    list.top().shares -= soldShares;
                    soldShares = 0;
                }    
            }
            
            // calculate unrealizedGain from the remaining shares
            Node current = list.top();
            while(current != null){
                unrealizedGain += (soldPrice - current.price) * current.shares;
                current = current.next;
            }
            
            // update total shares
            totalShares -= soldShares;
            System.out.println("Realized P/L = " + realizedGain + " Unrealized P/L = " + unrealizedGain);
        }else{
            System.out.println("Sell command rejected");
        }
    }
    
    public void showList(){
        Node currentNode = list.top();
        System.out.print("head -> ");
        while (currentNode!=null){
            System.out.print("[" + currentNode.shares + "@" + currentNode.price + "B] -> ");
            currentNode = currentNode.next;
        }
        System.out.println("tail");
    }
}