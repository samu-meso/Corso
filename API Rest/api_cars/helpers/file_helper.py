# helpers/file_helper.py
import json
from typing import Any
from decimal import Decimal


class DecimalEncoder(json.JSONEncoder):
    """Gestisce la serializzazione di valori Decimal in float."""
    def default(self, obj: Any):
        if isinstance(obj, Decimal):
            return float(obj)
        return super().default(obj)


def save_to_file(data: Any, filename: str) -> None:
    """Salva i dati su file in formato JSON."""
    with open(filename, 'w') as f:
        json.dump(data, f, cls=DecimalEncoder, indent=4)


def load_from_file(filename: str) -> Any:
    """Carica e restituisce i dati da un file JSON."""
    with open(filename, 'r') as f:
        return json.load(f)
