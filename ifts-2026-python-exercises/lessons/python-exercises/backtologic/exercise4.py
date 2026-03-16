"""
4. Somma selettiva

Input: lista numeri
Output: somma solo dei numeri positivi
"""
numbers_list = []
somma = 0

while True:
    x = input("Inserisci un numero ('stop' per terminare):")
    try:
        if x == "stop":
            break
        try:
            numero = int(x)
            if numero > 0:
                somma += numero
        except ValueError:
            print("Numero non valido.")
    except ValueError:
        print("Numero non valido.")


print("Somma: ", somma)