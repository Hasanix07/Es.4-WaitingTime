package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Gestione della lettura e scrittura di una lista di brani in formato JSON.
 * Non usa librerie esterne, tutto fatto manualmente.
 */
public class GestioneFile {


    /**
     * Scrive la lista di brani su file JSON manualmente.
     */
    public void scriviJson(String nomeFile, List<Brano> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile))) {
            bw.write("[\n");
            for (int i = 0; i < lista.size(); i++) {
                Brano b = lista.get(i);
                bw.write("  {\n");
                bw.write("    \"id\": \"" + b.getId() + "\",\n");
                bw.write("    \"titolo\": \"" + b.getTitolo() + "\",\n");
                bw.write("    \"cantante\": \"" + b.getCantante() + "\",\n");
                bw.write("    \"durata\": \"" + b.getDurata() + "\",\n");
                bw.write("    \"annoPubblicazione\": " + b.getAnnoPubblicazione() + ",\n");
                bw.write("    \"numeroAscolti\": " + b.getNumeroAscolti() + ",\n");
                bw.write("    \"numeroLike\": " + b.getNumeroLike() + "\n");
                bw.write("  }" + (i < lista.size()-1 ? "," : "") + "\n");
            }
            bw.write("]");
            System.out.println("File JSON scritto correttamente!");
        } catch (IOException e) {
            System.out.println("Errore scrittura file: " + e.getMessage());
        }
    }


    /**
     * Legge un file JSON manualmente e ricrea una lista di Brano.
     * Nota: funziona solo con il formato prodotto da scriviJson.
     */
    public List<Brano> leggiJson(String nomeFile) {
        List<Brano> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String line;
            Brano b = null;
            String id="", titolo="", cantante="", durata="";
            int anno=0, like=0;
            long ascolti=0;


            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"id\":")) {
                    id = line.split(":")[1].trim().replace("\"","").replace(",","");
                } else if (line.startsWith("\"titolo\":")) {
                    titolo = line.split(":")[1].trim().replace("\"","").replace(",","");
                } else if (line.startsWith("\"cantante\":")) {
                    cantante = line.split(":")[1].trim().replace("\"","").replace(",","");
                } else if (line.startsWith("\"durata\":")) {
                    durata = line.split(":")[1].trim().replace("\"","").replace(",","");
                } else if (line.startsWith("\"annoPubblicazione\":")) {
                    anno = Integer.parseInt(line.split(":")[1].trim().replace(",",""));
                } else if (line.startsWith("\"numeroAscolti\":")) {
                    ascolti = Long.parseLong(line.split(":")[1].trim().replace(",",""));
                } else if (line.startsWith("\"numeroLike\":")) {
                    like = Integer.parseInt(line.split(":")[1].trim().replace(",",""));
                } else if (line.equals("}") || line.equals("},")) {
                    // Fine brano
                    b = new Brano(id,titolo,cantante,durata,anno,ascolti,like);
                    lista.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura file: " + e.getMessage());
        }
        return lista;
    }
}

