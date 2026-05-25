import logging

logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s %(levelname)s %(message)s',
    handlers=[
        logging.FileHandler('app.log'),
        logging.StreamHandler()  # Mostra anche in console
    ]
)


def log_info(message: str):
    """Logga un messaggio di livello INFO."""
    print(f"INFO: {message}")  # Stampa in console per visibilità immediata
    logging.info(message)
    
def log_warning(message: str):
    """Logga un messaggio di livello WARNING."""
    logging.warning(message)
    
def log_error(message: str):
    """Logga un messaggio di livello ERROR."""
    logging.error(message)
    
def log_exception(ex: Exception):
    """Logga un'eccezione con il traceback completo."""
    logging.error("Eccezione catturata", exc_info=ex)

def log_debug(message: str):
    """Logga un messaggio di livello DEBUG."""
    logging.debug(message)

