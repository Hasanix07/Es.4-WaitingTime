# Es.4-WaitingTime

## Descrizione

Questo progetto è un’applicazione Java da console per la gestione di una lista di brani musicali.  
Permette all’utente di inserire, salvare, caricare, ordinare, filtrare e analizzare brani musicali.

Il programma utilizza il multithreading tramite `Runnable` per eseguire le operazioni di lettura e scrittura su file JSON senza bloccare l’esecuzione principale.

---

## Funzionalità

- Inserimento di nuovi brani
- Salvataggio dei brani su file JSON
- Caricamento dei brani da file JSON
- Visualizzazione della lista dei brani
- Ordinamento dei brani per:
  - Numero di ascolti
  - Numero di like
  - Anno di pubblicazione
  - Titolo alfabetico
- Filtraggio dei brani per cantante o anno
- Visualizzazione di statistiche:
  - Totale ascolti
  - Brano più ascoltato
  - Brano con il maggior numero di like
- Reset della lista in memoria
- Countdown iniziale tramite thread
- Lettura e scrittura asincrona con thread dedicati

---

## Tecnologie utilizzate

- Java
- Programmazione orientata agli oggetti (OOP)
- Multithreading (`Thread`, `Runnable`)
- File JSON (gestiti manualmente, senza librerie esterne)
- Console (nessuna interfaccia grafica)

---

## Struttura del progetto

```

org.example
├── Main.java           # Menu principale e logica dell’applicazione
├── Brano.java          # Modello dati del brano musicale
├── GestioneFile.java   # Lettura e scrittura del file JSON
├── Scrittore.java      # Thread per il salvataggio dei dati
├── Lettore.java        # Thread per il caricamento dei dati
└── Countdown.java      # Thread per il conto alla rovescia iniziale

````

---

## Formato del file JSON

I dati vengono salvati in un file JSON con la seguente struttura:

```json
[
  {
    "id": "27",
    "titolo": "BABA",
    "cantante": "RondoDaSosa",
    "durata": "2:45",
    "annoPubblicazione": 2024,
    "numeroAscolti": 2100000,
    "numeroLike": 53000
  }
]
````

---

## Menu dell’applicazione

```
1) Inserisci brano
2) Salva su file
3) Carica da file
4) Mostra tutti i brani
5) Ordinamenti
6) Filtri
7) Statistiche
8) Reset lista
9) Esci
```

---

## Test effettuati

* Verifica dell’inserimento corretto dei brani
* Verifica della scrittura e lettura del file JSON
* Verifica degli ordinamenti e dei filtri
* Controllo della correttezza delle statistiche
* Test del funzionamento dei thread
* Verifica della stabilità del programma in caso di input errati

---

## Autore

Progetto realizzato da **Hasanix07**
Classe quinta superiore – Informatica
Anno scolastico 2025/2026

---
