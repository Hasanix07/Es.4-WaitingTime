package org.example;


import java.util.*;
import java.util.stream.Collectors;


/**
 * Programma principale con menu testuale.
 * Permette inserimento, salvataggio/lettura, ordinamenti, filtri e statistiche.
 */
public class Main {


    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_DEFAULT = "Musica.json";


    public static void main(String[] args) {
        // Countdown iniziale
        Thread countdown = new Thread(new Countdown());
        countdown.start();
        try { countdown.join(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }


        GestioneFile gf = new GestioneFile();
        List<Brano> lista = new ArrayList<>();
        boolean exit = false;


        while (!exit) {
            stampaMenu();
            int scelta = leggiInt("Scegli: ");
            switch (scelta) {
                case 1 -> inserisciBrano(lista);
                case 2 -> salva(lista, gf);
                case 3 -> lista = carica(gf);
                case 4 -> mostra(lista);
                case 5 -> ordina(lista);
                case 6 -> filtra(lista);
                case 7 -> statistiche(lista);
                case 8 -> { lista.clear(); System.out.println("Lista svuotata."); }
                case 9 -> exit = true;
                default -> System.out.println("Opzione non valida.");
            }
        }
        System.out.println("Programma terminato. Ciao!");
    }


    private static void stampaMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1) Inserisci brano");
        System.out.println("2) Salva su file");
        System.out.println("3) Carica da file");
        System.out.println("4) Mostra tutti i brani");
        System.out.println("5) Ordinamenti");
        System.out.println("6) Filtri");
        System.out.println("7) Statistiche");
        System.out.println("8) Reset lista");
        System.out.println("9) Esci");
    }


    private static void inserisciBrano(List<Brano> lista) {
        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Titolo: "); String titolo = scanner.nextLine();
        System.out.print("Cantante: "); String cantante = scanner.nextLine();
        System.out.print("Durata (es. 3:20): "); String durata = scanner.nextLine();
        int anno = leggiInt("Anno pubblicazione: ");
        long ascolti = leggiLong("Numero ascolti: ");
        int like = leggiInt("Numero like: ");
        lista.add(new Brano(id,titolo,cantante,durata,anno,ascolti,like));
        System.out.println("Brano aggiunto!");
    }


    private static void salva(List<Brano> lista, GestioneFile gf) {
        Thread t = new Thread(new Scrittore(FILE_DEFAULT, lista, gf));
        t.start();
        try { t.join(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }


    private static List<Brano> carica(GestioneFile gf) {
        Thread t = new Thread(new Lettore(FILE_DEFAULT, gf));
        t.start();
        try { t.join(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return gf.leggiJson(FILE_DEFAULT);
    }


    private static void mostra(List<Brano> lista) {
        if (lista.isEmpty()) { System.out.println("(Lista vuota)"); return; }
        for (Brano b : lista) System.out.println(b);
    }


    private static void ordina(List<Brano> lista) {
        if (lista.isEmpty()) { System.out.println("Lista vuota."); return; }
        System.out.println("1) Ascolti decrescente  2) Like decrescente  3) Anno crescente  4) Titolo alfabetico");
        int sc = leggiInt("Scegli: ");
        switch (sc) {
            case 1 -> lista.sort(Comparator.comparingLong(Brano::getNumeroAscolti).reversed());
            case 2 -> lista.sort(Comparator.comparingInt(Brano::getNumeroLike).reversed());
            case 3 -> lista.sort(Comparator.comparingInt(Brano::getAnnoPubblicazione));
            case 4 -> lista.sort(Comparator.comparing(Brano::getTitolo));
            default -> { System.out.println("Opzione non valida."); return; }
        }
        System.out.println("Ordinamento applicato.");
        mostra(lista);
    }


    private static void filtra(List<Brano> lista) {
        if (lista.isEmpty()) { System.out.println("Lista vuota."); return; }
        System.out.println("1) Per cantante  2) Per anno");
        int sc = leggiInt("Scegli: ");
        if (sc == 1) {
            System.out.print("Nome cantante: "); String key = scanner.nextLine().toLowerCase();
            lista.stream().filter(b -> b.getCantante().toLowerCase().contains(key)).forEach(System.out::println);
        } else if (sc == 2) {
            int anno = leggiInt("Anno: ");
            lista.stream().filter(b -> b.getAnnoPubblicazione() == anno).forEach(System.out::println);
        } else System.out.println("Opzione non valida.");
    }


    private static void statistiche(List<Brano> lista) {
        if (lista.isEmpty()) { System.out.println("Lista vuota."); return; }
        long tot = lista.stream().mapToLong(Brano::getNumeroAscolti).sum();
        System.out.println("Totale ascolti: " + tot);
        lista.stream().max(Comparator.comparingLong(Brano::getNumeroAscolti)).ifPresent(b -> System.out.println("Più ascoltato: " + b.getTitolo()));
        lista.stream().max(Comparator.comparingInt(Brano::getNumeroLike)).ifPresent(b -> System.out.println("Più like: " + b.getTitolo()));
    }


    private static int leggiInt(String msg) {
        while (true) {
            try { System.out.print(msg); return Integer.parseInt(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.println("Inserisci un numero valido."); }
        }
    }


    private static long leggiLong(String msg) {
        while (true) {
            try { System.out.print(msg); return Long.parseLong(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.println("Inserisci un numero valido."); }
        }
    }
}

