
from werkzeug.exceptions import HTTPException
from flask import Flask, jsonify, render_template
from controllers.cars_controller import cars_controller
from helpers.log_helper import log_exception, log_info


app = Flask(__name__)
app.register_blueprint(cars_controller)


@app.errorhandler(HTTPException)
def handle_http_exception(e):
    """Converte tutte le eccezioni HTTP in risposte JSON uniformi."""
    log_exception(e)  # Logga l'eccezione completa
    return jsonify({
        'error': e.name,
        'message': e.description,
        'status_code': e.code
    }), e.code

@app.errorhandler(Exception)
def handle_generic_exception(e):
    """Gestisce errori imprevisti."""
    log_exception(e)  # Logga l'eccezione completa
    return jsonify({
        'error': 'Internal Server Error',
        'message': 'Si è verificato un errore imprevisto.',
        'status_code': 500
    }), 500


@app.route('/')
def home():
    return render_template('client_cars.html')


if __name__ == '__main__':
    from helpers.fake_database import load_cars
    load_cars()
    log_info("Applicazione avviata. Automobili caricate con successo.")
    
    # Test: stampa tutte le automobili caricate
    #from helpers.fake_database import get_all_cars
    # for car in get_all_cars():
    #     print(car)
    
    app.run(debug=True, port=5002)