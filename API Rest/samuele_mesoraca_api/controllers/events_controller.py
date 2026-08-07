from datetime import datetime
from flask import Blueprint, abort, jsonify, request
from helpers.fake_database import (
    get_all_events,
    get_event_by_id,
    add_event,
    update_event,
    get_event_stats,
    search_events,
    delete_event
)
from models.events import Event

events_controller = Blueprint("events_controller", __name__)

CATEGORIE_VALIDE = [
    "Concerti",
    "Formazione",
    "Mostre & Cultura"
]


@events_controller.route("/api/events", methods=["GET"])
def api_get_all_events():
    return jsonify([event.serialize() for event in get_all_events()]), 200


@events_controller.route("/api/events/<int:event_id>", methods=["GET"])
def api_get_event_by_id(event_id):
    if event_id <= 0:
        abort(400, "ID deve essere un intero positivo.")
    event = get_event_by_id(event_id)
    if event is None:
        abort(404, f"Evento con ID {event_id} non trovato.")
    return jsonify(event.serialize()), 200


@events_controller.route("/api/events", methods=["POST"])
def api_add_event():
    data = request.json
    if not data:
        abort(400, "Dati JSON mancanti.")

    errors = validate_event_data(data)

    if errors:
        abort(400, errors)

    event_date = datetime.strptime(data["data_evento"], "%Y-%m-%d").date()
    # Creazione del nuovo evento
    new_event = Event(
        0,
        data["titolo"].strip(),
        data["descrizione"].strip(),
        data["categoria"].strip(),
        event_date,
        float(data["prezzo"]),
        int(data["posti_disponibili"]),
        bool(data["in_evidenza"]),
    )
    new_event.id = add_event(new_event)  # Genera un ID unico per il nuovo evento
    if new_event.id is None:
        return abort(500, "An error occurred while saving the event. Try again later.")
    return jsonify({"id": new_event.id}), 201


@events_controller.route("/api/events/search/<string:keyword>", methods=["GET"])
def api_search_events(keyword):
    events = search_events(keyword)
    return jsonify([event.serialize() for event in events]), 200

@events_controller.route("/api/events/<int:event_id>", methods=["DELETE"])
def api_delete_event(event_id):
    if event_id <= 0:
        abort(400, "ID deve essere un intero positivo.")

    event = get_event_by_id(event_id)

    if event is None:
        abort(404, f"Evento con ID {event_id} non trovato.")

    # ATTENZIONE: attributo interno inglese
    if event.available_places == 0:
        return jsonify({
            "message": "Non puoi eliminare un evento sold out."
        }), 403

    success = delete_event(event_id)

    if not success:
        abort(500, f"Errore durante l'eliminazione dell'evento con ID {event_id}.")

    return "", 204

@events_controller.route("/api/events/<int:event_id>", methods=["PUT"])
def api_update_event(event_id):
    if event_id <= 0:
        abort(400, "ID deve essere un intero positivo.")

    data = request.json
    if not data:
        abort(400, "Dati JSON mancanti.")

    if get_event_by_id(event_id) is None:
        abort(404, f"Evento con ID {event_id} non trovato.")

    errors = validate_event_data(data)

    if errors:
        abort(400, errors)

    # Aggiornamento dell'evento esistente
    success = update_event(event_id, data)
    if not success:
        abort(
            500,
            f"EErrore durante l'aggiornamento dell'evento con ID {event_id}. Riprova più tardi.",
        )

    return "", 204

@events_controller.route("/api/events/stats", methods=["GET"])
def api_get_event_stats():
    stats = get_event_stats()
    return jsonify(stats), 200


@events_controller.route("/api/events/<int:event_id>", methods=["PATCH"])
def api_update_partial_event(event_id):
    if event_id <= 0:
        abort(400, "ID deve essere un intero positivo.")

    data = request.json
    if not data:
        abort(400, "Dati JSON mancanti.")

    if get_event_by_id(event_id) is None:
        abort(404, f"Evento con ID {event_id} non trovato.")

    errors = validate_partial_event_data(data)

    if errors:
        abort(400, errors)

    # Aggiornamento dell'automobile esistente
    success = update_event(event_id, data)
    if not success:
        abort(
            500,
            f"EErrore durante l'aggiornamento delll'evento con ID {event_id}. Riprova più tardi.",
        )

    return "", 204


