# try:
#     with open(r'data\file.txt') as f:
#         file_content = f.read()
#         print(file_content)
# except FileNotFoundError as e:
#     print("Error while opening the file")
#     print(e)
"""
Exercise #2
"""

file_content = None
formatted_content = ''

try:
    with open('../data/file.txt') as file:
        # file_content = file.read()
        # print(file.read())
        # print(file)
        for line in file:
            if (line[-1] != '\n') or (line[-1]) != ' ':
                line.replace('\n', '')
            # print(r"Starts with \n", line.startswith("\n"))
            # print(r"Starts with 'space'", line.startswith("     "))
            if line.startswith('\n'):
                formatted_content = formatted_content + '<riga vuota>\n'
            elif line.startswith(' '):
                formatted_content = formatted_content + '<riga di spazi>\n'
            else:
                formatted_content = formatted_content + line
except FileNotFoundError as e:
    print(e)
except Exception as e:
    print(e)
print(formatted_content)