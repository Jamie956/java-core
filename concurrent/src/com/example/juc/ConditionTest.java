package com.example.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void work() {
        lock.lock();
        try {
            try {
                System.out.println("Begin");
                condition.await();
                System.out.println("End");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public void continueWork() {
        lock.lock();
        try {
            System.out.println("Signal All");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest test = new ConditionTest();
        new Thread(() -> test.work()).start();

        TimeUnit.SECONDS.sleep(3);
        test.continueWork();
    }
}