# my_list = ['a', 'b', 'c', 'd', 'e', 'f']
# #         #   0    1    2    3    4    5
# #         #   -6  -5   -4   -3   -2   -1
# # print(my_list[1:5]) # Prende dall'elemento 1 fino al 4 se voglio tutto non specifico punto d'arrivo
# # print(my_list[:]) # Prende tutto senza punto d'arrivo
# # print(my_list[:5]) # Prende dall'elemento 0 fino al 4
# # print(my_list[3:]) # Prende dal terzo elemento in poi
# # print(my_list[::2]) # Prende ogni due elementi
# #
# # ###
# #
# # print(my_list[-3:]) #Il meno è la stessa cosa del numero positivo;
# # print(my_list[-3:5]) #
# # print(my_list[::]) # Normale
# # my_list = ['a', 'b', 'c', 'd', 'e', 'f']
# #         #   0    1    2    3    4    5
# #         #   -7  -6   -5   -4   -3   -2
# # print(my_list[::-1]) # contrario

# my_list = [1, 2, 3, 2]
# my_list.sort()
# print(my_list)
# my_list.remove(2)
# print(my_list)
# print(removed)

# while 2 in my_list:
#     my_list.remove(2)
#
# print(my_list)
# popped = my_list.pop(0)
#
# print(my_list)
# print(popped)



# my_list = [4, 5, -7, 1, 2, 3, 2]
# my_list.sort(reverse=True)
# print(my_list)

# my_list = ['a', '5', '-7', '1', 'ammaccabanane', 'Giulio', 'sedie'] # tipi sempre omogenei nelle liste
#
# print(my_list)
#
# my_ordered_list = sorted(my_list)
# print(my_ordered_list)
# print(my_ordered_list is my_list)


# my_range = range(6)
#
# for elem in my_range:
#     print(elem)
# my_range = range(6)
#
# print(my_range)         # Returns: range(0, 6)
# print(type(my_range))   # Returns: <class 'range'>