package org.example;


/**
 * Thread che esegue un conto alla rovescia prima dell'avvio dell'app.
 */
public class Countdown implements Runnable {


    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Avvio tra " + i + "...");
                Thread.sleep(1000);
            }
            System.out.println("Pronti, via!\n");
        } catch (InterruptedException e) {
            System.out.println("Countdown interrotto.");
            Thread.currentThread().interrupt();
        }
    }
}

