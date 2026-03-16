# Python Functions

In this section you will see: 
- Expressiveness and Reusability
- Functions & Syntax
- Parameter Passing
- Return Statement
- Scope Visibility
- Built-In Functions

## Functions: Expressiveness and Reusability

#### ***Expressiveness*** 

In programming, it refers to a language's capability to clearly, naturally, and concisely express solutions to problems, often closely mirroring the original problem formulation. 

It encompasses how ***easily a language allows programmers to write code that is both understandable to humans and efficiently processed by computers***. A highly expressive language enables the representation of a wide variety of ideas, constructs, and abstractions with minimal boilerplate or redundant code.

In relation to the concept of a function, expressiveness is often reflected in how ***functions enable the encapsulation of behavior and abstraction of complexity***. Functions help express complex computations and repeated operations clearly and succinctly, making the code easier to read, maintain, and reuse. 

The ability to define functions with ***parameters***, ***return values***, and ***flexible argument types*** adds to the expressiveness of a language because it allows programmers to modularize their solutions and represent subproblems directly through function calls.

Thus, functions are a fundamental construct that contribute significantly to a language's expressiveness by allowing programmers to structure code logically and efficiently, directly reflecting the problem's structure within the program.

---


#### ***Reusability***

In programming, it refers to the design principle and practice of ***creating code components that can be used multiple times*** across different parts of a program or even in different programs without modification. It emphasizes writing ***modular***, ***generalized***, and ***decoupled*** code that can be easily integrated into new contexts, reducing redundancy and effort.

Regarding the concept of a function, reusability is a core advantage functions provide. Functions ***encapsulate a specific task or behavior into a discrete unit of code that can be called repeatedly whenever that task is needed***. 

By defining a function once and reusing it multiple times, programmers ***avoid duplicating code***, making the codebase ***more maintainable*** and ***less prone to errors***. Functions can take parameters, making them adaptable to slightly different situations or inputs, further enhancing their reusability.

In summary, reusability is about ***leveraging abstractions like functions to write code that can be applied in multiple scenarios***, promoting efficiency, consistency, and maintainability in software development. Functions are one of the primary tools that enable reusability in programming languages.

> [!NOTE] What is an abstraction? 
> 
> “A less detailed representation of an object or concept in nature.” More about it later.

---

Until now, the expressiveness of our programs equals the base expressiveness of Python, while the only reusability tool that we know is ... copy-paste. Let's improve it.

## Functions: Definition & Syntax

A function is a fragment of code associated with a name, re-executable at will by referring to the name.

- ***expressiveness*** = arbitrary, incrementable at will by the programmer encapsulating increasingly complex code inside of it;
- ***reusability*** = unlimited, at any function invocation.

The code to be reused, i.e., the one that we previously used to copy-paste, becomes the function code.

The syntax of a function is depicted below: 

```python
def my_function(parameters):
    """Function docstring"""
    <function body>
    return <expression>
```

It is defined using the keyword `def`. Parameters are variables ready to contain values passed at the function invocation, used in the body of the function itself.

The `docstring` is optional, but it is a good practice to include it. The body of the function, instead, encapsulates the code executed at the function call. 

The final keyword `return` indicates the value to return to the code fragment that calls the function. If it is empty or absent, nothing is returned.

```python
def showDictList(dictsList):
    """Prints nicely a list of dictionaries to console"""
    for e in dictsList: 
        for k in e: 
            print(k, ': ', e[k])
            print('-----')
    return

ld1 = [
    {
        'first_dict_first_elem': 'value 1', 
        'first_dict_second_elem': 'value 2'
    },
    {
        'second_dict_first_elem': 'another value 1', 
        'second_dict_second_elem': 'another value 2'
    }, 
]

showDictList(ld1)
```

## Functions and Parameters Passing

Parameter passing during a function invocation in Python always occurs by ***copying the reference***.

- For simple types, there is no reference, so the value is copied.
- For structured types, two variables may therefore point to the same value.

What undesirable situations can the second case generate?

```python
def selfAppend(l):
    """Appends the receveid list to itself"""
    l.append(l[:])
    print('l: ', l)

def reset(l):
    """Resets the received list as an empty list"""
    l = []
    print('l: ', l)

l1 = [1, 2, 3]
selfAppend(l1)
print('l1: ', l1)
reset(l1)
print('l1: ', l1)

# Returns: 
# l:  [1, 2, 3, [1, 2, 3]]
# l1:  [1, 2, 3, [1, 2, 3]]
# l:  []
# l1:  [1, 2, 3, [1, 2, 3]]
```

Can you explain what happened? 

## Parameters Passing Variants

Parameters can be passed in various ways in PYthon.

> [!NOTE] Difference between ***parameters*** and ***arguments***
> 
> ***Parameters*** are functions' variables acting like placeholders. 
> 
> ***Arguments*** are the actual values or data you provide to the function when you call it. These values fill in the parameters.

### Standard Parameters Definition and Argument Passing

```python
def triSum(a, b, c): 
    return a + b - c

triSum(1, 2, 3)     # Returns: 0
triSum(3, 2, 1)     # Returns: 4
```

