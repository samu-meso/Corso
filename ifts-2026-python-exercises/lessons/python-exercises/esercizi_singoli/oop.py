class Car:  # or Car() or Car(object)
    # Instance constructor (or initializator)
    def __init__(self, brand, model, speed=0):
        # instance attributes
        self.brand = brand
        self.model = model
        self.speed = speed

    # Instance methods
    def speed_up(self, step=1):
        self.speed += step

    def speed_down(self, step=1):
        self.speed -= step


# The following line defines the program main
if __name__ == '__main__':
    m3 = Car('BMW', 'M3', 70)
    print('Brand={}, Model={}, Speed={}'.format(m3.brand, m3.model, m3.speed))

    punto = Car('Fiat', 'Punto')
    print('Brand={}, Model={}, Speed={}'.format(punto.brand, punto.model, punto.speed))

    print(id(m3), type(m3))
    print(id(punto), type(punto))