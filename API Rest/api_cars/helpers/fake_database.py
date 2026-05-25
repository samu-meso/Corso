# fake_database.py
from typing import List, Optional
from models.car import Car
from helpers.file_helper import save_to_file, load_from_file

FILENAME = 'cars.json'

# Lista in memoria — simulazione del database
cars_db: List[Car] = []


def load_cars():
    """Carica i automobili dal file JSON. Se il file non esiste, crea i dati di default."""
    global cars_db
    try:
        data = load_from_file(FILENAME)
        cars_db = [Car(**c) for c in data]
    except FileNotFoundError:
        raise FileNotFoundError(f"Il file {FILENAME} non esiste. Creare il file con i dati di default.")
    
def get_all_cars() -> List[Car]:
    """Restituisce tutte le automobili."""
    return cars_db

def get_car_by_id(car_id: int) -> Optional[Car]:
    """Restituisce un'automobile per ID, o None se non trovata."""
    return next((car for car in cars_db if car.id == car_id), None)


def save_cars():
    """Salva tutte le automobili su file JSON."""
    data = [c.serialize_db() for c in cars_db]
    save_to_file(data, FILENAME)
    
def add_car(car: Car) -> Optional[int]:
    new_id = max(c.id for c in cars_db) + 1 if cars_db else 1
    car.id = new_id
    cars_db.append(car)
    try:
        save_cars()  # Salva su file dopo l'aggiunta
    except Exception as e:        
        print(f"Errore durante il salvataggio delle automobili: {e}")
        return None
    return car.id

def update_car(car_id: int, data_car:dict) -> bool:
    existing_car = get_car_by_id(car_id)
    if existing_car is None:
        return False
    existing_car.brand = data_car.get('marca', existing_car.brand)
    existing_car.model = data_car.get('modello', existing_car.model)
    existing_car.year = data_car.get('anno', existing_car.year)
    existing_car.price = data_car.get('prezzo', existing_car.price)
    try:
        save_cars()  # Salva su file dopo l'aggiornamento
    except Exception as e:        
        print(f"Errore durante il salvataggio delle automobili: {e}")
        return False
    return True

    
