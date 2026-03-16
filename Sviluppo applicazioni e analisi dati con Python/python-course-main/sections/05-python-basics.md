# Python Basics

In this section you will see: 
- Main Data Types
- Operators
- Conditional Statements
- Loops
- Input/Output to/from the console
- Comments

## Single Value Data Types

Python is a programming language that is *dynamically typed*, it means that the developer does not  need to explicitly specify the type. Indeed, the Python interpreter automatically infers the data type and handles it accordingly.

Despite the automatic handling, the developer must be aware of Python available data types and associated handling methods.

### Numbers Data Types

Python has three main number data types essential for representing numeric values: 
- **Integer numbers** (`int`): <br>
Represents whole numbers, positive or negative, without any decimal or fractional part. Integers in Python are of unlimited length, meaning Python can handle very large numbers beyond typical fixed-size integer limits, such as `x = 42` or `big = 12345678901234567890`. <br>
This is because Python's int type automatically expands to accommodate very large numbers, using a variable-length representation internally. So, you can work with extremely large integers, for example, `10**309` or larger, without overflow errors. <br>
Python allows underscore separators in long integers for readability, like `1_000_000`.

- **Floating-Point numbers** (`float`): <br>
Used for numbers with decimal points, representing real numbers. Floats can also be expressed in scientific notation using `e` or `E` (e.g., `1.2e3` for `1200.0`). <br> 
Floating-Point numbers are stored with ***limited precision*** (typically up to 15 decimal places), because they are implemented using the ***IEEE 754 double-precision format***. 

