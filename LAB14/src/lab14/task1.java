package lab14;

//Runnable implementation to print numbers from 1 to 10
class PrintNumbersTask implements Runnable {
 @Override
 public void run() {
     for (int num = 1; num <= 10; num++) {
         System.out.println("Number: " + num);
         // Pause for half a second
         try {
             Thread.sleep(500); 
         } catch (InterruptedException ex) {
             System.out.println("PrintNumbersTask interrupted");
         }
     }
 }
}

//Runnable implementation to print squares of numbers from 1 to 10
class PrintSquaresTask implements Runnable {
 @Override
 public void run() {
     for (int num = 1; num <= 10; num++) {
         System.out.println("Square of " + num + ": " + (num * num));
         // Pause for half a second
         try {
             Thread.sleep(500); 
         } catch (InterruptedException ex) {
             System.out.println("PrintSquaresTask interrupted");
         }
     }
 }
}

public class task1 {
 public static void main(String[] args) {
     // Create the two threads
     Thread numbersThread = new Thread(new PrintNumbersTask());
     Thread squaresThread = new Thread(new PrintSquaresTask());
     // Start both threads
     numbersThread.start();
     squaresThread.start();
 }
}
