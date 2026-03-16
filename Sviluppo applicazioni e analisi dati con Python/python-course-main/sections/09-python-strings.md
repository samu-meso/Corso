# Python Strings

In this section you will see: 
- Slicing in-depth and its application to strings
- Formatted String Literal
- Common `string` methods
- Strings and Lists

## Strings and Slicing

Python strings have the double-nature of being both a single value data-type, as well as a data structure. Indeed, they can be treaten as ***a unique string containing some information***, or as ***an ordered sequence of characters***. 

In this context, some tools applicable to Python lists are also applicable to Python strings. This is the case for the ***slicing***. 

As the reader may recall, slicing is the operation to ***access to a portion of an sequence*** such as a list, or in other words, a "sub-sequence" of the given sequence. The slicing operation can be performed using the slicing operator `:` inside of the index operator `[ ]`. 

When using the slice operator, it is necessary to indicate before it the starting position of the slice, and after it its final position. The starting position of the slice is include, the last excluded. 

Slicing returnes the same data type given as an input.

```python
my_list = [1, 'two', 3, 'four', 'cinque', 7, 'acht', 9, 10]

print(my_list[1:5])         # Returns: ['two', 3, 'four', 'cinque']
print(type(my_list[1:5]))   # Returns: <class 'list'>
```

The same happens for Python strings.

```python
my_string = 'Hi mom, look how much fun I\'m having!'

print(my_string[0:6])         # Returns: Hi mom
print(type(my_string[0:6]))   # Returns: <class 'list'>
```

It is also possible to omit slicing positions.

```python
my_string = 'Hi mom, look how much fun I\'m having!'

print(my_string[:])         # Returns: Hi mom, look how much fun I'm having!
print(my_string[:6])         # Returns: Hi mom
print(my_string[0:])         # Returns: Hi mom, look how much fun I'm having!
```

Slicing offers also a way to provide a ***slicing step***.

```python
my_string = 'Hi mom, look how much fun I\'m having!'

print(my_string[::2])         # Returns: H o,lo o uhfnImhvn!
print(my_string[:6:3])         # Returns: Hm
```

## Formatted String Literal

Python Formatted String Literal (also called *f-string*), were introduced in `Python 3.6` to provide a concise and efficient way of embedding expressions inside string literals for string interpolation and formatting. 

An f-string is created by prefixing a string literal with the letter `f` or `F`. Within this string, Python expressions can be enclosed in curly braces `{ }` and will be evaluated and inserted into the string at runtime.

```python
name = "Jane"
age = 25
print(f"Hello, {name}! You are {age} years old.")
# Output: Hello Jane! You are 25 years old.
```

Python f-strings support the ***expression embedding***, which means that almost any valid Python expression can be inserted, including arithmetic operations, method calls, and comprehensions.

```python
print(f"The answer is {2 * 21}.")
# Output: The answer is 42

name = "John"
print(f"Uppercased name: {name.upper()}")
# Output: Uppercase name: JOHN

print(f"Powers of 2: {[2**n for n in range(3)]}")
# Output: [1, 2, 4]
```

Expressions inside f-strings are evaluated at runtime, requiring that the variables or expressions be in scope at the time of evaluation.

Some other features of python f-strings are: 

- Performance and Readability: compared to previous string formatting methods (`%` operator and `.format()` method), f-strings are typically faster and result in more readable and concise code;

- Advanced Usage: `Python 3.12` and later improved f-strings to support multi-line expressions, nested expressions, and the use of backslashes within the expression parts.

- Use Cases: F-strings are particularly useful for debugging, logging, and constructing dynamic strings where expressions, function calls, or data conversions happen inline.

