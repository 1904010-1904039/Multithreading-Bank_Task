
public class Bank {

    String accountNumber;
    OperationsQueue operationsQueue;
    //private final Object lock = new Object();

    //initial balance
    volatile int balance = 0;

    //constructor 
    public Bank(String accountNumber, OperationsQueue operationsQueue) {
        this.accountNumber = accountNumber;
        this.operationsQueue = operationsQueue;
    }

    // A deposit function that will run in parallel on a separate thread. It will be a loop where in each iteration, it read the amount from the operationQueue and deposit the amount.
    public void deposit() {
        while (true) {
            System.out.println("Calling getNextItem from => deposite");
            //cs portion
            int amount = operationsQueue.getNextItem("deposite");
            
            if(amount == -9999) {
                break;
            }
            // if amount is positive, we will add it to the balance
            if (amount>0) {
                //cs portion
                
                balance =  balance + amount;
                System.out.println("Deposited: " + amount + " Balance: " + balance);
                
            }
            else{ //extracted amount is negative. Cann't deposit a negative amount

                //cs portion
                operationsQueue.add(amount);
                System.out.println("Operation added back "+amount+" by => (deposite func)");
                
            }
        }
    }

    // A withdraw function that will run in parallel on a separate thread. It will be a loop where in each iteration, it read the amount from the operationQueue and withdraw the amount.
    public void withdraw() {
        while (true) {
            // extracting the first value of the operationqueue list
            System.out.println("Calling getNextItem from => withdraw");

            // cs
            int amount = operationsQueue.getNextItem("withdraww");
            //base case
            if(amount == -9999) {
                break;
            }

            if(amount < 0) { // amount is negative, So withdraw money
                if(balance + amount < 0) { // Not enough money
                    System.out.println("Not enough balance = " + balance + " to withdraw = " + amount + " (Withdraw func)");
                }
                else { // enough money

                    balance =  balance + amount;
                        // jhamela 
                    System.out.println("Withdrawn: " + amount + " Balance: " + balance);
                    
                }
            }
            else{ // amount is positive. Can't withdraw a positive amount. So add back again

                // cs 
                
                operationsQueue.add(amount);
                System.out.println("operation added back "+amount+" by => (withdraw func)");
                
                
            }
        }
    }
}
