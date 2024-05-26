public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and welcome!");
        System.out.printf("Initializing banking system..");

        int totalNumberOfSimulation = 10;

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
        System.out.printf("Initializing deposit systen....");
        Thread depositThread = new Thread(() -> {
            bank.deposit();
        });
        depositThread.start();
        System.out.println("completed");

        //thread for withdrawo system
        System.out.printf("Initializing withdraw systen....");
        Thread withdrawThread = new Thread(() -> {
            bank.withdraw();
        });
        withdrawThread.start();
        System.out.println("completed");

        //Main thread completion status
        System.out.println("completed");
    }
}