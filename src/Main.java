public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello and welcome!");
        System.out.println("Initializing banking system..");

        int totalNumberOfSimulation = 10;

        //creating object of the two class
        OperationsQueue operationsQueue = new OperationsQueue();
        Bank bank = new Bank("123", operationsQueue);
        
        // Thread for simulation
        System.out.println("Initializing simulation....");
        // lambda expression which executes -> {}
        Thread simulationThread = new Thread(() -> {
            operationsQueue.addSimulation(totalNumberOfSimulation);
        });
        simulationThread.start(); //starting the thread

        // thread for deposit system
        System.out.println("Initializing deposit systen....");
        Thread depositThread = new Thread(() -> {
            bank.deposit();
        });
        depositThread.start();
        System.out.println("Deposit completed");

        //thread for withdrawo system
        System.out.println("Initializing withdraw systen....");
        Thread withdrawThread = new Thread(() -> {
            bank.withdraw();
        });
        withdrawThread.start();
        System.out.println("withdraw completed");


        // simulationThread.join();
        // depositThread.join();
        // withdrawThread.join();

        //Main thread completion status
        System.out.println("Main thread completed");
    }
}