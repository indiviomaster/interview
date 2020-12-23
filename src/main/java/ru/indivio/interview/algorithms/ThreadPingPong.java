package ru.indivio.interview.algorithms;

import java.util.concurrent.locks.Lock;

public class ThreadPingPong {
    public static void main(String[] args) {
        Object monitor =new Object();
        PingPong pingPong = new PingPong();

        Thread pingThread = new Thread(new Runnable() {

            @Override
            public void run() {
                int i=0;
                while (i<10){
                    pingPong.doPing();
                    //monitor.notify();
                    i++;
                }
            }
        });
        Thread pongThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<10){
                    pingPong.doPong();
                    //monitor.notify();
                    i++;
                }
            }
        });

        System.out.println("Start demon Ping");
        //pingThread.setDaemon(true);
        pingThread.start();
        System.out.println("Start demon Pong");
        //pongThread.setDaemon(true);
        pongThread.start();
    }

}

