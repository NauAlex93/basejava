package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static int counter;
    private static final String LOCK1 = "LOCK1";
    private static final String LOCK2 = "LOCK2";

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };

        thread0.start();


        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();

        List<Thread> threads = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
//           thread.join();       // Thread main is waiting for Thread thread
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }


    public static class DeadLock {

        public static void main(String[] args) {
            Thread thread0 = new Thread(() -> {
                inc(LOCK1, LOCK2);
            });
            Thread thread1 = new Thread(() -> {
                inc(LOCK2, LOCK1);
            });
            thread0.start();
            thread1.start();
        }


        private static void inc(String LOCK1, String LOCK2) {
            synchronized (LOCK1) {
                System.out.println("holding " + LOCK1);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("waiting for " + LOCK2);
                synchronized (LOCK2) {
                    System.out.println("holding LOCK1 and LOCK2");
                }
            }
        }
    }
}
