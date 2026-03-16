# Python Files

In this section you will see: 
- How to open text files
- Reading from files
- Reading line by line
- Writing text files
- Closing files
- Error handling role
- Folder and file management
- Serializing data files

## Opening and Reading Files

Before reading or writing to any file, you must open it.
To open a file in Python, it is possible to use built-in function `open(<file_name> [, <access_mode>])`.

It is necessary to pass to `open()` the filename to open in the program, and eventually the ***access mode***. In total there are 3 possible access modes: 

- `r`: read, default value;
- `w`: write, writes inside the file; if it does not exist, is created; if it exists, is overwritten;
- `a`: append, adds data inside of the file; if it does not exist, is created.

```python
f = open('input.txt', 'r')
```

> [!NOTE]
> 
> The opening mode must be passed as a string!

The function `open()` returns an ***object***, from which we can read the stored data using associated methods. 

With the method `.read()` it is possible to read all the file content (if it fits the memory), returning the read string. The method is called on the variable hosting the object returned from the `open()` function call.

```python
f = open('input.txt', 'r')
my_string = f.read()
print(my_string)
# Output: ... the content of the file
```

### Filenames, Filepaths and Operative Systems

The path to a file is the sequence of folders you need to traverse to get to the file.

```
\
├── bin
├── boot
├── dev
├── ...
└── home
    └── my_user
        ├── desktop
        ├── documents
        │   └── notes
        │       └── my-super-secret-notes.txt
        ├── download
        ├── images
        └── music
```

Consider the `my-super-secret-notes.txt`: the complete path to traverse to get to the file from the Linux/MacOS content root `\` (in Windows the content root is a disk letter), is: `\home\my_user\documents\notes\my-super-secret-notes.txt`.

When the path to the file starts from the content root, then the path is called ***absolute***.

It is worth noting that, from the machine's perspective, ***filenames are defined by their absolute paths*** rather than solely by the final component of the path representing the filename itself!

When working on a project, usually it has a project root (i.e., the folder containing the project files) that is different from the absolute root. It is called ***working directory***. When it is considered the path from the working directory to the target file, then is highlighted its ***relative path***.


```
\
├── bin
├── boot
├── dev
├── ...
└── home
    └── my_user
        ├── desktop
        ├── documents
        │   └── notes
        │       └── my-super-secret-notes.txt
        ├── download
        ├── images
        └── music
        └── projects
            ├── my-super-project
            │   └── assets
            │       └── style
            │           └── index.css
            └── index.html
```

In the above case, considering `my-super-project` as the working directory, than `\assets\style\index.css` is the path to the `index.css` file.

When working on a project, is usually preferable to adopt all relative path, to enhance the project portability. 

To get the current working directory: 

```python
import os 

os.getcwd()
# Ouput: /path/to/cwd
```

## Reading File Line by Line

The method `.readline()` reads the file ***line by line***.

The last character of each line is the termination character `\n`.

If the line is empty and is not the last, then only the `\n` is returned.

When the read line is the last of the file, then the `\n` character is omitted. In this case, an empty line ` ` is returned.

Usually, when reading line by line is a good practice to use an iterative cycle.

```python
file = open('input.txt', 'r')
while my_string != '': 
    my_string = file.readline()
    print(my_string) 
    # Output: ... the content of the line
```

Alternatively, it is possible to use the `for` loop.

```python
file = open('input.txt', 'r')
for line in file: 
    print(line, end='') 
    # Output: ... the content of the line
```

In this case, the content of each line is automatically stored in the `line` variable of the `for` loop.

It is handy in certain situations to store the whole content of a file in a list, with each element of a list being a line.

```python
file = open('input.txt', 'r')
content = list(file)
```

## Writing a File

To write a file, it must be opened using the appropriate mode.

```python
# To overwrite
file = open('input.txt', 'w')

# To append
file = open('input.txt', 'a')
```

Remember the difference between the `w` and the `a` mode:

- `w` mode writes inside the file; if it does not exist, is created; if it exists, is overwritten; 
- `a` appends data inside of the file; if it does not exist, is created.

To write inside the file, use the method `.write(<string to write>)` applied on the variable holding the file content.

```python
file = open('input.txt', 'w')
file.write('Hello my friend, \nI am writing you this file ...')
```

The `.write()` method applies both to write and append modes. The `.writelines(<list or iterable>)` instead writes several lines grouped in a collection, where each element of the collection is considered as a single line.

```python
file = open('input.txt', 'w')
file.write(['Hello my friend,', 'am writing you this file ...'])
```

## Closing Files

Each opened file ***must be closed***. To close an opened file, it is possible to call the `.close()` method on the varaible holding the object with the content of the file.

```python
file = open('input.txt', 'w')

# ... performing operations

file.close()
```

It is very important to close a file after each opening, to avoid data loss or other potential issues. 

Another approach is possible, involving the `with` setatement.

```python
with open('input.txt', 'w') as f:
    # ... performing operations

