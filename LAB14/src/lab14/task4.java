package lab14;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

// AccountManager class with deposit, withdraw, and getBalance methods
class AccountManager {
    private static AtomicInteger accountBalance = new AtomicInteger(1000);
    /**
     * Perform a deposit operation.
     * @param depositAmount the amount to deposit
     */
    public static void deposit(int depositAmount) {
        accountBalance.addAndGet(depositAmount);
        System.out.println(Thread.currentThread().getName() + " deposited: " + depositAmount);
    }
    /**
     * Perform a withdrawal operation.
     * @param withdrawalAmount the amount to withdraw
     */
    public static void withdraw(int withdrawalAmount) {
        if (withdrawalAmount > 0 && accountBalance.get() >= withdrawalAmount) {
            accountBalance.addAndGet(-withdrawalAmount);
            System.out.println(Thread.currentThread().getName() + " withdrew: " + withdrawalAmount);
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw: " + withdrawalAmount + " but insufficient funds. Current Balance: " + accountBalance.get());
        }
    }
    /**
     * Get the current account balance.
     * @return the current balance
     */
    public static int getBalance() {
        return accountBalance.get();
    }
}

// Customer class that performs random transactions
class Customer implements Runnable {
    private final Random transactionGenerator = new Random();
    @Override
    public void run() {
        for (int transactionCount = 0; transactionCount < 2; transactionCount++) { // Perform 2 random transactions
            int transactionAmount = transactionGenerator.nextInt(100) + 1; // Random amount between 1 and 100
            if (transactionGenerator.nextBoolean()) {
                AccountManager.deposit(transactionAmount);
            } else {
                AccountManager.withdraw(transactionAmount);
            }
            try {
                Thread.sleep(100); // Simulate processing time
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted");
            }
        }
    }
}

public class task4 {
    public static void main(String[] args) {
        // Create multiple customer threads
        Thread customer1 = new Thread(new Customer(), "Customer1");
        Thread customer2 = new Thread(new Customer(), "Customer2");
        Thread customer3 = new Thread(new Customer(), "Customer3");
        // Start the customer threads
        customer1.start();
        customer2.start();
        customer3.start();
        // Wait for all threads to finish
        try {
            customer1.join();
            customer2.join();
            customer3.join();
        } catch (InterruptedException ex) {
            System.out.println("Main thread interrupted");
        }
        // Display the final balance
        System.out.println("Final Account Balance: " + AccountManager.getBalance());
    }
}
