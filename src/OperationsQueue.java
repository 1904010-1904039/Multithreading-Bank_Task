import java.util.List;
import java.util.ArrayList;;
public class OperationsQueue {
    
    // Immutability of Reference: The final keyword means that the reference to the List<Integer> object stored in the operations variable cannot be changed after it is assigned. In other words, you cannot reassign operations to point to a different List object.
    private volatile List<Integer> operations = new ArrayList<>();

    public void addSimulation(int totalSimulation) {

        // Add 50 random numbers in the `operations` list. The number will be range from -100 to 100. It cannot be zero.
            for (int i = 0; i < totalSimulation; i++) {

                // Math.Random() generates a ranomd number between 0.0 to 1.0
                int random = (int) (Math.random() * 200) - 100;
                if (random != 0) {
                    // cs
                    operations.add(random);
                }
                System.out.println(i + ". New operation added: " + random);
                // add small delay to simulate the time taken for a new customer to arrive
                try {
                    Thread.sleep((int) (Math.random() * 80));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            operations.add(-9999);
    }
    public synchronized void add(int amount) {
        // appends the value to the end of the list.
        operations.add(amount);
    }

    public synchronized int getNextItem(String who) { //syncronized method
        // add a small delay to simulate the time taken to get the next operation.
        while(operations.isEmpty()) {
            try {
                System.out.println("Item is empty, infinite while loop");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // returns  the operationList
        System.out.printf(who + " => "  +"List: " + operations + "\n");

        // return and remove the item from the array
        // cs portion

        int value = operations.get(0);
        
        operations.remove(0);

        return value;
    }
}