> ![NOTE]
> 
> **IEEE 754 double-precision format**
> Also known as binary64, uses 64 bits to represent Floating-Point numbers, composed as follows: 
> <ul>
>     <li> 1 bit for sign; 
>     <li> 11 bit for exponent (biased by 1023);
>     <li> 52 bit mantissa(fraction).
> </ul>
> For more information, look [here](https://en.wikipedia.org/wiki/Double-precision_floating-point_format).

The maximum representable float is about `1.7976931348623157×10**308`, and the minimum positive normalized float is around `2.2250738585072014×10**−308`
 . Values outside this range are represented as special values like `inf` (infinity). <br>
Floating-Point numbers can sometimes exhibit rounding errors due to binary representation. They are useful for measurements, percentages, and any values requiring decimals, e.g., `pi = 3.14159`.

- **Complex numbers** (`complex`): <br>
Numbers that have both a real part and an imaginary part, written as `a + bj`, where `a` and `b` are floats and `j` represents the imaginary unit. <br>
Since the real and the imaginary parts are represented and floats, limits of Complex numbers are the same as Floating-Point numbers.<br>
Complex numbers are mostly used in scientific computing and engineering fields. For example, `z = 2 + 3j`. Python provides attributes like `.real` and `.imag` to access parts of the Complex number, for example: 

```python
z = 2 + 3j
print(z)        # Returns: (2+3j)
print(z.real)   # Returns: 2.0
print(z.imag)   # Returns: 3.0
```


### String Data Type
In Python, a `string` is a sequence of Unicode characters enclosed in either single quotes (`'...'`), double quotes (`"..."`), or triple quotes (`'''...'''` or `"""..."""`). Triple quotes allow multi-line strings.

```python
print('Hello, how are
you?')                  # Returns: error!

print('''Hello, how are
you?''')                  # Returns: 'Hello, how are
                          # you?'S
```

Since strings are sequences, individual characters can be accessed by ***indexing*** (starting from 0) and ***slicing***.

```python
s = "Hi, I am John! :)"
print(s[4])    # Returns: 'I'
```

Python does not have a separate character data type; a single character is simply a string of length one.

String support concatenation with the `+` operator and repetition with the `*` operator.

Python strings are Unicode by default, allowing representation of characters from virtually all writing systems and symbols.

Strings can include escape sequences like `\n` (new line), `\t` (tab), and `\\` (backslash). Prefixing the whole string with `r` creates ***raw strings***, which treat backslashes literally. For example: 

```python
print("Hi, I am \nJohn! :)")    # Returns: 'Hi, I am 
                                # John! :)'

print(r"Hi, I am \nJohn! :)")   # Returns: 'Hi, I am \nJohn! :)'
```

Strings can be used in many applications including text processing, user input handling, web development, and data processing.

### Boolean Data Type

Boolean data type (`bool`) represents one of two possible values: `True` or `False` (both in upper case). It is a built-in data type used primarily to express ***truth values*** in logic and control flow within Python programs.

The Boolean data type is a subclass of the Integer (`int`) type, where `True` is equivalent to the integer `1` and `False` is equivalent to the integer `0`. This means Booleans can be used in arithmetic contexts and converted easily to integers.

Thanks to the ***Truthiness***, is possible also the opposite, i.e., Non-Boolean values can be implicitly converted to Boolean using the `bool()` function:
- Zero numeric types `(0, 0.0, 0j)` convert to `False`.
- Non-zero numbers convert to `True`.
- Empty sequences or collections (like `''`, `[]`, `{}`) also convert to `False`.
- Most other values convert to `True`.

## Main Operations with Single Values Data Types

### Getting the Type

The `type()` Python ***built-in function*** allows you to know the data type. 
For example:

```python
type(1)         # Returns: 'int'
type(1.0)       # Returns: 'float'
type(3 - 2j)    # Returns: 'complex'
type(True)      # Returns: 'bool'
```

> ![NOTE]
> 
> Python ***built-in functions*** are functions that come integrated with the Python language, which can be called without referring to an object or a class. More on that later. 

### Converting Data Types

It is possible to use the `int()`, `float()` or `str()` Python ***built-in functions*** to convert a variable into an Integer, a Float or a String.

```python
x = 2.5
y = int(x)
print(y)        # Returns: 2
print(str(y))   # Returns: '2'
```

> ![NOTE] 
> 
> The conversion from float to int is done by truncating the decimal point. In other words, it ***returns its integer part***. 
To round a value, use the `round()` Python ***built-in*** method.

Python performs the automatic conversion of data types when useful or possible.
For example: 

```python
x = 5
y = 2
z = x / y
print(z)        # Returns: 2.5
print(type(z))  # Returns: 'float'
```

### Define Data Types

Even though it is dynamically typed, it is possible to integrate so called `type-hints` into the code.

```python
x: int = 5     # Suggests that x is an integer
```

Keep in mind that type hints ***are not used for type checking by the Python interpreter***.

This makes it possible to write ***incorrect*** type-hints:

```python
x: int = ‘5’    # Although conceptually incorrect, it is possible to do so.
```

Type hints are mainly used in function and method definitions, and can also be actively used by static analysis tools (e.g., `mypy`, more info [here](https://mypy-lang.org/)).

It is still possible to integrate manual type checks into the code using flow control and error handling tools (see later):

```python
x = 5
if not isinstance(x, int): 
    raise TypeError("x must be an integer")
```

---

## Assignment Operators

Assignment operators are operators used to assign to the symbol ***at the left of the operator*** the value provided on ***its right***, eventually following a rule. 

### Assignment (=)

The symbol "=" assigns the value of the expression on the right side of the "=" symbol, to the variable on the left.

For example: 

```python
x = 5       # The operator "=" assign the value "5" to the variable "x"
```

### Assignment with addition (+=)

Adds the value on the right to the current value of the variable on the left.

For example:

```python
x = 2
x += 3       # Assigns to "x" its previous value, plus 3
             # The result is 5
```

### Assignment with subtraction (-=)

Can be deduced from above.

### Assignment with multiplication (*=)

Can be deduced from above.

### Assignment with division (/=)

Can be deduced from above.

### Assignment with modulus (%=)

Can be deduced from above.

### Assignment with exponentiation (**=)

Can be deduced from above.

### Assignment with integer division (//=)

Can be deduced from above.

---

## Arithmetic Operators

Typically applicable to numbers, the result is a number.
When applied to other data types, they perform different operations depending by the involved types (see later, *operators overloading*).

- Addition (+): also applicable to strings!
- Subtraction (-)
- Multiplication (*): also applicable to strings!
- Division (/): the result is a float, if necessary, e.g., `5 / 2 = 2.5`
- Exponential (**): e.g., `3 ** 2 = 9`
- Integer division (//): e.g., `5 // 2 = 2`
- Module (%): remainder of integer division, e.g., `5 % 2 = 1`

---

## Comparison Operators

Python ***comparison operators*** are used to compare two values and return a result of type `bool`: either `True` or `False`. These operators allow you to evaluate relationships between operands and are ***fundamental for decision-making*** and ***control flow*** in programming.

Here are the key Python comparison operators with their meanings and examples:

| Operator  | Meaning             | Example | Result |
|-----------|---------------------|---------|--------|
| ==        | Equal to            | 3 == 5  | False  |
| !=        | Not equal to        | 3 != 5	| True   |
| >         | Greater than	      | 3 > 5	| False  |
| <         | Less than	          | 3 < 5	| True   |
| >=	    | Greater or equal to | 3 >= 3  | True   |
| <=	    | Less or equal to    | 3 <= 5  | True   |

These operators work with many data types, including numbers, strings (compared lexicographically), and other objects. The result of a comparison is always a bool (True or False).

Comparison operators are binary, meaning they require two operands. String comparisons are based on alphabetical, or [lexicographic order](https://en.wikipedia.org/wiki/Lexicographic_order), e.g., `"apple" < "banana"` returns `True`.

You can use them in conditional statements (if, while) and expressions to control program flow.

```python
a = 5
b = 2

print(a == b)   # False
print(a != b)   # True
print(a > b)    # True
print(a < b)    # False
print(a >= b)   # True
print(a <= b)   # False

# With strings
print("apple" < "banana")  # True
print("apple" == "Apple")  # False (case-sensitive)
```

## Logical Operators

Logical operators are operators working with Boolean values, returing a Boolean as well. They are based on the so-called "Truth Tables". Main operators are: 
- AND (`&`)
- OR (`|`)
- NOT (`!`)

| x     | y     |-| `x and y` |-| `x or y` |-| `not x` |
|-------|-------|-|-----------|-|----------|-|---------|
| True  | True  |-| True      |-| True     |-| False   |
| True  | False |-| False     |-| True     |-| False   |
| False | True  |-| False     |-| True     |-| True    |
| False | False |-| False     |-| False    |-| True    |

It is also possible to apply logical operators to other data types, which are therefore evaluated based on their ***Truthiness*** (see [before](#boolean)).

### Difference between `logical` and `bitwise-logical` Python operators

Logical operators `and` and `&`, `|` and `or`, and `not` and `!`, may look similar, but they perform slightly different boolean operations. 

- `and`, `or`, `not`: used to combine two Boolean expressions; it works on entire Boolean values, **not on bits**.
- `&`, `|`, `!`: perform a bitwise logical operations, which works on the binary representations of integers bit by bit. For example: 

```python
a = 6        # 110 in binary
b = 3        # 011 in binary
print(a & b) # Returns 2, 010 in binary
```

#### Explanation: 
```python
   1 1 0   (6 in binary)
 & 0 1 1   (3 in binary)
 --------
   0 1 0   (result - 2 in binary)
```

---

## Python Control Flow

Control flow in Python refers to the sequence in which individual statements, instructions, or function calls are executed or evaluated in a program. 

Control flow operators and statements enable you to direct the execution path of your code, allowing it to make decisions, repeat tasks, and handle exceptions dynamically rather than running sequentially from top to bottom. 

Key control flow tools include conditional statements such as `if`, `elif`, and `else`, loops like `for` and `while`, and exception handling with `try` and `except`. These structures are fundamental for writing flexible and intelligent programs that can respond appropriately to different situations and inputs.

Understanding control flow is essential for mastering Python programming and writing robust, maintainable code.

### If Statement

The if statement executes the associated code ***if and only if*** the condition it checks returns `True`.

**Structure:**

```python
if <condition to check>:
    <instructions>
```

The condition to check is always ***comparison***, or a ***chain of comparisons*** glued together by ***logical operators***. The condition to check can be arbitrarily complex.

**Example:**

```python
if (x % 2) == 0:
    s = "x is even"
    print(s)
```

> [!NOTE] 
> 
> In Python, the indentation ***is fundamental***. Indeed, the executed code is the one present ***under the indentation***.

### If ... Else Statement

This set of operators let the program execute a portion of code if the condition is `True`, or another otherwise.

**Structure:**

```python
if <condition to check>:
    <instructions>
else:
    <other instructions>
```

**Example:**

```python
if (x % 2) == 0:
    s = "x is even"
else:
    s = "x is uneven"
print(s)
```

### If ... Elif Statement

This set of operators is a bit different from the previous, as it allows to chain multiple `if ... else` operations. 

**Structure:**

```python
if <condizione che restituisce vero o falso>:
    <istruzioni>
elif <un’altra condizione>:
    <altre istruzioni>
else:
    <altre istruzioni ancora>
```

**Example:**

```python
if (x % 2) == 0:
    s = "x is even"
elif (x % 2) != 0:
    s = "x is uneven"
else:
    s = "Impossible!"
print(s)
```

### If ... Elif ... Else and Nesting

- Multiple `if ... elif ... else` statements can be nested arbitrarily. Be careful with indentation: it must reflect the nested structure of the statements!

> [!NOTE]
> 
> Oftentimes is possible to translate nested statemtens into a single `if ... elif ... else` structure using ***Logical Oeprators***.

**Example:**

```python
if <condizione> and/or/not <ulteriore condizione>:
    ...
```

---

### For Statement

A `for` loop in Python is a control flow statement used to execute a block of code repeatedly, for a number of times chosen in advance. Unlike other languages, the Python for loop is based on iterables. 

Python iterables are objects "[capable of returning its members one at a time](https://docs.python.org/3/glossary.html#term-iterable)". An example of that are *sequence-collections* (e.g., `list`, `str`, `tuple`, *see later*) but also collections that do not respect the concept of *order*, such as `dict`.

The syntax involves specifying an ***iteration*** variable that takes on the value of each ***item*** in the ***iterable*** in turn, followed by a colon and an indented block of code to be executed during each iteration. This looping mechanism makes it easy to perform repetitive tasks and process collections of data efficiently. 

```python
names = ['Lily', 'Brad', 'Fatima']

for name in names:
    print(name)

# Returns: 
# Lily
# Brad
# Fatima
```

The membership operator `in` verifies if a value belongs to the sequence or not. When used in a `for` loop, it iterates through the iterable values making them available as a variable. 

> [!NOTE]
>
> Unlike some other languages, Python's for loop works by directly iterating over the items rather than using an index counter by default.

In Python, not only data structures can be considered as iterables, but also strings: 

```python
for l in 'Hello!':
    print(l)

# Returns: 
# H
# e
# l
# l
# o
# !
```

### For Statement with `range()`

Generating an iterable ***in place*** makes it possible to create a for loop without having an iterable in advance.

```python
for i in range(1, 10, 2):
    print(i)

# Returns:
# 1
# 3
# 5
# 7
# 9
```

To this end, the `range()` operator comes in help. The ***built-in function*** `range()` generates a sequence of numeric values between two extremes (`([start included, optional, default 0], stop excluded, [end excluded, optional])`) at a chosen distance; more easily: 

> [!NOTE]
> 
> (step, optional, default 1)

### While Statement

A while loop in Python is a fundamental control flow statement that repeatedly executes a block of code as long as a specified condition remains `True`. The general syntax is:

```python
while <condition>:
    <instructions>
```

**Example:**

```python
i = 0
while (i < 10):
    print(i)
    i += 1
```

Before each iteration, the loop’s condition is evaluated. If the condition is `True`, the code block inside the loop executes. Otherwise, the loop ends and the next code is executed accordingly.

> [!NOTE] Potential for Infinite Loops:
>
> If the condition never becomes `False`, a while loop will run indefinitely (“infinite loop”). To prevent this, ensure that some operation inside the loop changes something that will eventually make the condition False.

While loops are usually used when the developer doesn't know in advance how many times the loop should run.

For example: 
- Reading input until a valid response is received.
- Waiting for an external event or condition to change.
- Print a menu until the exit command is provided
- ...

Loop Control Statements:
- `break` can be used to exit the loop early.
- `continue` skips the rest of the current iteration and moves to the next loop evaluation.

> [!TIP]
> 
> An optional `else` block can follow a while loop, which executes if the loop terminates *normally* (not by `break`).

#### `break` and `continue` Statements

- The `break` instruction interrupts the cycle execution immediately.

**Example:**

```python
i = 0
for c in "hello world":
    if (i > 5):
        break
    print(c)
    i += 1
# Returns: 
# h (0)
# e (1)
# l (2)
# l (3)
# o (4) 
# _ (5)
```

- The `continue` instruction skips the remaining instructions in the current iteration, passing directly to the next iteration, if any.

**Example:**

```python
i = 0
for c in "hello world":
    if (i == 4):
        i += 1
        continue
    print(c)
    i += 1
# Returns: 
# h (0)
# e (1)
# l (2)
# l (3) 
# _ (5)
# w (6)
# o (7)
# r (8)
# l (9) 
# d (10)
```

### Loop Statements and Nesting

- Multiple `for ... while ...` statements can be nested arbitrarily. Pay attention to eventual `break` and `continue` statements: they apply only to the loop where they belong.

---

## Console I/O - Basic User Interaction

Console Input and Output (I/O) in Python allows your programs to interact directly with users through the keyboard and the screen. 
The two primary ***Python built-in functions*** for console I/O are `input()` for reading user input and `print()` for displaying output.

### Console Output and the `print()` built-in

The `print()` function has been already mentioned and used several times in the content presented until here, but let's see more in depth what is it capable of.

First of all, the `print()` function outputs text, variables, or expressions to the console. 

```python
weather_string = "Today is sunny"
name = 'John'

print("Hello, folks!")      
print(weather_string)
print("My name is ", name)

# Returns: 
# Hello, folks!
# Today is sunny
# My name is John
```

In the `print()` arguments, it is also possible to pass multiple items separated by a comma which, by default, will be displayed in the output separated by spaces. 

```python
age = 33

print("You are", age, "years old.")

# Returns: 
# You are 33 years old.
```

The `print()` function adds a newline at the end by default, but you can change this using the end keyword argument.

```python
print("First string (dispayed on the same line of the second);", end=" ")
print("Second string;")
print("Third string.")

# Returns: 
# First string (dispayed on the same line of the second) Second string
# Third string
```

> [!NOTE] `print()` function and conversion to string
>
> All values passed to the `print()` function are automatically converted to strings for display.

### Console Input and the `input()` built-in

The `input()` built-in function prompts the user for input via the keyboard and ***always returns the entered value as a string***.

> [!NOTE]
> 
> In other words, every input given by the user via the input console is ingested as a string.

You can provide a prompt message by passing a string argument to `input()`. To work with numbers, you must ***explicitly convert the input string to an integer or float***  using `int()` or `float()` built-ins for [casting](#converting-data-types).

```python
name = input("Enter your name: ")
age = int(input("Enter your age: "))  # Converts input string to integer

print("My name is ", name, ", and I am ", age, ".")

# Returns: 
# My name is John, and I am 33.
```

A very important aspect of the `input()` function, is that it ***blocks the program***: it means that the program stops when it encounters the `input()` keyword, waiting for the user to press the `Enter` key.

## Python Comments

In Python (and, more in general, in programming), comments are lines or portions of code that are ignored by the interpreter and serve to explain, clarify, or document the code for human readers. 

Comments enhance code *readability*, *maintainability*, and *collaboration* by providing context or explanations where needed.

A comment in Python starts with the symbol `#`:

```python
# This is a single-line comment
print("Hello, World!")  # Comment after code
```

Unlike other programming languages, Python does not offer a way to write multi-line comments; to do it, just use the `#` symbol on multiple lines.

```python
# This is a comment
# that spans multiple
# lines
```

From time to time, it is possible to write instead that Python offers multi-line comments using the triple-quotes (e.g., `"""` or `'''`). Indeed, in this way, it is possible to write text ignored by the program in multiple lines; by the way, the text is not ignored by the so-called `docstrings`; instead, this is the intended way to create them! 

But, what are `docstrings`? 

### Python `docstrings`

Docstrings are a special kind of multi-line string used to document modules, classes, functions, or methods. Usually, Python `docstrings` are placed immediately after the function or class definition, and are programmatically accesible via the `__doc__` attribute (topic for advanced Python usage).

```python
def greet(name):
    """This function greets the person by name."""
    print(f"Hello, {name}!")
```

At this stage, it is enough to remember that with the triple quotes the developer does not start a multi-line comment, but `docstrings`, and that `docstrings` are used to create explicit documentation that can be reused by other programs, as they are programmatically accessible.

### The Importance of Comments and Best Practices

In the following, some suggestion about how to use comments:
- comments should be clear, concise, and relevant; 
- avoid redundant comments that restate obvious code;
- keep comments up to date as code evolves;
- even if oftentimes comments are used to "deactivate code" for fast debugging, avoid to push code with commented lines at the end of a developing task.

> [!NOTE] As a general rule of thumb, remember the following two concepts:
> 
> - Developers do not write code for machines to execute it, but for other developers to read it; 
> - The [code tells you *how* something is done, comments tells you *why*](https://blog.codinghorror.com/code-tells-you-how-comments-tell-you-why/).

<!-- TODO: add exercises -->