### Parameters Definition with Default Arguments

```python
def triSum(b=1, c=2, a=3): 
    return a + b - c

triSum()            # Returns: 2
```

```python
def triSum(a, b, c=0): 
    return a + b - c

triSum(1, 2)        # Returns: 3
```

### Parameters Definition, Parameters and Returned Value Type Hints

```python
def triSum(a: int, b: int, c: int) -> int: 
    return a + b - c
```

```python
def triSum(a: int, b: int, c: int=0) -> int: 
    return a + b - c
```

> [!NOTE] Type hints and runtime operations
> 
> Remember that in Python types are not automatically checked during runtime by the interpreter! 

### Parameters Definition with Arbitrary Arguments

```python
def triSum(a, b, *args): 
    for elem in args:   
        print(elem) 
    return a+b
    
triSum(1, 2, 3) 
# Returns: 
# 3

triSum(3, 2, 1, 'hi', 'friend')
# Returns: 
# 1
# hi
# friend
```

The parameter `*args` allows the function to accept an arbitrary number of positional arguments. 

When you define a function with `*args` in the parameter list, it collects all the positional arguments passed to the function into a `tuple` named `args`. Though the name can be anything, `*args` is the convention.

The `*args` parameter useful when you don't know beforehand how many arguments might be passed to the function.

Additional notes:

- `*args` is especially handy when designing functions that must handle varying numbers of inputs;

- The arguments passed through `*args` behave like a tuple inside the function;

- You can also use the `*` operator to unpack lists or tuples when calling such functions.

```python
def greet(*names):
    for name in names:
        print(f"Hello, {name}!")

# Using * to unpack a list into the function arguments
name_list = ["Alice", "Bob", "Charlie"]
greet(*name_list)   

# Here the * operator unpacks the list so each element is
# passed as a separate positional argument.
```

### Parameters Definition with Arbitrary Key-Value Arguments

A similar behavior can be obtained using `kwargs`. The difference is that, in this case, arguments are packed as key-value pairs.

```python
def triSum(a, b, **kwargs): 
    for elem in kwargs:   
        print(elem)   
        print(kwargs[elem]) 
    return a+b-c 

triSum(1, 2, 3)
triSum(3, 2, 1, key='value')
```

With `kwargs`, all the keyword arguments passed are collected into a dictionary where the keys are the argument names and the values are the argument values.

Key points about **kwargs:

- It collects keyword arguments that you pass by name during the function call;

- Inside the function, `kwargs` behaves like a dictionary holding all these keyword-value pairs.

- This provides flexibility to accept any number of named arguments without explicitly defining them.

As `*args`, `**kwargs` can also take advantage of the `**` unpacking operator.

```python
def display_info(name, age):
    print(f"Name: {name}, Age: {age}")

info = {"name": "Emma", "age": 25}
# Using ** to unpack a dict into the function key-value arguments
display_info(**info)

# Here the ** operator unpacks the dict so each element is
# passed as a separate key-value positional argument.
```

## The `return` Instruction

The Python return statement serves two main purposes within a function:

1. It immediately exits the function when executed;

2. It sends a value (or multiple values) back to the caller, which invoked the function.

When the `return` statement is executed, the function terminates, and the value specified after `return` is passed back to the caller.

If no value is provided with `return`, or if the `return` statement is omitted entirely, the function returns the special value `None` by default.

```python
def function_name(parameters):
    # function body
    return value
```

The returned value can be any Python object, including integers, floats, strings, lists, dictionaries, tuples, user-defined objects, or even other functions.

Any code after the `return` statement inside a function is not executed.

Functions can return multiple values separated by commas; internally, Python returns these as a `tuple`:

- Returning a single value: 

    ```python
    def add(a, b):
        return a + b

    result = add(2, 3)
    print(result)
    # Output: 
    # 5
    ```

- Returning multiple values:

    ```python
    def calculate(a, b):
        return a + b, a * b

    sum_result, product_result = calculate(4, 5)
    print('Sum: ', sum_result, 'Product: ', product_result)
    # Output: 
    # Sum: 9, Product: 20
    ```

    If the result of a function returning multiple values is assigned to a single variable, then the returned value is a `tuple`.

- Returning complex data types:

    ```python
    def person_info(name, age):
        return {"name": name, "age": age}

    print(person_info("Alice", 30))  
    # Output: 
    # {'name': 'Alice', 'age': 30}
    ```

- Returning without a value:

    ```python
    def no_return():
        return

    print(no_return())  
    # Output: 
    # None
    ```

## Variable Scope and Visibility

In Python, *scope* refers to the ***region or context in a program where a variable is accessible and can be referenced by the interpreter***. Understanding scope is essential for managing variable visibility and avoiding conflicts.

In Python, there are the following scopes: 

- local scope; 
- global scope; 
- non-local scope. 

### Local Scope

Variables declared inside a function have ***local scope***.  What it means, is that they ***can only be accessed within that function*** and ***do not exist outside of it***.

Each time a function is called, a new local scope is created.

