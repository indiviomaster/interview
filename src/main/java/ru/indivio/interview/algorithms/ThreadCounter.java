package ru.indivio.interview.algorithms;

import java.util.concurrent.locks.Lock;

public class ThreadCounter {
    public static void main(String[] args) {
        Counter count = new Counter();

        Thread threadOne=  new Thread(()->{
            for(int i=1;i<=10;i++){
                    count.up();
                    System.out.println("t1="+count.getCount());
            }
        });

        Thread threadTwo=  new Thread(() -> {
            for(int i=1;i<=10;i++){
                //lock.tryLock(100);
                count.up();
                System.out.println("t2="+count.getCount());
            }
        });
        Thread threadThree =  new Thread(()->{
            for(int i=1;i<=10;i++){
                count.up();
                System.out.println("t3="+count.getCount());
            }
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }
}
