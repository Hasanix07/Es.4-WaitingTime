package org.example;


/**
 * Rappresenta un brano musicale con tutte le informazioni principali.
 * Utilizzato sia per memorizzare i dati in memoria sia per scriverli/leggerli da JSON.
 */
public class Brano {
    private String id;
    private String titolo;
    private String cantante;
    private String durata;
    private int annoPubblicazione;
    private long numeroAscolti;
    private int numeroLike;


    public Brano(String id, String titolo, String cantante, String durata,
                 int annoPubblicazione, long numeroAscolti, int numeroLike) {
        this.id = id;
        this.titolo = titolo;
        this.cantante = cantante;
        this.durata = durata;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroAscolti = numeroAscolti;
        this.numeroLike = numeroLike;
    }


    // Getter e setter
    public String getId() { return id; }
    public String getTitolo() { return titolo; }
    public String getCantante() { return cantante; }
    public String getDurata() { return durata; }
    public int getAnnoPubblicazione() { return annoPubblicazione; }
    public long getNumeroAscolti() { return numeroAscolti; }
    public int getNumeroLike() { return numeroLike; }


    public void setId(String id) { this.id = id; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setCantante(String cantante) { this.cantante = cantante; }
    public void setDurata(String durata) { this.durata = durata; }
    public void setAnnoPubblicazione(int anno) { this.annoPubblicazione = anno; }
    public void setNumeroAscolti(long n) { this.numeroAscolti = n; }
    public void setNumeroLike(int n) { this.numeroLike = n; }


    /**
     * Restituisce una rappresentazione testuale del brano.
     */
    @Override
    public String toString() {
        return "Brano{" +
                "id='" + id + '\'' +
                ", titolo='" + titolo + '\'' +
                ", cantante='" + cantante + '\'' +
                ", durata='" + durata + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroAscolti=" + numeroAscolti +
                ", numeroLike=" + numeroLike +
                '}';
    }
}

