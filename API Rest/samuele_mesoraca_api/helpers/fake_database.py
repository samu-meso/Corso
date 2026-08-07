# fake_database.py
from typing import List, Optional
from models.events import Event
from helpers.file_helper import save_to_file, load_from_file
from datetime import datetime, date

FILENAME = 'events.json'

# Lista in memoria — simulazione del database
events_db: List[Event] = []


def load_events():
    """Carica gli eventi dal file JSON. Se il file non esiste, crea i dati di default."""
    global events_db
    try:
        data = load_from_file(FILENAME)
        events_db = [Event(**c) for c in data]
    except FileNotFoundError:
        raise FileNotFoundError(f"Il file {FILENAME} non esiste. Creare il file con i dati di default.")
    
def get_all_events() -> List[Event]:
    """Restituisce tutti gli eventi."""
    return events_db

def get_event_by_id(event_id: int) -> Optional[Event]:
    """Restituisce un evento per ID, o null se non viene trovata."""
    return next((event for event in events_db if event.id == event_id), None)


def save_events():
    """Salva tutti gli eventi su file JSON."""
    data = [c.serialize_db() for c in events_db]
    save_to_file(data, FILENAME)
    
def add_event(event: Event) -> Optional[int]:
    new_id = max(c.id for c in events_db) + 1 if events_db else 1
    event.id = new_id
    events_db.append(event)
    try:
        save_events()  # Salva su file dopo l'aggiunta
    except Exception as e:        
        print(f"Errore durante il salvataggio degli eventi: {e}")
        return None
    return event.id

def update_event(event_id: int, data_event: dict) -> bool:
    existing_event = get_event_by_id(event_id)

    if existing_event is None:
        return False

    if "titolo" in data_event:
        existing_event.title = data_event["titolo"].strip()

    if "descrizione" in data_event:
        existing_event.desc = data_event["descrizione"].strip()

    if "categoria" in data_event:
        existing_event.category = data_event["categoria"].strip()

    if "data_evento" in data_event:
        existing_event.date = datetime.strptime(
            data_event["data_evento"],
            "%Y-%m-%d"
        ).date()

    if "prezzo" in data_event:
        existing_event.price = float(data_event["prezzo"])

    if "posti_disponibili" in data_event:
        existing_event.available_places = int(data_event["posti_disponibili"])

    if "in_evidenza" in data_event:
        existing_event.highlight = data_event["in_evidenza"]

    try:
        save_events()
    except Exception as e:
        print(f"Errore durante il salvataggio degli eventi: {e}")
        return False

    return True

    
def get_event_stats():
    if len(events_db) == 0:
        return {
            "posti_totali": 0,
            "prezzo_medio": 0.00
        }

    posti_totali = sum(event.available_places for event in events_db)
    prezzo_medio = sum(event.price for event in events_db) / len(events_db)

    return {
        "posti_totali": posti_totali,
        "prezzo_medio": round(prezzo_medio, 2)
    }
    
def search_events(keyword):
    keyword = keyword.lower().strip()

    results = []

    for event in events_db:
        title = event.title.lower()
        desc = event.desc.lower()

        if keyword in title or keyword in desc:
            results.append(event)

    return results


def delete_event(event_id: int) -> bool:
    global events_db

    existing_event = get_event_by_id(event_id)

    if existing_event is None:
        return False

    events_db = [
        event for event in events_db
        if event.id != event_id
    ]

    try:
        save_events()
    except Exception as e:
        print(f"Errore durante il salvataggio degli eventi: {e}")
        return False

    return True