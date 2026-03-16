from carDealer.Warehouse import Warehouse


class Menu(object):
    __menu_instance = None
    __initialized = False

    def __new__(cls, *args, **kwargs):
        if cls.__menu_instance is not None:
            return cls.__menu_instance
        else:
            instance = super().__new__(cls)
            cls.__menu_instance = instance
            return instance

    def __init__(self):
        self.system_warehouse = Warehouse('Il paradiso del pistone',
                             'Via del Carburante 10',
                             "+39 399 123456")
        self.options = {
            0: 'exit',
            1:  'load warehouse from file',
            2: 'add car to warehouse',
            3: 'calculate average warehouse value',
            4: 'calculate average cars km',
            5: 'calculate average warehouse age',
            6: 'Modify warehouse name',
            7: 'Modify warehouse address',
            8: 'Modify warehouse phone number',
        }
        self.run = True

    def run_menu(self):
        print(f"### Welcome to the {self.system_warehouse.name} Management System ###")
        while self.run:
            print(self.options)
            print("Select a menu option")
            selected_option = input("--> ")
            if selected_option not in self.options.keys():
                pass
