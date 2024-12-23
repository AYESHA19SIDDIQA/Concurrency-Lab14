package lab14;

import java.util.concurrent.CopyOnWriteArrayList;

class SharedListTask implements Runnable {
    private final CopyOnWriteArrayList<Integer> threadSafeList;
    /**
     * Constructor
     * @param threadSafeList
    */
    public SharedListTask(CopyOnWriteArrayList<Integer> threadSafeList) {
        this.threadSafeList = threadSafeList;
    }
    @Override
    public void run() {
        // Add elements to the list
        for (int element = 0; element < 5; element++) {
            threadSafeList.add(element);
        }
    }
}

public class task3 {
    public static void main(String[] args) {
        // Shared thread-safe data structure
        CopyOnWriteArrayList<Integer> safeList = new CopyOnWriteArrayList<>();
        // Create threads for CopyOnWriteArrayList
        Thread writerThread1 = new Thread(new SharedListTask(safeList));
        Thread writerThread2 = new Thread(new SharedListTask(safeList));
        // Start all threads
        writerThread1.start();
        writerThread2.start();
        // Wait for threads to finish
        try {
            writerThread1.join();
            writerThread2.join();
        } catch (InterruptedException ex) {
            System.out.println("Main thread interrupted");
        }
        // Print final state of shared data structures
        System.out.println("Final List: " + safeList);
    }
}
