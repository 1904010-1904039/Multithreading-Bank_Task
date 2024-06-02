import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Bank {

    String accountNumber;
    OperationsQueue operationsQueue;
    //private final Object lock = new Object();

    //initial balance
    volatile int balance = 0;
    Lock lock = new ReentrantLock();

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
                lock.lock(); // Acquiring the lock before updating balance(volatile)
                 try {
                     balance += amount;
                     System.out.println("Deposited: " + amount + " Balance: " + balance);
                 } finally {
                     lock.unlock(); // Releasing the lock after updating balance
                 }
            }
            else{ //extracted amount is negative. Cann't deposit a negative amount
                System.out.println("Invalid deposit amount: " + amount);  
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
                lock.lock();
                try {
                    if(balance + amount < 0) { // Not enough money
                        System.out.println("Not enough balance = " + balance + " to withdraw = " + amount + " (Withdraw func)");
                    }
                    else { // enough money
    
                        balance =  balance + amount;
                            // jhamela 
                        System.out.println("Withdrawn: " + amount + " Balance: " + balance);
                        
                    }
                } finally {
                    lock.unlock();
                }
            }
            else{ // amount is positive. Can't withdraw a positive amount. Give warning now 

                System.out.println("Invalid withdraw amount: " + amount);
  
            }
        }
    }
}