```python
def my_function():
    local_var = 10  # local variable
    print(local_var)

my_function()       
# Output: 10

print(local_var)    
# Output: ERROR!
# Traceback (most recent call last):
#   File "<main.py>", line 6, in <module>
# NameError: name 'local_var' is not defined
```

### Global Scope

Variables declared outside any function or block have global scope. These variables ***can be accessed anywhere in the program***, including inside functions.

```python
global_var = 20     # global variable

def print_global():
    print(global_var)

print_global()      # Output: 20
print(global_var)   # Output: 20
```

Modifying a global variable inside a function ***requires the*** `global` ***keyword***.

```python
counter = 0         # global variable

def increment():
    global counter  # declare counter as global
    counter += 1    # modify the global variable

increment()
print(counter)      # Output: 1
```

Explicitely declaring the variable as `global` within the function, tells Python that you want to use the global version of the variable rather than creating a new local variable with the same name.

Some details must be considered when modifying global variables inside of functions - or other non global scopes: 

- the `global` statement must appear before you modify the variable inside the function;

- without `global`, assigning to a variable inside a function creates a local variable, leaving the global variable unchanged;

- it is possible to declare multiple variables as global in one global statement if needed;

- use of global variables in functions is generally discouraged except when necessary, as it makes code harder to understand and maintain.

> [!NOTE]
> 
> It is also possible to modify global variables by using the built-in `globals()` function, but the `global` keyword is more straightforward and commonly used.

## Python Built-ins Functions

Python has a rich set of built-in functions that are always available ***without needing to import any additional modules***. 

These functions cover a broad range of common programming tasks, including numerical operations, type conversions, data structure manipulations, and utility functionalities, making Python coding more efficient and expressive.

All Python built-in functions are listed [here](https://docs.python.org/3/library/functions.html).

In the following, some of them are depicted.

- `len()`: accepts an object or collection and returns the number of items it stores;
- `max()`: accepts an interable, or two or more arguments, and returns the largest;
- `min()`: accepts an interable, or two or more arguments, and returns the smaller;
- `range()`: accepts the length of the range to return as a mandatory parameter, and then the starting point (default is `0`), and the steps (default is `0`). It returns an object of class `range()`, that is an immutable sequence type;
- `round()`: accepts a number and returns the same number rounded to the number of digits of precision after the decimal point; if the number of digits is omitted or is `None`, it returns the nearest integer to its input;
- `sorted()`: accepts and iterable and returns its content sorted in packed in a `list()`; it accepts as optional arguments `key` to specify a function that extracts comparison keys from each element used to perform the sorting operation, and `reverse` to specify if the result must be reversed at the end;
- `sum()`: accepts and iterable and performs the sum of all the elements present in it, from left to right; it accepts also a different starting position as optional parameter;  
- `type()`: it can be used with one or three arguments; when the argument is one, it returns the type of the received object; the returned value is a `type()` object and generally the same object as returned by `object.__class__`.

There are also other very useful built-ins that can be used to perform castings, such as `int()`, `float()`, `str()`, `list()`, `set()`, `tuple()`, and `dict()`.

## Python Functions: Let's Experiment! 

### Exercise 1

Write a function `greet(name)` that takes a name as an argument and prints a greeting. Call the function with your name.

--

### Exercise 2

Write a function `calculate_area(radius)` that takes the radius of a circle as an argument and returns the area of the circle. Use the formula: $Area=π×radius2Area=π×radius2$

--

### Exercise 3

Write a function `sum_two_numbers(a, b)` that takes two numbers as arguments and returns their sum.

--

### Exercise 4

Write a function `is_even(number)` that takes a number as an argument and returns `True` if the number is even and `False` otherwise.

--

### Exercise 5

Write a function `factorial(n)` that calculates the factorial of a given number `n`.

--

### Exercise 6

Write a function `count_vowels(text)` that takes a string as an argument and returns a dictionary with the vowels present in the text and their count.
Ignores case, only considers vowels `(a, e, i, o, u)`.

--

### Exercise 7

Write a function `find_primes(n)` that returns a list of prime numbers up to `n`.

--

### Exercise 8

Write a series of functions to analyze a text. Use these functions to obtain various information:

- Function 1: `word_count(text)`. This function should take a text string as an argument and return the number of words in the text.

- Function 2: `word_occurrence_count(text, word)`. This function should take a text string and a word as arguments, then return the number of times the word appears in the text. The search should be case-insensitive.

- Function 3: `average_word_length(text)`. This function should calculate the average length of the words in the text. The length of a word is given by the number of characters, and the average length is obtained by dividing the sum of the lengths of all the words by the total number of words.

- Function 4: `most_frequent_word(text)`. This function should return the most frequent word in the text. In case of a tie, return any of the most frequent words.

- Function 5: `sentence_count(text)`. This function should take a text string as an argument and return the number of sentences in the text. You can assume that sentences are separated by a period, semicolon, or exclamation point.

- Function 6: `character_count(text)`. This function should return a dictionary containing a count of each character in the text. Non-alphanumeric characters and spaces must be included in the count.
