package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TaskTwo {
    private final int limit;
    private int count;
    private String[] result;

    public TaskTwo(int limit) {
        this.limit = limit;
        this.count = 1;
        result = new String[limit];
        run();
    }

    public synchronized void fizz() throws InterruptedException {
        while (count <= limit) {
            if (count % 3 == 0 && count % 5 != 0) {
                //System.out.print("fizz, ");
                result[count-1] = "fizz";
                count++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void buzz() throws InterruptedException {
        while (count <= limit) {
            if (count % 3 != 0 && count % 5 == 0) {
                //System.out.print("buzz, ");
                result[count-1] = "buzz";
                count++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void fizzbuzz() throws InterruptedException {
        while (count <= limit) {
            if (count % 3 == 0 && count % 5 == 0) {
                //System.out.print("fizzbuzz, ");
                result[count-1] = "fizzbuzz";
                count++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public synchronized void number() throws InterruptedException {
        while (count <= limit) {
            if (count % 3 != 0 && count % 5 != 0) {
                //System.out.print(count + ", ");
                result[count-1] =String.valueOf(count);
                count++;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    public void run(){
        Thread threadA = new Thread(() -> {
            try {
                fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String printResult = String.join(",",result);
        System.out.println(printResult);
    }
}
