# my_dict = {'name': 'John', 'surname': 'Doe', 'year': 1984}
# for k, v in my_dict.items():
#     print(k, v)
#
# myList = []
# numbersAmount = input("Inserisci quanti numeri vuoi inserire: ")
# for i in range(int(numbersAmount)):
#     x = int(input(f"Inserisci il {i} numero: "))
#     myList.append(x)
#     myList.sort()
# print(myList)
import math
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
    else:
        print("Non valido.")

print("reverse:", collected_numbers[::-1])
collected_numbers.reverse()
print("func reverse: ", collected_numbers)

# print(list1)3


# """
# Store a series of numbers taken from the user's input.
# Print the series in the order in which they were entered and in reverse order.
# """
from winreg import ExpandEnvironmentStrings
#
# exit_string = 'end'
# continue_asking = True
# collected_numbers = []
#
# while continue_asking:
    ## Solution 1
    #user_input = input('Insert a number; write "end" to continue: ')
    ## Control if it is an 'end'; if not, throw error
    #if user_input=='end':
    #    # End of the cycle, change the boolean variable and terminate the while loop
    #    continue_asking = False
    #else:
    #    # Control if the conversion is feasible; if not, throw error
    #    # Convert the input and append to the collection
    #    try:
    #        collected_numbers.append(int(user_input))
    #    except Exception as e:
    #        print(e)
    #        print('The inserted value is illegal.')

    # Solution 2
#     user_input = input('Insert a number; write "end" to continue: ')
#     print('Is digit?', user_input.isdigit())
#     # Control if it is an 'end'; if not, throw error
#     if user_input.isdigit():
#         collected_numbers.append(int(user_input))
#     elif user_input == 'end':
#         continue_asking = False
#     else:
#         raise Exception('The inserted value is illegal')
#
# print('Inserted numbers are:', collected_numbers)
#
# collected_numbers.reverse()
# print('The reversed collections is:', collected_numbers)