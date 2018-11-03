package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

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
                inc(Thread.currentThread());
            });
            Thread thread1 = new Thread(() -> {
                inc2(Thread.currentThread());
            });
            thread0.start();
            thread1.start();
        }


        private static void inc(Thread thread) {
            synchronized (LOCK1) {
                System.out.println(thread.getName() + " holding lock 1");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(thread.getName() + " waiting for lock2");
                synchronized (LOCK2) {
                    System.out.println(thread.getName() + " holding lock1 and lock2");
                }
            }
        }

        private static void inc2(Thread thread) {
            synchronized (LOCK2) {
                System.out.println(thread.getName() + " holding lock 2");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(thread.getName() + " waiting for lock1");
                synchronized (LOCK1) {
                    System.out.println(thread.getName() + " holding lock2 and lock1");
                }
            }
        }
    }
}
