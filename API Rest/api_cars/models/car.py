class Car:
    def __init__(self, id: int, brand: str, model: str, year: int, price: float):
        self.id = id
        self.brand = brand
        self.model = model
        self.year = year
        self.price = price

    def __repr__(self):
        return f"Car(id={self.id}, brand='{self.brand}', model='{self.model}', year={self.year}, price={self.price})"

    def serialize(self):
        return {
            'id': self.id,
            'marca': self.brand,
            'modello': self.model,
            'anno': self.year,
            'prezzo': self.price
        }       
        
    def serialize_db(self):
        return {
            'id': self.id,
            'brand': self.brand,
            'model': self.model,
            'year': self.year,
            'price': self.price
        }       
        