# try: 
#     file = open('input.txt')
#     file.read()
# except FileNotFoundError as e:
#     print('An error occcured: ', e)

import json

d = {}

with open('db.json') as file:
    d = json.load(file)
    print(json.dumps(d))