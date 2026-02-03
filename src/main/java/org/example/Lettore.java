package org.example;


import java.util.List;


/**
 * Thread che legge i brani da un file JSON e li stampa su console.
 */
public class Lettore implements Runnable {


    private final String nomeFile;
    private final GestioneFile gestioneFile;


    public Lettore(String nomeFile, GestioneFile gestioneFile) {
        this.nomeFile = nomeFile;
        this.gestioneFile = gestioneFile;
    }


    @Override
    public void run() {
        List<Brano> lista = gestioneFile.leggiJson(nomeFile);
        if (lista.isEmpty()) {
            System.out.println("(Nessun brano trovato o file vuoto)");
        } else {
            System.out.println("\n--- Brani letti dal file ---");
            for (Brano b : lista) {
                System.out.println(b);
            }
        }
    }
}

