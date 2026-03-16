from datetime import datetime

from carDealer.Car import Car


class Warehouse(object):
    __warehouse_instance = None
    __initialized = False

    def __new__(cls, *args, **kwargs):
        if cls.__warehouse_instance is not None:
            return cls.__warehouse_instance
        else:
            instance = super().__new__(cls)
            cls.__warehouse_instance = instance
            return instance

    def __init__(self, name: str, address: str, phone_number: str):
        if not self.__initialized:
            if not isinstance(name, str):
                raise TypeError(f'The warehouse name must be a str; received {name} '
                                f'of type {type(name)}')
            if not isinstance(address, str):
                raise TypeError(f'The address name must be a str; received {address} '
                                f'of type {type(address)}')
            if not isinstance(phone_number, str):
                raise TypeError(f'The phone number name must be a str; received {phone_number} '
                                f'of type {type(phone_number)}')
            self._name = name
            self._address = address
            self._phone_number = phone_number
            self._cars_collection = []
            self.__initialized = True
        else:
            print('An instance of the class Warehouse already exists!')

    def __str__(self):
        cars_list = ''
        for car in self.cars_collection:
            cars_list += car.__str__()
        return f'Warehouse: {self.name}, Cars: [{cars_list}]'

    # Getters
    @property
    def name(self):
        return self._name

    @property
    def address(self):
        return self._address

    @property
    def phone_number(self):
        return self._phone_number

    @property
    def cars_collection(self):
        return self._cars_collection

    # Setters

    @name.setter
    def name(self, value):
        if not isinstance(value, str):
            raise TypeError(f"The warehouse name must be a str; received {value} of type {type(value)}")
        self._name = value

    @address.setter
    def address(self, value):
        if not isinstance(value, str):
            raise TypeError(f"The address name must be a str; received {value} of type {type(value)}")
        self._address = value

    @phone_number.setter
    def phone_number(self, value):
        if not isinstance(value, str):
            raise TypeError(f"The phone number name must be a str; received {value} of type {type(value)}")
        self._phone_number = value

    def load_cars_from_file(self, path='../db/cars_warehouse.txt'):
        try:
            with open(path, 'r', encoding='utf-8') as file:
                car_info = dict()
                for line in file:
                    if line == "***\n" or line == "***":
                        new_car = Car(
                            car_info['brand'],
                            car_info['model'],
                            car_info['color'],
                            int(car_info['year']),
                            car_info['accessories'],
                            int(car_info['km']),
                            car_info['license_plate'],
                            car_info['vin'],
                            int(car_info['price']),
                            car_info['currency'],
                        )
                        self._cars_collection.append(new_car)
                        car_info = dict()
                        print("New car added to the list")
                    else:
                        line_info = line.split(":")
                        line_info[0] = line_info[0].strip()
                        line_info[1] = line_info[1].strip()
                        if line_info[0] == "accessories":
                            accessories_list = line_info[1].split(",")
                            line_info[1].strip()
                            for i in range(len(accessories_list)):
                                accessories_list[i] = accessories_list[i].strip()
                            car_info[line_info[0]] = set(accessories_list)
                        else:
                            car_info[line_info[0]] = line_info[1]
        except FileNotFoundError as e:
            print("Errore: file non trovato!\n", e)

    def save_car_in_file(self, car_to_save: Car, path='../db/cars_warehouse.txt'):
        self._cars_collection.append(car_to_save)
        try:
            with open(path, 'a', encoding='utf-8') as file:
                accessories_str = ''
                accessories_list =  list(car_to_save.accessories)
                for i in range(len(accessories_list)):
                    if i+1 == len(accessories_list):
                        accessories_str += accessories_list[i]
                    else:
                        accessories_str += accessories_list[i]
                        accessories_str += ', '
                # car_values = {
                #     'brand': car_to_save.brand,
                #     'model': car_to_save.model,
                #     'color': car_to_save.color,
                #     'year': car_to_save.year,
                #     'accessories': accessories_str, # TODO: save it as a set of string
                #     'km': car_to_save.km,
                #     'license_plate': car_to_save.license_plate,
                #     'vin': car_to_save.vin,
                #     'price': car_to_save.price[:-1],
                #     'currency': car_to_save.price[-1:]
                # }
                # for key in car_values:
                #     file.write(key + ': ' + car_values[key])

                list_to_write = [
                    '\n'
                    'brand: ' + str(car_to_save.brand) + '\n',
                    'model: ' + str(car_to_save.model) + '\n',
                    'color: ' + str(car_to_save.color) + '\n',
                    'year: ' + str(car_to_save.year) + '\n',
                    'accessories: ' + accessories_str + '\n',
                    'km: ' + str(car_to_save.km) + '\n',
                    'license_plate: ' + str(car_to_save.license_plate) + '\n',
                    'vin: ' + str(car_to_save.vin) + '\n',
                    'price: ' + str(car_to_save.price[:-1]) + '\n',
                    'currency: ' + str(car_to_save.price[-1:]) + '\n***'
                ]
                file.writelines(list_to_write)
                print("Car written successfully!")
        except FileNotFoundError as e:
            print("Errore: file non trovato!\n", e)

    def average_value(self):
        value: int = 0
        for car in self.cars_collection:
            value += int(car.price[:-1])
        average = value / len(self.cars_collection)
        return average

    def average_km(self):
        value: int = 0
        for car in self.cars_collection:
            value += int(car.km)
        average = value / len(self.cars_collection)
        return average

    def average_age(self):
        age: int = 0
        for car in self.cars_collection:
            car_age = datetime.now().year - car.year
            age += car_age
        average = age / len(self.cars_collection)
        return average

    def write_info_to_file(self, path='../db/averages.txt'):
        try:
            with open(path, 'w', encoding='utf-8') as file:
                    file.write('averagePrice: ' + str(self.average_value()) + '\n')
                    file.write('averageAge: ' + str(self.average_age()) + '\n')
                    file.write('averageKm: ' + str(self.average_km()) + '\n')
        except FileNotFoundError as e:
            print("Errore: file non trovato!\n", e)