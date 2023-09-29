package org.example;

public class TaskOne {

    public TaskOne(int limit){
       timers(limit);
    }
    private void timers(int limit){
        int[] counter = {0};
        Thread thread1 = new Thread(() -> {
               while (counter[0] <= limit){
                    System.out.println(counter[0]++ + " second");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        });

        Thread thread2 = new Thread(() -> {
                while (counter[0] <= limit-5){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Five second later");
                }
        });
        thread1.start();
        thread2.start();
    }
}
