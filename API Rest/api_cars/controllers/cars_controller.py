from datetime import datetime
from flask import Blueprint, abort, jsonify, request
from helpers.fake_database import get_all_cars, get_car_by_id, add_car, update_car
from models.car import Car

cars_controller = Blueprint('cars_controller', __name__)

@cars_controller.route('/api/auto', methods=['GET'])
def ep_get_all_cars():
    return jsonify([car.serialize() for car in get_all_cars()]),200


@cars_controller.route('/api/auto/<int:car_id>', methods=['GET'])
def ep_get_car_by_id(car_id):
    if car_id <= 0:
        abort(400, "ID deve essere un intero positivo.")
    car = get_car_by_id(car_id)
    if car is None:
        abort(404, f"Automobile con ID {car_id} non trovata.")
    return jsonify(car.serialize()), 200

@cars_controller.route('/api/auto', methods=['POST'])
def ep_add_car():
    data = request.json
    if not data:
        abort(400, "Dati JSON mancanti.")
        
    errors = validate_car_data(data)
        
    if errors:
        abort(400, errors)
        
    #Creazione della nuova automobile
    new_car = Car(0, data['marca'].strip(), data['modello'].strip(), int(data['anno']), float(data['prezzo']))
    new_car.id = add_car(new_car)     # Genera un ID unico per la nuova automobile
    if new_car.id is None:
        return abort(500, "An error occurred while saving the car. Try again later.")
    return jsonify({"id": new_car.id}), 201


@cars_controller.route('/api/auto/<int:car_id>', methods=['PUT'])
def ep_update_car(car_id):
    if car_id <= 0:
        abort(400, "ID deve essere un intero positivo.")
    
    data = request.json
    if not data:
        abort(400, "Dati JSON mancanti.")
        
    if get_car_by_id(car_id) is None:
        abort(404, f"Automobile con ID {car_id} non trovata.")
        
    errors = validate_car_data(data)
        
    if errors:
        abort(400, errors)
    
    #Aggiornamento dell'automobile esistente    
    success = update_car(car_id, data)
    if not success:
        abort(500, f"EErrore durante l'aggiornamento dell'automobile con ID {car_id}. Riprova più tardi.")
    
    return "", 204



@cars_controller.route('/api/auto/<int:car_id>', methods=['PATCH'])
def ep_update_partial_car(car_id):
    if car_id <= 0:
        abort(400, "ID deve essere un intero positivo.")
    
    data = request.json
    if not data:
        abort(400, "Dati JSON mancanti.")
        
    if get_car_by_id(car_id) is None:
        abort(404, f"Automobile con ID {car_id} non trovata.")
        
    errors = validate_partial_car_data(data)
        
    if errors:
        abort(400, errors)
    
    #Aggiornamento dell'automobile esistente    
    success = update_car(car_id, data)
    if not success:
        abort(500, f"EErrore durante l'aggiornamento dell'automobile con ID {car_id}. Riprova più tardi.")
    
    return "", 204
        
        
        

def validate_car_data(data):
    errors = []
    if 'marca' not in data or len(data['marca'].strip()) < 2:
        errors.append("Il campo 'marca' è obbligatorio e deve essere una stringa di almeno 2 caratteri.")
    if 'modello' not in data or len(data['modello'].strip()) < 3:
        errors.append("Il campo 'modello' è obbligatorio e deve essere una stringa di almeno 3 caratteri.")
    current_year = datetime.now().year
    if 'anno' not in data or not isinstance(data['anno'], int) or data['anno'] < 1886 or data['anno'] > current_year + 1:
        errors.append(f"Il campo 'anno' è obbligatorio e deve essere un intero compreso tra 1886 e {current_year + 1}.")
    if 'prezzo' not in data or not isinstance(data['prezzo'], (int, float)) or data['prezzo'] <= 0.01:
        errors.append("Il campo 'prezzo' è obbligatorio e deve essere un numero positivo.")
    return errors
        
def validate_partial_car_data(data):
    errors = []
    if 'marca' in data and len(data['marca'].strip()) < 2:
        errors.append("Il campo 'marca' deve essere una stringa di almeno 2 caratteri.")
    if 'modello' in data and len(data['modello'].strip()) < 3:
        errors.append("Il campo 'modello' deve essere una stringa di almeno 3 caratteri.")
    current_year = datetime.now().year
    if 'anno' in data and (not isinstance(data['anno'], int) or data['anno'] < 1886 or data['anno'] > current_year + 1):
        errors.append(f"Il campo 'anno' deve essere un intero compreso tra 1886 e {current_year + 1}.")
    if 'prezzo' in data and (not isinstance(data['prezzo'], (int, float)) or data['prezzo'] <= 0.01):
        errors.append("Il campo 'prezzo' deve essere un numero positivo.")
    return errors
    