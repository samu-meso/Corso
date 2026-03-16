# Database e tecnologie di acccsso ai dati

### Lezione 29/01/2026:

#### Sistemi informativi (SI): è un elemento fondamentale di un’organizzazione (azienda, ente, ecc.) che si occupa della gestione delle informazioni rilevanti per il raggiungimento degli obiettivi dell’organizzazione stessa.
Con gestione si intende:
- Elaborazione;
- Distribuzione;
- Conservazione (privacy, GDPR);
- Produzione.

L’organizzazione come sistema; un sistema informativo funziona come un ciclo di controllo:
1. Input: acquisizione dei dati;
2. Attività primarie (Memoria): Elaborazione e conservazione;
3. Output: distribuzione delle informazioni

- L’informazione è un bene a valore crescente;
- È la materia che viene trasformata dai sistemi informativi;
- Ha un costo associato;
C’è una differenza sostanziale tra il dato grezzo e l’informazione:
- Dato (materia prima): elemento grezzo con scarso valore se non interpretato. Esempio “12345”, “Mario”, “Rossi”.
- Informazione (valore): il dato interpretato;

Quantità e valore sono inversamente proporzionali: fonti primarie, informazioni sele

#### Il mondo funziona sui dati:
I sistemi di data


#### Sistema informativo vs Sistema informatico

Non sono sinonim, ma concetti distinti:
- Sistemi informati

#### Le insidie dei File System tradizionali:
Le applicazioni basate sui file hanno mostrato gravi limiti:
- Ridondanza e Incoerenza: i dati sono duplicati in file diversi, portando a disallineamenti;
- Difficoltà di Accesso: necessità di scrivere un nuovo programma per ogni query o compito;
- Isolamento dei dati: i dati sono sparsi in formati e file multipli;
- Problemi di integrità: i vincoli (es. saldo > 0) sono "sepolti" nel codice del programma invecfe di essere espliciti.

#### DBMS:
Per gestire i dati digitali usiamo strumenti specifici:
- DB (Database): Una collezione di dati che rappresentano le informazioni di interesse per un'organizzazione.
- DBMS (Database Management System): Un sistema software in grado di gestire grandi collezioni di dati condivise da più applicazioni e utenti.

#### Lo scopo dei Sistemi di database:
Un DBMS risolve i problemi dei file system garantendo:
- Atomicità degli Aggiornamenti: i guasti non devono lasciare il database in uno stato incoerente (es. un bonifico deve avvenire per intero o per nulla);
- Accesso concorrente: utenti multipli devono accedere ai dati simultaneamente senza creare incosistenze;
- Sicurezza: gestione granulare degli accessi ai dati.

#### Visione dei dati e astrazione:
L'obiettivo è fornire una visione astratta, nascondendo la complessità:
1. Livello vista: come gli utenti finali vedono i dati;
2. Livello logico: quali dati sono memorizzati e le loro reazioni (es. tabelle);
3. Livello fisico: come i dati sono effettivamente memorizzati su disco.
>Indipendenza fisica dei dati: la capacità di modificare lo schema fisico senza dover cambiare lo schema logico (i programmi applicativi).
#### Modelli di dati: il modello relazionale:
E' il modello più ampiamente utilizzato.
- Struttura basata su Righe (Record) e Colonne (Attributi)<br>
**Esempio tabella instructor**



#### Linguaggi del database:
- DDL (Data Definition Language):
    - Definisce lo schema del database e i vincoli;
    - L'output viene memorizzato nel Dizionario dei dati (Metadati).
- DML (Data Manipulation Language):
    - Noto come Query Language;
    - Procedurale: l'utente specifica quali dati servono e come ottenerli;
    - Dichiarativo (non procedurale): l'utente specifica solo quali dati servono (es. sql)

#### SQL: Lo standard industriale:
SQL è un linguaggio non procedurale ampiamente adottato.
- Logica: prende in input diverse tabelle e restituisce una singola tabella;
- Esempio:  
    ```sql
    select name from instructor where dept_name = 'Comp. Sci.'
    ```
- Limitazioni: Non è Turing-equivalennte. Per calcoli complessi si usa un linguaggio ospite (C++, Java, Python) tramite ODBC/JDBC


#### Workflow nella progettazione di una Base Di Dati:
- Progettazione concettuale.
- Progettazione logica.
- Progettazione fisica.

#### Il modello entità - Relazione (Entity-Relationship):
- Uno "standard de facto"

### Lezione 05/02/2026:

#### Entità:
- Rappresentano classi di oggetti (fatti, cose, persone, ad esempio) che hanno proprietà comuni e esistenza autonoma
- Città, Dipartimento, Impiegato, Acquisto

![Immagine Web](https://i.imgur.com/SiZcxb1.png)