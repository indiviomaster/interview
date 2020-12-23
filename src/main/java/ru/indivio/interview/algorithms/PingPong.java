package ru.indivio.interview.algorithms;

public class PingPong{
    volatile boolean ping = true;
    PingPong(){}
    Object lock = new Object();
    void doPing(){
        synchronized(lock) {
            if(!ping){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("do Ping");
            ping=false;
            lock.notify();

        }
    }
    void doPong(){
        synchronized(lock) {
            if(ping){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("do Pong");
            ping=true;
            lock.notify();

        }
    }
}