def validate_event_data(data):
    errors = []

    if (
        "titolo" not in data
        or not isinstance(data["titolo"], str)
        or len(data["titolo"].strip()) < 5
    ):
        errors.append(
            "Il campo 'titolo' è obbligatorio e deve essere una stringa di almeno 5 caratteri."
        )

    # descrizione: obbligatoria, max 250 caratteri
    if (
        "descrizione" not in data
        or not isinstance(data["descrizione"], str)
        or len(data["descrizione"].strip()) > 250
    ):
        errors.append(
            "Il campo 'descrizione' è obbligatorio e deve essere una stringa di massimo 250 caratteri."
        )

    # categoria: whitelist
    if (
        "categoria" not in data
        or not isinstance(data["categoria"], str)
        or data["categoria"].strip() not in CATEGORIE_VALIDE
    ):
        errors.append(
            f"Il campo 'categoria' è obbligatorio e deve essere uno tra: {', '.join(CATEGORIE_VALIDE)}."
        )

    # data_evento: formato YYYY-MM-DD
    if "data_evento" not in data or not isinstance(data["data_evento"], str):
        errors.append(
            "Il campo 'data_evento' è obbligatorio e deve avere formato YYYY-MM-DD."
        )
    else:
        try:
            datetime.strptime(data["data_evento"], "%Y-%m-%d")
        except ValueError:
            errors.append(
                "Il campo 'data_evento' deve avere formato YYYY-MM-DD."
            )

    # prezzo: decimale >= 0.00
    if "prezzo" not in data:
        errors.append(
            "Il campo 'prezzo' è obbligatorio e deve essere un numero maggiore o uguale a 0.00."
        )
    else:
        try:
            prezzo = float(str(data["prezzo"]))

            if prezzo < float("0.00"):
                errors.append(
                    "Il campo 'prezzo' deve essere maggiore o uguale a 0.00."
                )
        except (ValueError, TypeError):
            errors.append(
                "Il campo 'prezzo' deve essere un numero decimale valido."
            )

    # posti_disponibili: intero >= 0
    if (
        "posti_disponibili" not in data
        or not isinstance(data["posti_disponibili"], int)
        or isinstance(data["posti_disponibili"], bool)
        or data["posti_disponibili"] < 0
    ):
        errors.append(
            "Il campo 'posti_disponibili' è obbligatorio e deve essere un intero maggiore o uguale a 0."
        )

    # in_evidenza: booleano
    if "in_evidenza" not in data or not isinstance(data["in_evidenza"], bool):
        errors.append(
            "Il campo 'in_evidenza' è obbligatorio e deve essere un booleano."
        )

    return errors


def validate_partial_event_data(data):
    errors = []

    if "titolo" in data and (
        not isinstance(data["titolo"], str)
        or len(data["titolo"].strip()) < 5
    ):
        errors.append(
            "Il campo 'titolo' deve essere una stringa di almeno 5 caratteri."
        )

    if "descrizione" in data and (
        not isinstance(data["descrizione"], str)
        or len(data["descrizione"].strip()) > 250
    ):
        errors.append(
            "Il campo 'descrizione' deve essere una stringa di massimo 250 caratteri."
        )

    if "categoria" in data and (
        not isinstance(data["categoria"], str)
        or data["categoria"].strip() not in CATEGORIE_VALIDE
    ):
        errors.append(
            f"Il campo 'categoria' deve essere uno tra: {', '.join(CATEGORIE_VALIDE)}."
        )

    if "data_evento" in data:
        if not isinstance(data["data_evento"], str):
            errors.append(
                "Il campo 'data_evento' deve avere formato YYYY-MM-DD."
            )
        else:
            try:
                datetime.strptime(data["data_evento"], "%Y-%m-%d")
            except ValueError:
                errors.append(
                    "Il campo 'data_evento' deve avere formato YYYY-MM-DD."
                )

    if "prezzo" in data:
        try:
            prezzo = float(str(data["prezzo"]))

            if prezzo < float("0.00"):
                errors.append(
                    "Il campo 'prezzo' deve essere maggiore o uguale a 0.00."
                )
        except (ValueError, TypeError):
            errors.append(
                "Il campo 'prezzo' deve essere un numero decimale valido."
            )

    if "posti_disponibili" in data and (
        not isinstance(data["posti_disponibili"], int)
        or isinstance(data["posti_disponibili"], bool)
        or data["posti_disponibili"] < 0
    ):
        errors.append(
            "Il campo 'posti_disponibili' deve essere un intero maggiore o uguale a 0."
        )

    if "in_evidenza" in data and not isinstance(data["in_evidenza"], bool):
        errors.append(
            "Il campo 'in_evidenza' deve essere un booleano."
        )

    return errors