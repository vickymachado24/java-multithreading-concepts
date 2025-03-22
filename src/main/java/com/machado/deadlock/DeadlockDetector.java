package com.machado.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector extends Thread {
    public void run() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        while (true) {
            long[] deadlockedThreads = threadBean.findDeadlockedThreads();
            if (deadlockedThreads != null && deadlockedThreads.length > 0) {
                System.out.println("Deadlock detected! Affected threads:");
                for (long threadId : deadlockedThreads) {
                    System.out.println("Thread ID: " + threadId);
                }
                break; // Stop checking once detected (optional)
            }

            try {
                Thread.sleep(5000);  // Check every 5 seconds
            } catch (InterruptedException ignored) {}
        }
    }
}