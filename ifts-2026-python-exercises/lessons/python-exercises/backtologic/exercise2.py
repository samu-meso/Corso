"""
Esercizio 2 — Contatore condizionato

Input: lista di età

Output: quante sono:
< 18
≥ 18

Vincoli:
due contatori
un solo ciclo
"""
stop = True
adults = 0
minors = 0

while stop:
    x = input("Inserisci l'età (-1 o 'stop' per terminare):")
    try:
        if x == str("stop") or int(x) < 0:
            break
        if 18 > int(x) >= 1:
            minors += 1
        elif int(x) >= 18:
            adults += 1
    except ValueError:
        if x != str("stop"):
            print("sono nell'except")
            print("Numero non valido.")
print("Adults: ", adults, " Minors: ", minors)
