from datetime import date, datetime


class Event:
    def __init__(
        self,
        id: int,
        titolo: str,
        descrizione: str,
        categoria: str,
        data_evento,
        prezzo: float,
        posti_disponibili: int,
        in_evidenza: bool,
    ):
        self.id = id
        self.title = titolo
        self.desc = descrizione
        self.category = categoria

        if isinstance(data_evento, str):
            self.date = datetime.strptime(data_evento, "%Y-%m-%d").date()
        elif isinstance(data_evento, date):
            self.date = data_evento
        else:
            raise ValueError("data_evento deve essere una stringa YYYY-MM-DD o un oggetto date")

        self.price = prezzo
        self.available_places = posti_disponibili
        self.highlight = in_evidenza


    def __repr__(self):
        return (
            f"Event("
            f"id={self.id!r}, "
            f"title={self.title!r}, "
            f"desc={self.desc!r}, "
            f"category={self.category!r}, "
            f"date={self.date!r}, "
            f"price={self.price!r}, "
            f"available_places={self.available_places!r}, "
            f"highlight={self.highlight!r}"
            f")"
        )
    def serialize(self):
        return {
            "id": self.id,
            "titolo": self.title,
            "descrizione": self.desc,
            "categoria": self.category,
            "data_evento": self.date.isoformat() if self.date else None,
            "prezzo": self.price,
            "posti_disponibili": self.available_places,
            "in_evidenza": self.highlight
        }

    def serialize_db(self):
        return {
            "id": self.id,
            "titolo": self.title,
            "descrizione": self.desc,
            "categoria": self.category,
            "data_evento": self.date.isoformat() if self.date else None,
            "prezzo": self.price,
            "posti_disponibili": self.available_places,
            "in_evidenza": self.highlight
        }
