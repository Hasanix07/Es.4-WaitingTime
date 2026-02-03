package org.example;


import java.util.List;


/**
 * Thread che salva una lista di brani su file JSON.
 */
public class Scrittore implements Runnable {


    private final String nomeFile;
    private final List<Brano> lista;
    private final GestioneFile gestioneFile;


    public Scrittore(String nomeFile, List<Brano> lista, GestioneFile gestioneFile) {
        this.nomeFile = nomeFile;
        this.lista = lista;
        this.gestioneFile = gestioneFile;
    }


    @Override
    public void run() {
        gestioneFile.scriviJson(nomeFile, lista);
    }
}

