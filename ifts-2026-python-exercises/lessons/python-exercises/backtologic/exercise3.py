"""
Esercizio 3 — Massimo manuale

Input: lista di numeri

Output: valore massimo

Vincoli:

niente max()

confronti espliciti
"""

stop = True
numbers_list = []
maxNumber = 0

while stop:
    x = input("Inserisci un numero ('stop' per terminare):")
    try:
        if str(x) == "stop":
            break
        else:
            numbers_list.append(int(x))
    except ValueError:
        print("Numero non valido.")


if numbers_list:
    maxNumber = numbers_list[0]

    for numero in numbers_list:
        if numero > maxNumber:
            maxNumber = numero

    print("Programma terminato, numero massimo: ", maxNumber)
else:
    print("Programma terminato, nessun numero inserito!")