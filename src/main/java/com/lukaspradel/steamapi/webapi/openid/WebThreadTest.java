package com.lukaspradel.steamapi.webapi.openid;

/**
 * Tests the WebThread class.
 */
public class WebThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new WebThread());
        thread.start();
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}