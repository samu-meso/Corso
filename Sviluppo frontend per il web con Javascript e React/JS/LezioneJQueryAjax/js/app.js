$(document).ready(function() {
    console.log("DOM pronto. Inizializzazione Dashboard...");

    // ==========================================
    // SEZIONE 1: Eventi di Input, Classi e Traversing
    // ==========================================

    // A. Aggiungere una nota
    $("#btn-aggiungi").click(function() {
        // 1. Leggiamo il valore dell'input (GET val)
        let testoNota = $("#input-utente").val();

        // Se l'input non è vuoto
        if (testoNota.trim() !== "") {
            // Creiamo il nuovo elemento lista con un bottone X
            let nuovoLi = `
                <li>
                    <span class="testo-nota">${testoNota}</span>
                    <button class="btn-elimina">X</button>
                </li>
            `;
            
            // Lo appendiamo alla lista
            $("#lista-note").append(nuovoLi);
            
            // 2. Svuotiamo il campo input (SET val)
            $("#input-utente").val("");
        }
    });

    // B. Segnare una nota come completata (Uso la delegazione degli eventi con .on)
    $("#lista-note").on("click", ".testo-nota", function() {
        // Aggiunge o rimuove la classe 'completato' al click
        $(this).toggleClass("completato");
    });

    // C. Eliminare una nota (Traversing del DOM con .parent)
    $("#lista-note").on("click", ".btn-elimina", function() {
        // $(this) è il bottone X. .parent() seleziona il tag <li> che lo contiene.
        $(this).parent().remove();
    });

    // ==========================================
    // SEZIONE 2: Effetti Mouse (.hover)
    // ==========================================

    $("#footer-info").hover(
        function() {
            // Mouse entra (mouseenter)
            $(this).css("background-color", "#d1e7dd");
            $(this).text("Sistema Operativo: Attivo | API: Online");
        },
        function() {
            // Mouse esce (mouseleave)
            $(this).css("background-color", "#eee");
            $(this).text("Passa il mouse qui per i dettagli di sistema.");
        }
    );

    // ==========================================
    // SEZIONE 3: Chiamate AJAX (GET)
    // ==========================================

    $("#btn-fetch").click(function() {
        // Cambiamo il testo del bottone durante il caricamento
        let btn = $(this);
        btn.text("Caricamento in corso...");

        $.ajax({
            url: "https://jsonplaceholder.typicode.com/users",
            method: "GET",
            success: function(utenti) {
                // Svuotiamo il contenitore dei risultati
                $("#risultati-api").empty();

                // Cicliamo solo i primi 3 utenti per non intasare l'interfaccia
                $.each(utenti.slice(0, 3), function(indice, utente) {
                    let bloccoUtente = `
                        <div class="user-entry">
                            <strong>${utente.name}</strong><br>
                            Email: <a href="mailto:${utente.email}">${utente.email}</a>
                        </div>
                    `;
                    $("#risultati-api").append(bloccoUtente);
                });

                // Ripristiniamo il testo del bottone
                btn.text("Carica Utenti (GET)");
            },
            error: function() {
                $("#risultati-api").html("<p style='color:red;'>Errore nel caricamento dati.</p>");
                btn.text("Riprova");
            }
        });
    });

    // ==========================================
    // SEZIONE 4: Chiamate AJAX (POST) e Tasto Invio
    // ==========================================

    function inviaPost() {
        let titolo = $("#post-title").val();
        let contenuto = $("#post-body").val();

        if(titolo === "" || contenuto === "") {
            $("#feedback-post").html("<p style='color:red;'>Compila entrambi i campi!</p>");
            return;
        }

        let datiDaInviare = {
            title: titolo,
            body: contenuto,
            userId: 1
        };

        $.ajax({
            url: "https://jsonplaceholder.typicode.com/posts",
            method: "POST",
            data: datiDaInviare,
            success: function(risposta) {
                $("#feedback-post").html(`
                    <p style="color: green;">
                        <strong>Post inviato!</strong> ID generato: ${risposta.id}
                    </p>
                `);
                
                // Pulizia campi
                $("#post-title").val("");
                $("#post-body").val("");
            },
            error: function() {
                $("#feedback-post").html("<p style='color:red;'>Errore durante l'invio.</p>");
            }
        });
    }

    // Esegui la POST al click del bottone
    $("#btn-invia-post").click(inviaPost);

    // Esegui la POST anche premendo "Invio" sul campo del contenuto (Bonus!)
    $("#post-body").keypress(function(evento) {
        if (evento.which === 13) { // 13 è il codice del tasto Invio (Enter)
            inviaPost();
        }
    });

});