# ... performing other operations
```

In the case above, the explicit method `.close()` is not mentioned, as it is automatically called when the code below the `with` statement, ends. In particular, the `with` statement stores the content of the opened file in the variable positioned after the `as` keyword, in this case the variable's name is `f`.

## Error Handling

Input/output operations are usually very prone to errors. This is due to several factors that may be involved, such as: 

- file not found; 
- file already existing;
- file already opened; 
- ... and so on. 

For that reason, each time a file is opened, is a good practice to encapsulate it in a `try - except` statement.

```python
try: 
    file = open('input.txt')
    file.read()
except FileNotFoundError as e:
    print('An error occured: ', FileNotFoundError.args)
finally:
    file.close()
```

Closing a file is still important, also when applying the `try - except`. Closing the file using `with` is still possible.

```python
try: 
    with open('input.txt') as file:
        file.read()
except FileNotFoundError as e:
    print('An error occured: ', FileNotFoundError.args)
```

## Folder and File Management

It is possible to add or remove folders using Python using the `os` module. Target functions are `.mkdir('<path-to-dir>')` and `.rmdir('<path-to-dir>')`.

```python
import os 

os.mkdir('/path/to/dir')
# ... other operations
os.rmdir('/path/to/dir')
```

Keep in mind that both `.mkdir('<path-to-dir>')` and `.rmdir('<path-to-dir>')` functions add and remove one folder at a time, without the possibility of creating or removing entire paths in one call. Moreover, to delete a folder, it must be empty for safety reasons.

Folders exploration is also possible using Python. 

```python
import os 

list_dir = os.listdir('path/to/explore')
print(list_dir)
# Output: ['.git', '.gitattributes', 'folder-1', 'folder-2', 'config', 'text-file.txt']
```

The method returns a list of all the content in the provided path. If no path is passed, then the current working directory path is explored.

Other useful methods are: 

- file existence: `os.path.exists('file/to/check.ext')`
- is a file: `os.path.isfile('file/to/check.ext')`
- is a folder: `os.path.isfolder('folder/to/check')`

### File exploration

Saving data such as lists, dictionaries and other, in text files is not handy. Indeed, it is needed to manually manage the internal data representation, as well as know them in advance.

```python
# Saving
d = {
    'name': 'John',
    'surname': 'Doe', 
    'year': '1984'
}

with open('db.txt', 'w') as file: 
    for k in d: 
        file.write(f'{k}:{str(d[k])}')

###

# Loading
d = {}

with open('db.txt') as file: 
    for line in file:
        k, v = line.split(',')
        d[k] = v

d['year'] = int(d['year'])
```

For that reason, key data are usually ***serialized*** in JSON (JavScript Object Notation) files, ***de facto standard*** in the web domain, then extended in other contexts.

To handle json objects, it is necessary to import the `json` module. The `.dumps()` method returns the JSON representation stored in the file. The `.dump()` object can instead be used to ***serialize*** (i.e., write) JSON files on the disk. Finally, the `.load()` method can be used to ***de-serialize*** (i.e., load) the content of a file.

```python
# Save
import json

d = {
    'name': 'John',
    'surname': 'Doe', 
    'year': '1984'
}

with open('db.json', 'w') as file: 
    json.dump(d, file)

###

# Load
import json

d = {}

with open('db.json') as file:
    d = json.load(file)

print(json.dumps(d))
# Output: {"name": "John", "surname": "Doe", "year": "1984"}
```

## Python Files: Let's Experiment! 

### Exercise 1

Given an input file with content as shown in the figure on the right, write a program that displays its contents by numbering its lines.

--

### Exercise 2

Given an input file with content as shown in the figure below on the left, specifically with blank spaces, empty lines, and lines with only white spaces, write a program that displays its contents by numbering its lines and replacing empty lines with the string <empty line> and lines with only spaces with <line with only spaces>. The expected result is depicted in the figure below, on the right.

<div align="center">
    <img src="../assets/images/21-file-exercise-2-input.png" width="173"/>
    <img src="../assets/images/22-file-exercise-2-output.png" width="200"/>
    <div align="center">
        <figcaption>
            <em>Input and expected output of Exercise 2.</em>
            <br>
            <br>
        </figcaption>
    </div>
</div>

--

### Exercise 3

Given the same input file as in Exercise 2, write a program that displays its contents on a single line, joining lines with the '<' character and replacing line breaks with the '>' character. The result must be as follows: 

<div align="center">
    <img src="../assets/images/23-file-exercise-result.png" width="600"/>
    <div align="center">
        <figcaption>
            <em>Expected result of Exercise 3.</em>
            <br>
            <br>
        </figcaption>
    </div>
</div>

--

### Exercise 4

Write a program that prompts the user to enter a sentence in which line breaks are specified with the '§' character, and then writes that sentence, starting a new line at the required point in a file.

--

### Exercise 5

Write a program that writes a new line to a file for each time the
program is launched, indicating the number of times it has been launched.

--

### Exercise 6

Add a function to the "register" exercise (e.g., 5 Data Structures - Dictionaries) to save data to a file and a function to load data from a file. Use the JSON format.
