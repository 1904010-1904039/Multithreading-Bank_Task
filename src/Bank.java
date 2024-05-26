
public class Bank {

    String accountNumber;
    OperationsQueue operationsQueue; //object of OperationQueue class

    //initial balance
    int balance = 0;

    public Bank(String accountNumber, OperationsQueue operationsQueue) {
        this.accountNumber = accountNumber;
        this.operationsQueue = operationsQueue;
    }

    // A deposit function that will run in parallel on a separate thread. It will be a loop where in each iteration, it read the amount from the operationQueue and deposit the amount.
    public void deposit() {
        while (true) {

            // extracting the first value of the operationqueue list
            int amount = operationsQueue.getNextItem();
            if(amount == -9999) {
                break;
            }
            // if amount is positive, we will add it to the balance
            if (amount>0) {
                balance =  balance + amount;
                System.out.println("Deposited: " + amount + " Balance: " + balance);
            }
            else{ //extracted amount is negative. Cann't deposit a negative amount
                operationsQueue.add(amount);
                System.out.println("operation added back "+amount+" (deposite func)");
            }

        }
    }

    // A withdraw function that will run in parallel on a separate thread. It will be a loop where in each iteration, it read the amount from the operationQueue and withdraw the amount.
    public void withdraw() {
        while (true) {
            // extracting the first value of the operationqueue list
            int amount = operationsQueue.getNextItem();
            //base case
            if(amount == -9999) {
                break;
            }

            // if(balance+amount<0){

            //     System.out.println("Not enough balance to withdraw "+amount+" (Withdraw func)");
            //     continue;
            // }

            // if (amount<0) { // enough money to withdraw the amount.
            //     balance =  balance + amount;
            //     System.out.println("Withdrawn: " + amount + " Balance: " + balance);
            // }

            if(amount < 0) { // amount is negative, So withdraw money
                if(balance + amount < 0) { // Not enough money
                    System.out.println("Not enough balance to withdraw "+amount+" (Withdraw func)");
                }
                else { // enough money
                    balance =  balance + amount;
                    System.out.println("Withdrawn: " + amount + " Balance: " + balance);
                }
            }
            else{ // amount is positive. Can't withdraw a positive amount. So add back again
                operationsQueue.add(amount);
                System.out.println("operation added back "+amount+" (withdraw funct)");
            }
        }
    }
}
