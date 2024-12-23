package lab14;

//Class that contains a shared counter
class CounterManager {
 public static int count = 0;
 /**
  * Synchronized method to increment the counter
  */
 public static synchronized void increaseCount() {
     count++;
 }
 /**
  * Get the current value of the counter
  * @return the current value of the counter
  */
 public static int getCount() {
     return count;
 }
}

class WorkerThread extends Thread {
 @Override
 public void run() {
     for (int iteration = 0; iteration < 100; iteration++) {
         CounterManager.increaseCount();
     }
 }
}

public class task2 {
 public static void main(String[] args) {
     // Create three threads
     WorkerThread worker1 = new WorkerThread();
     WorkerThread worker2 = new WorkerThread();
     WorkerThread worker3 = new WorkerThread();
     // Start all threads
     worker1.start();
     worker2.start();
     worker3.start();
     // Wait for threads to finish
     try {
         worker1.join();
         worker2.join();
         worker3.join();
     } catch (InterruptedException ex) {
         System.out.println("Main thread interrupted");
     }
     // Display the final counter value
     System.out.println("Final Counter Value: " + CounterManager.getCount());
 }
}
