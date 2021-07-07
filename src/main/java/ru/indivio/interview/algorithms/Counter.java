package ru.indivio.interview.algorithms;

public class Counter {
    private volatile int count = 0;
    void up(){
        synchronized(this) {
            count++;
        }
    }
    int getCount(){
        return count;
    };
}
