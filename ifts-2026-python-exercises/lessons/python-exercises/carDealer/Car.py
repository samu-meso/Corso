class Car(object):
    _next_id: int = 1000

    def __init__(
            self,
            brand: str,
            model: str,
            color: str,
            year: int,
            accessories: set,
            km: int,
            license_plate: str,
            vin: str,
            price: int,
            currency: str
    ) -> None:
        if not isinstance(brand, str):
            raise TypeError(f"The car brand must be of type string: received type: {type(brand)}")
        if not isinstance(model, str):
            raise TypeError(f"The car model must be of type string: received type: {type(model)}")
        if not isinstance(color, str):
            raise TypeError(f"The car color must be of type string: received type: {type(color)}")
        if not isinstance(year, int):
            raise TypeError(f"The car year must be of type int: received type: {type(year)}")
        if year < 1900 or year > 2026:
            raise TypeError(f"The car year must greater than 1900 and lower than 2026; "
                            f"received year is :{type(year)}")
        if not isinstance(accessories, set):
            raise TypeError(f"Car accessories must be of type set: received type: "
                            f"{type(accessories)}")
        if not isinstance(km, int) or km < 0:
            raise TypeError(f"Car km must be a positive int; received {km} of type int: received type: {type(km)}")
        if not isinstance(license_plate, str):
            raise TypeError(f"Car license plate must be type str; received type: "
                            f"{type(license_plate)}")
        if not self.is_license_plate_valid(license_plate):
                raise ValueError("License plate must be LLNNNLL")
        if not isinstance(vin, str):
            raise TypeError(f"Car VIN must be type str; received type: "
                            f"{type(vin)}")
        # TODO: Add VIN consistency check
        if not isinstance(price, int) or price < 0:
            raise TypeError(f"Car price must be a positive int; received {price} of type "
                            f"{type(price)}")
        # TODO: Add Euro symbol consistency check
        if not isinstance(currency, str) or currency != '€':
            raise TypeError(f"Car price currency must be the € symbol; received {currency} symbol "
                            f"of type {type(price)}")
        self._id = Car._get_next_id()
        Car._update_id()
        self._brand = brand
        self._model = model
        self._color = color
        self._year = year
        self._accessories = accessories
        self._km = km
        self._license_plate = license_plate
        self._vin = vin
        self._price = price
        self._currency = currency

    # Getters
    @property
    def id(self):
        return self._id

    @property
    def brand(self):
        return self._brand

    @property
    def model(self):
        return self._model

    @property
    def color(self):
        return self._color

    @property
    def year(self):
        return self._year

    @property
    def accessories(self):
        return self._accessories

    @property
    def km(self):
        return self._km

    @property
    def license_plate(self):
        return self._license_plate

    @property
    def vin(self):
        return self._vin

    @property
    def price(self):
        """Return the price of the car and the associated currency as a string"""
        return str(self._price) + self._currency

    @classmethod
    def _get_next_id(cls):
        return cls._next_id

    @accessories.setter
    def accessories(self, value: str, remove=False):
        if not isinstance(value, str):
            raise TypeError(f"The accessory must be a string; received {value} of type "
                            f"{type(value)}")
        if not remove:
            self._accessories.add(value)
            print(f"Accessory {value} correctly added!")
        if remove:
            self._accessories.remove(value)
            print(f"Accessory {value} correctly removed!")

    @price.setter
    def price(self, value: int):
        if not isinstance(value, int):
            raise TypeError(f"Price must be a positive int; received: {value} of type {type(value)}")
        self._price = value

    @license_plate.setter
    def license_plate(self, value):
        if not isinstance(value ,str):
            print(f"The license plate must be of type str; received {value} of type "
                  f"{type(value)}")
        if not self.is_license_plate_valid(value):
            raise ValueError("License plate must be LLNNNLL")
        self._license_plate = value

    @classmethod
    def _update_id(cls):
        cls._next_id += 1

    @staticmethod
    def is_license_plate_valid(new_license_plate: str) -> bool:
        if len(new_license_plate) != 7:
            return False
        if not all(x.isalpha() for x in new_license_plate[0:2]):
            return False
        if not all(x.isalpha() for x in new_license_plate[-2:]):
            return False
        if not all(x.isnumeric() for x in new_license_plate[2:5]):
            return False
        return True