A more detailed guide on Python f-strings is available [here](https://realpython.com/python-f-strings/).

## Common Methods

In the following, some common formatting string methods are reported: 

- ***Capitalization***: the method capitalized the string. It is applied on the string calling the method. 

    `<target_string>.capitalize()`

    ```python
    my_string = "hi, how are you?"

    capitalized = my_string.capitalize()
    print(capitalized)      # Output: Hi, how are you? 
    ```

- ***Upper case***: the method turns all letters of the string in upper case. It is applied on the string calling the method.

    `<target_string>.upper()`

    ```python
    my_string = "hi, how are you?"

    upper_case = my_string.upper()
    print(upper_case)      # Output: HI, HOW ARE YOU? 
    ```

- ***Lower case***:  the method turns all letters of the string in lower case. It is applied on the string calling the method
    
    `<target_string>.lower()`

    ```python
    my_string = "HI, HOW ARE YOU?"

    lower_case = my_string.lower()
    print(lower_case)      # Output: hi, how are you? 
    ```

- ***Title case***: the method turns all first letters of each word in upper case. It is applied on the string calling the method. 
    
    `<target_string>.title()`

    ```python
    my_string = "hi, how are you?"

    title_case = my_string.title()
    print(title_case)      # Output: Hi, How Are You? 
    ```

Research methods are also made available by Python: 

- ***Finding a substring in a string***: it looks for a substring in a string or in a portion of it, returning the position of the first letter of the substring if found, `-1` otherwise. The method is applied on the string calling the method, and accepts as parameters the substring to find, and an eventual starting and/or ending letter where to apply the research.

    `<target_string>.find(<sub> [, <start>, [<end>]])`

    ```python
    my_string = "hi, how are you?"

    print(my_string.find('how'))        # Output: 4 
    print(my_string.find('how', 5, 8))  # Output: -1 
    ```

- ***Replacing a substring in a string***: the method replaces a substring within a string or a specified portion of it, returnign the modified string. It is called on the original string and takes as parameters the substring to be replaced, an optional starting index from where to begin the replacement, and the number of occurrences to substitute if multiple matching substrings are found. 

    `<target_string>.replace(<old>, <new> [, <count>])`

    ```python
    my_string = "hi, how are you?"

    print(my_string.replace('hi', 'hello'))     # Output: hello, how are you?
    print(my_string.replace(' ', '_', 2))       # Output: hello,_how_are you? 
    ```

- ***Starts with***: the method checks whether the string, or a subportion of it, starts with the provided comparison string. It returns `True` if the string starts with the comparison string in the given interval, `False` otherwise. The method is applied on the string calling the method.

    `<target_string>.startswith(<prefix>[, <start> [, <end>]])`

    ```python
    my_string = "hi, how are you?"

    print(my_string.startswith('how'))      # Output: False
    print(my_string.startswith('how', 4))   # Output: True
    ```

- ***Ends with***: the method checks whether the string, or a subportion of it, ends with the provided comparison string. It returns `True` if the string ends with the comparison string in the given interval, `False` otherwise. The method is applied on the string calling the method.

    `<target_string>.endswith(<suffix>[, <start> [, <end>]])`

    ```python
    my_string = "hi, how are you?"

    print(my_string.endswith('?'))          # Output: True
    print(my_string.endswith('?', 0, 4))    # Output: False
    ```

- ***Occurences counting***: the method counts how many strings appear in a target string. It accepts the string to find, and eventual starting and ending characters where to perform the research. It returns the number of occurences found in the target string. The method is applied on the string calling the method.

    `<target_string>.count(<sub>[, <start> [, <end>]])`

    ```python
    my_string = "hi, how are you?"

    print(my_string.count('hi'))        # Output: 1
    print(my_string.count('o', 6, 14))  # Output: 1
    ```

Other formatting methods are: 

- ***strip***: the method removes `.strip()` characters from the start and the end of the string; if no characters to remove are provided, then the default character is the white space. The method is applied on the string calling the method.

    `<target_string>.strip(<substring>)`

    ```python
    my_string = "  hi, how are you? "

    print(my_string.strip())        # Output: hi, how are you? 
    print(my_string.strip('how'))   # Output: , how are you?
    ```

- ***l-strip***: the method `lstrip()` removes characters from the start of the string; if no characters to remove are provided, then the default character is the white space. The method is applied on the string calling the method.

    `<target_string>.lstrip(<substring>)`

    ```python
    my_string = "  hi, how are you? "

    print(my_string.lstrip())        # Output: hi, how are you?  
    print(my_string.lstrip('how'))   # Output: , how are you? 
    ```

- ***r-strip***: the method `.rstrip()` removes characters from the end of the string; if no characters to remove are provided, then the default character is the white space. The method is applied on the string calling the method.

    `<target_string>.rstrip(<substring>)`

    ```python
    my_string = "  hi, how are you? "

    print(my_string.rstrip())       # Output:   hi, how are you?  
    print(my_string.lstrip('you?')) # Output:   hi, how are 
    ```

There are also methods that can take or return a list from a string: 

- ***Union***: it is possible to convert elements in a list (or another collection) in a string, starting from a separator string, using the `.join()` method. The method is applied on the string calling the method and used as the separator string.

    `<separator_string>.join(<collection_to_join>)`

    ```python
    my_list = [1, "two", 3, "vier"]

    print(",".join(my_list))
    # Output: 1, two, 3, vier
    ```

- ***Separation***: it is possible to separate a string and collect separated elements in a list usign the `.split()` method. The method is applied on the string to spearate, calling the method.

    `<string_to_separate>.split(<separator>)`

    ```python
    my_string = "1 , two, 3, vier"

    print(my_string.split(my_string))
    # Output: ['1', 'two', '3, 'vier']
    ```

- ***Line by line collection***: the method `.splitlines()` collects a multi-line string into a list, where each element is a string line. The method is applied on the string to spearate, calling the method.

    `<multiline_string_to_collect>.splitlines()`

    ```python
    my_string = "1 , \ntwo, 3, \nvier"

    print(my_string.splitlines(my_string))
    # Output: ['1', 'two, 3', 'vier']
    ```

## Python Strings: Let's Experiment!

### Exercise 1

Given a string, create a copy of it in which all occurrences of its first character are replaced by '$' except the first occurrence.

--

### Exercise 2

Given a sentence, if both the words 'not' and 'poor' appear, and 'not' appears before 'poor', replace the sentence fragment between the two words with 'good' (test with 'the lyrics are not that poor').

--

### Exercise 3

Given a string representing a list of words separated by white space, find the longest word and print its length.

--

### Exercise 4

Given a string and a character, display the part of the string that appears after the last appearance of the character.

--

### Exercise 5

Given a multi-line text (not from the terminal, but set directly in the source code), display it on the screen with each line prefixed with a user-entered prefix.

--

### Exercise 6

Given a sentence, show how many times each vowel appears.

--

### Exercise 7

Given a sentence, remove all blank spaces.

--

### Exercise 8

Given a sentence, find the shortest and the longest word.

--

### Exercise 9

Create an exercise with the same functionality as exercise 5 at the end of the "Data Structures" section, but that stores students and grades as follows:
    
```python
register = {
    'mario-rossi': [30, 28, 30, 26],
    'dario-verdi': [30, 30, 28]
}
```
(First and last names are joined with a "-" and used as the key)