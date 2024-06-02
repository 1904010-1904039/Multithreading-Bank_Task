import java.util.List;
import java.util.ArrayList;;

public class OperationsQueue {

    private volatile List<Integer> operations = new ArrayList<>();
    private final Object lock = new Object(); // Adding a lock object

    public void addSimulation(int totalSimulation) {
        synchronized (lock) { 
            for (int i = 0; i < totalSimulation; i++) {
                int random = (int) (Math.random() * 200) - 100;
                if (random != 0) {
                    operations.add(random);
                }
                System.out.println(i+1 + ". New operation added: " + random);
                try {
                    Thread.sleep((int) (Math.random() * 80));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify(); // Notify waiting threads after adding an operation
            }
            operations.add(-9999);
            lock.notifyAll(); // Notify waiting threads after adding termination signal
        }
    }

    public int getNextItem(String who) {
        synchronized (lock) {
            // timeout er moddhe kono item add na hoile break
            long timeout = 5000; 
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            
            while (operations.isEmpty() && elapsedTime < timeout) {
                try {
                    System.out.println("Item is empty, waiting for operations...");
                    lock.wait(timeout - elapsedTime); // Wait with timeout
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Update elapsed time
                elapsedTime = System.currentTimeMillis() - startTime;
            }
            
            if (operations.isEmpty()) {
                // No operations available within the timeout period
                System.out.println("No operations available within timeout period");
                operations.add(-9999);
                return -9999;
            }

            int value = operations.remove(0);

            if (value == -9999) {
                // Break out of the loop when the last item is -9999
                return -9999;
            }
    
            System.out.printf(who + " => " + "List: " + operations + "\n");
            
            return value;
        }
    }
    
}

