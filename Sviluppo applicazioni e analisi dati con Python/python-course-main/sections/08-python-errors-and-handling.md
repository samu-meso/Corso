# Python and Programming Errors

In this section you will see: 
- Python Error Handling and Key Concepts
- Python Errors Hierarchy
- Rising Errors in Python
- Python Built-in Errors

When developing a program, the ***rising errors at the right moment*** and ***managing them when they appear*** are two crucial activities to let the program run smoothly, executing what the user expects.

Let's start from the latter activity, also called ***Error Handling***.

## Python and Error Handling

Error handling in Python is a robust mechanism designed to manage errors (called `exceptions`) that occur during the execution of a program, allowing the program to continue running or fail gracefully instead of crashing abruptly.

### Key Concepts of Error Handling in Python

- Python `Exceptions`: Exceptions are events that interrupt the normal flow of a program when an error occurs. They can result from various issues such as division by zero, file not found, invalid input, or network errors.

- Block `try`: The `try` block is used to wrap code that might generate an `exception`. Python executes the code in this block and monitors for errors.

- Block `except`: If an error occurs in the try block, control is transferred to the corresponding `except` block where the exception can be caught and handled. You can catch specific exceptions by naming them or catch any exception more broadly.

- Block `else`: The optional `else` block runs if no exceptions are raised in the `try` block, allowing you to specify code that should only execute when everything goes smoothly.

- Block `finally`: The `finally` block contains code that will execute regardless of whether an exception was raised or not—commonly used for cleanup actions like closing files or releasing resources.

Usually, when an error occurs, we know that the program suddendly exists, reporting the error in the console and, thus, closing without completing. 

```python
my_list = [1, 2, 3]
list_pos = int(input('Take the position in the list to pick: '))

print('Picked position number: ', list_pos , '; contained element: ', my_list[list_pos])
# The program stops here when inserting a letter instead of a number! 
# ERROR!
# Traceback (most recent call last):
#   File "<main.py>", line 2, in <module>
# ValueError: invalid literal for int() with base 10: 'd'

print('Program ended')
```

As we can see, the program does not reach the final print reporting the string `Program ended`, returning the description of an error. The errore tells us that it is not possible to use a convert a keyboard character into an `int`.

We can avoid the crash of the program when the error occurs, by wrapping the piece of code exposed to the error with the two `try ... except` blocks.

```python
my_list = [1, 2, 3]

try:
    list_pos = int(input('Take the position in the list to pick: '))
    print('Picked position number: ', list_pos , '; contained element: ', my_list[list_pos])
except Exception as e:
    print(e)

print('Program ended')
# Returns: 
# invalid literal for int() with base 10: 'd'
# Program ended
```

With such a setup, the program returns 2 prints:

1. The first reports the description of the exception, as defined at the line `print(e)`; 
2. The second, reports the final print `Program ended`

Let's take a look to a more meaningful example: in the case above, we want the user to insert a valid value, and we will repeat the request until a valid value is inserted.

```python
my_list = [1, 2, 3]

right_value = False
while right_value == False: 
    try: 
        list_pos = int(input('Take the position in the list to pick: '))
    except Exception as e:
        print(e)
    else: 
        right_value = True

print('Picked position number: ', list_pos , '; contained element: ', my_list[list_pos])
        
print('Program ended')
# Returns: 
# Take the position in the list to pick: g
# invalid literal for int() with base 10: 'g'
# Take the position in the list to pick: 2
# Picked position number:  2 ; contained element:  3
# Program ended
```

... But: what happens if we give in input a value out of range with respect the list length? 

It returns: 

```
Take the position in the list to pick: 5
ERROR!
Traceback (most recent call last):
  File "<main.py>", line 12, in <module>
IndexError: list index out of range
```

To avoid it, we must make a little mod to our previous code:

```python
my_list = [1, 2, 3]

right_value = False
while right_value == False: 
    try: 
        list_pos = int(input('Take the position in the list to pick: '))
        print('Picked position number: ', list_pos , '; contained element: ', my_list[list_pos])
    except Exception as e:
        print(e)
    else: 
        right_value = True
        
print('Program ended')
```

Can you spot the modification? Can you explain why and how it works? 

### Exception Hierarchy

All exceptions in Python derive from the base `Exception`, and ***inherit*** from it. The inheritation creates a hierarchy that contains specialized exceptions such as `ZeroDivisionError`, `FileNotFoundError`, `ValueError`, etc., that allow fine-grained control.

