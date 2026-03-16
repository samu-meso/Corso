exit_string = 'end'
continue_asking = True
collected_numbers = []

while continue_asking:
    user_input = input("Inserisci un numero; scrivi 'end' per continuare: ")
    # print(type(user_input), "", user_input != str('end'))
    if user_input.isdigit():
        collected_numbers.append(int(user_input))
    elif user_input == 'end':
        continue_asking = False
            q
    else:
        print("Non valido.")

