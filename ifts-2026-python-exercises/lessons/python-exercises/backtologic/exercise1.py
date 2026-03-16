"""
Esercizio 1 — Filtro base

Input: lista di numeri
Output: solo numeri pari e > 10

Vincoli:
ciclo
if

"""

numbers = []
stop = True
counter = 0


while stop:
    x = input("Inserisci il numero (<0 o 'stop' per finire):")

    try:
        if x == str("stop") or (int(x) < 0):
            stop = False
        if int(x) >= 0:
            numbers.append(int(x))
    except ValueError:
        if x != str("stop") and int(x) < 0:
            print("Numero non valido (<0 o str)")

for value in numbers:
    # print(value % 2 == 0, value > 10)
    if value % 2 == 1 or value < 10:
        numbers.remove(value)

print(numbers)