See the Python built-in Exceptions [here](https://www.w3schools.com/python/python_ref_exceptions.asp).

It is a good practice to catch specific exceptions before broader ones. For example, catching a `ValueError` before a general `Exception`. This respects the inheritance hierarchy and prevents broad except blocks from ***overshadowing specific handlers***.

You can also handle multiple exceptions in a single block by ***specifying them as a tuple***.

With these specifications in mind, it is possible to refine the above example as follows: 

```python
my_list = [1, 2, 3]

right_value = False
while right_value == False: 
    try: 
        list_pos = int(input('Take the position in the list to pick: '))
        print('Picked position number: ', list_pos , '; contained element: ', my_list[list_pos])
    except ValueError as e:
        print('A ValueError occured! ', e)
    except IndexError as e:
        print('An IndexError occured! ', e)
    except Exception as e:
        print('A generic Exception occured!', e)
    else: 
        right_value = True
        
print('Program ended')
```

As you can see, the more general `Exception` is left last, so that it does not shadow other errors when they occur. It is also possible to make the code more compact as follows: 

```python
my_list = [1, 2, 3]

right_value = False
while right_value == False: 
    try: 
        list_pos = int(input('Take the position in the list to pick: '))
        print('Picked position number: ', list_pos , '; contained element: ', my_list[list_pos])
    except (ValueError, IndexError, Exception) as e:
        print(e)
    else: 
        right_value = True
        
print('Program ended')
```

### Wrapping Up: How Error Handling Works Internally

When an error occurs, Python creates an ***exception object*** containing details about the error type and where it happened. The runtime searches the call stack from the point where the error occurred to find an appropriate handler, i.e., the appropriate `except` associated to the correct error class. If none is found, the program terminates with an error message.

## Python Raising Errors

The second key activity connected to errors regards ***raising customized exceptions*** when they occur in the program under development.

Raising exceptions is the process where the developed code ***intentionally triggers an error condition*** during execution using the `raise` statement. This mechanism allows to signal that something unexpected or erroneous has occurred, stopping the normal flow of the program until the exception is handled.

The syntax is `raise ExceptionType('message attached to the error')`:

```python
raise ValueError("Invalid input value")
```

### Built-in and Custom Exceptions

It is possible to raise any built-in `Exception` like `ValueError` or `IndexError`, or define and raise your own custom exception classes intheritating from the `Exception` base class.

```python
class MyError(Exception):
    pass

raise MyError("A custom error occurred")
```

More about class inheritance later. <!-- TODO: put a link to the relative section at the end of the course-->

### Use Cases of `raise`

When an exception is raised, normal code execution stops, and Python searches for an external handler (`try ... except`) that can catch the exception. If none is found, the program terminates with a traceback error message.

The `raise` statement can be used:

- To enforce conditions or input validation: 

    ```python
    age = input('Insert your age:')
    if age < 0:
        raise ValueError("Age cannot be negative")
    ```

- To propagate or re-raise exceptions within exception handlers after performing cleanup or logging:

    ```python
    try:
        ...
    except SomeError:
        # Do some actions
        raise   # Re-raise the caught exception
    ```

    For example: 

    ```python
    def divide_numbers(a, b):
        try:
            result = a / b
        except ZeroDivisionError as e:
            print("Logging error: attempted division by zero.")
            # Perform any cleanup or logging here
            raise  # Re-raise the same exception to propagate it further
        else:
            return result

    try:
        print(divide_numbers(10, 0))
    except ZeroDivisionError:
        print("Caught the re-raised ZeroDivisionError in the calling code.")
    # Returns: 
    # ERROR!
    # Logging error: attempted division by zero.
    # Caught the re-raised ZeroDivisionError in the calling code.
    ```

    In this way, the error caugh by the `try ... except` in the function is propagated towards higher-level `except` in the stack.

- To clarify or add context by ***chaining exceptions***:

    ```python
    try:
        ...
    except Exception as e:
        raise RuntimeError("Higher-level failure") from e
    ```

    In Python, ***exception chaining*** is a technique where a new exception is raised (usually in an except block) while preserving the original exception details. This is typically done using the syntax:

    ```python
    raise NewException() from OriginalException
    ```

    This makes the new exception explicitly linked to the original one, providing a clear traceback that shows both the original cause and the new error, which helps in debugging.

    ```python
    def open_database():
        try:
            # Simulate an error like file not found
            open("nonexistent_file.txt")
        except FileNotFoundError as original_exception:
            # Raise a new exception that adds context, chaining from original
            raise RuntimeError("Failed to open the database file") from original_exception

    try:
        open_database()
    except RuntimeError as e:
        print(f"Caught an exception: {e}")
        print("Original cause:")
        print(e.__cause__)
    # Returns: 
    # Caught an exception: Failed to open the database file
    # Original cause:
    # [Errno 2] No such file or directory: 'nonexistent_file.txt'
    ```

    What happens is the following: 
    
    - The function `open_database` tries to open a file which does not exist, causing a `FileNotFoundError`.

    - The except `FileNotFoundError` as `original_exception` block catches this error.

    - Inside this block, it raises a new `RuntimeError` with a more descriptive message and chains it explicitly to the original `FileNotFoundError` using from `original_exception`.

    - When caught outside, you can see the `RuntimeError` raised and also inspect the original cause with `e.__cause__`.

    > [!NOTE]
    > 
    > Keywords surrounded by double-underscores are called ***dunder attributes***. The word "dunder" is an abbreviation of the longer "double under(score)" name. More about **dunder attributes** and **methods** later [here](). <!-- TODO: At the end, add here the link -->