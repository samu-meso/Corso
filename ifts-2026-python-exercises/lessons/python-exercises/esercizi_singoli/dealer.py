"""
brand: Toyota
model: Yaris
color: metallic grey
year: 2017
accessories: antifog lights, apple car play, rear camera
license_plate: none
vin: 1234567

brand: Opel
model: corsa
color: black white
year: 2020
accessories: antifog lights, rear camera, level 2 adas
license_plate: AB123YZ
vin: 7654321
"""

class Dealer:

    def __init__(self, brand, model, color, year, accessories, license, vin):
        # instance attributes
        self.brand = brand
        self.model = model
        self.color = color
        self.year = year
        self.accessories = accessories
        self.license_plate = license
        self.vin = vin

if __name__ == '__main__':
    Toyota = Dealer("Toyota", "Yaris", "Metallic Gray", 2017, "antifog lights, apple car play, rear camera", None, 1234567)
    print("Brand={}, Model={}, Color={}, Year={}, Accessories={}, License plate={}, VIN={}".format(
        Toyota.brand,
        Toyota.model,
        Toyota.color,
        Toyota.year,
        Toyota.accessories,
        Toyota.license_plate,
        Toyota.vin
    ))
    Opel = Dealer("Opel", "Corsa", "Black White", 2020, "antifog lights, rear camera, level 2 adas", "AB123YZ", 7654321)
    print("Brand={}, Model={}, Color={}, Year={}, Accessories={}, License plate={}, VIN={}".format(
        Opel.brand,
        Opel.model,
        Opel.color,
        Opel.year,
        Opel.accessories,
        Opel.license_plate,
        Opel.vin
    ))