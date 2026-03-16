# Python Modules

In this section you will see: 
- Modules for Reusability, Maintainability, Organization
- Importing a Module
- Modules Initialization
- (Advanced) Modules Search Path: Locating Python Modules
- (Advanced) Modules Cache: Compiled Python Files
- Built-in Modules
- (Advanced) Python Packages
- Python `import` Guidelines

## Modules for Reusability, Maintainability, Organization

Python modules are ***files containing Python definitions and statements***. These files have a `.py` extension, and the file name without the extension acts as the module name. 

A module can include ***functions***, ***classes***, ***variables***, and ***runnable code***, allowing developers to ***organize and reuse code efficiently in different programs or scripts***.

Modules serve as a way to ***break down larger programs into smaller, manageable, and logically organized parts***. This modular approach helps maintain and structure code better. Modules can be imported into other modules or scripts using the `import` statement, giving access to the module's content through its namespace.

## Importing a Module

To import modules in your Python programs use the `import` keyword. 

```python
import sys

print(sys)          # Output: <module 'sys' (built-in)>
```

Python do not add the names of the attributes defined in `sys` directly to the current namespace, but it only adds the module name `sys`. 

Within a `module`, its name is available as the global variable `__name__`. To then access attributes offered by the module, it is possible to use the dot notation `module_name.module_attribute`.

```python
import sys

print(sys.path)     # Output: ... your Python paths
```

In such a setup, Python keeps the modules namespaces (the one of the program the developer is writing, and the one imported with the module) separated, importing in the current program only the module name `sys`.

If it is necessary to frequently access a module attribute, then is is possible to use a variable to keep its easier.

```python
import sys

sys_path = sys.path
print(sys.path)             # Output: ... your Python paths
```

It is also possible to import a module changing its reference name.

```python
import sys as another_name

print(another_name.path)    # Output: ... your Python paths
```

It is usually done to make modules name shorter (e.g., `import pandas as pd`).

### Submodules and Import

When importing a module, Python imports the whole set of information it contains. If only a little functionality is needed, then it is possible to use the `from` keyword, using the structure `from <module_name> import <module_attribute>`. 

```python
from sys import path

print(path)         # Output: ... your Python paths
```

The difference with the previous strategy is that, in this case, the whole namespace of the submodule is added to the current project namespace.

```python
from sys import path

print(path)         # Output: ... your Python paths

path = 'my_string'
print(path)         # Output: my_string
```

Such a setup can lead to conflicts with the current program under development, so pay attention to it.

The `from` keyword can also be used to import a whole module, when matched with the `*` wildcard.

```python
from sys import *

# ...
```

Nevertheless, its usage is discouraged as it does not clear out what is imported in the program, and what is not. 

## Modules Initialization

The initialization of a module happens only one time, when the name of the module is reached by the interpreter after the import statement. In this moment, the module is read, all functions and classes defined, and eventual code executed for initialization.

```python
# my_module.py
print('I am executing the code to initialize the module')

# Defining a function
def greet(name): 
	print(f'Ciao, {name}!')
```

At the module import, the global code is executed, so the message "I am executing the code to initialize the module" is printed in the console. Then, the function (and all eventual further code) is defined.

### Main Initialization Operations

#### 1. Global variable definition 

```python
# Module with configurations
config = {
    "version": "1.0",
    "author": "Me"
}

print("Module initialized with a configuration")
```

#### 2. Lump sum operations

```python
# Initialization
data = None

with open("data.txt") as file:
    data = file.read()

print("Data uploaded:", data)
```

#### 3. Configure loggers or other shared resources

```python
import logging

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)
logger.info("Logger configured")
```

## (Advanced) Modules Search Path: Locating Python Modules

In Python, it is possible to import existing modules, or to write custom modules and the import them. 

```python
# fibonacci.py: Fibonacci numbers module

def print_fib(n):    # Prints Fibonacci series up to n
    a, b = 0, 1
    while a < n:
        print(a, end=' ')
        a, b = b, a+b
    print()

def return_fib(n):   # Returns Fibonacci series up to n
    result = []
    a, b = 0, 1
    while a < n:
        result.append(a)
        a, b = b, a+b
    return result
```

To import custom modules in other .py files, it is possible to use rules presented in the [previous](#importing-a-module) section.

```python
import fibonacci

print(fibonacci.print_fib(1000))
# Output: 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987

print(fibonacci.return_fib(100))
# Output: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]

print(fibo.__name__)
# Output: 'fibonacci'
```

When using the `import` statement, the interpreter looks for its location to find and load it:

1. Firstly, the interpreter looks if the modules is listed in `sys.builtin_module_names`;

2. If not found, then the interpreter looks for a file name module_name.py in a list of directories given by the variable `sys.path`.

    > [!NOTE]
    > 
    > `sys.path` is a list of directories that Python reads to find modules to import. When a Python program starts, `sys.path` is automatically initialized with some standard directories, included the path to the Python standard library. 

    Is therefore important to be aware of how `sys.path` is populated:

    - a. the directory ***containing the input script*** (or the current directory if the interpreted is launched *interactively*);
    
    - b. **`PYTHONPATH`**: in this case, PYTHONPATH can contain one or more paths specified by the user. Here you can define additional directories where to look at (***not suggested!***).

    - c. Python looks in the standard-library-path where standard modules and packages comes by default with the interpreter (e.g., **`os`**, **`sys`**, **`math`** …)

    - d. the installation-dependent default (by convention including a site-packages directory, handled by the **`site`** module): it means, that at this stage Python looks in the installed packages locations (i.e., in the path of packages installed with **`pip`**).

> [!NOTE]
> 
> It is important to remember that, when the module is found, the research algorithm stops. So, if for example in the working directory there is a module with the same name of a standard module, Python will import the found module first, overwriting the standard library module - thus stopping and the research at the step "*a*".

## (Advanced) Modules Cache: Compiled Python Files

To speed up loading modules, they compiled version is stored in the **`__pycache__`**. The name of the compiled file associate to a given module is **`module.version.pyc`**. The “*version*” usually contains the Python version number.

> [!NOTE]
> 
> If the module has been written using **`CPython 3.3`**, the compiled version of the `module spam.py` would be **`__pycache__/spam.cpython-33.pyc`**. The naming convention allows different versions of the same cached module to ***coexist***.

Controls for needs for modifications or recompilation are made automatically by Python. Compiled modules are platform independent.

Cached modules are not checked in 2 cases: 

1. It always re-compiles and does not store the result for the module that is loaded directly from the command line; 

2. If there is no source module, i.e., if Python cannot find the source code file for the module (the `.py` file), it cannot cache the compiled bytecode (`.pyc` file) for that module. This typically happens if you only have a compiled module (like a `.pyc` file) but not the source code, or if the module is built-in or dynamically loaded without a source file.

## Built-in Modules

Python comes with a predefined set of modules described in the [***Python Standard Library***](https://docs.python.org/3/library/index.html) reference. 

Some modules are built-in into the interpreter; some other are not. Those built-in into the interpreter provide access to operations that are not part of the core of the language, but are built-in for efficiency or to provide access to OS primitives, as system calls. 

The set of such modules is a configuration option that depends by the underlying platform. For example, **`winreg`** module is only provided in Windows systems.

### The **`sys`** Module

One important module is **`sys`**. It  provides access to some variables used or maintained by the interpreter and to functions that interact strongly with the interpreter. It is always available.

The variable `sys.path` contains a list of strings that determines the search path of the interpreter for modules. It is initialized to a default path taken from `PYTHONPATH`, or from a default if it is not present. 

```python
import sys

print(sys.path)
# Out: 
# ['/home/hbx5DH', '/usr/lib/python38.zip', '/usr/lib/python3.8', 
# '/usr/lib/python3.8/lib-dynload', '/usr/local/lib/python3.8/dist-packages', 
# '/usr/lib/python3/dist-packages']
```

#### Personalizing `sys.path` to look for modules
After initialization, Python programs can modify `sys.path`, to personalize where Python should look for modules. 

```python
# Adding a new path to the end of sys.path
import sys

sys.path.append('/path/to/extra/modules')
```

To add the path at the beginning of the list, use instead: 

```python
import sys

sys.append(0, '/path/to/extra/modules')
```

### The **`dir()`** Function

The built-in function **`dir()`** provides which names a module defines. It returns a sorted list of string.

```python
import fibo, sys

dir(fibo)
# Out: ['__name__', 'print_fib', 'return_fib']

dir(sys)  
# Out: ['__breakpointhook__', '__displayhook__', '__doc__', '__excepthook__',
# '__interactivehook__', '__loader__', '__name__', '__package__', '__spec__',
# '__stderr__', '__stdin__', '__stdout__', '__unraisablehook__',
# '_clear_type_cache', '_current_frames', '_debugmallocstats', '_framework',
# '_getframe', '_git', '_home', '_xoptions', 'abiflags', 'addaudithook',
# 'api_version', 'argv', 'audit', 'base_exec_prefix', 'base_prefix',
# 'breakpointhook', 'builtin_module_names', 'byteorder', 'call_tracing',
# 'callstats', 'copyright', 'displayhook', 'dont_write_bytecode', 'exc_info',
# 'excepthook', 'exec_prefix', 'executable', 'exit', 'flags', 'float_info',
# 'float_repr_style', 'get_asyncgen_hooks', 'get_coroutine_origin_tracking_depth',
# 'getallocatedblocks', 'getdefaultencoding', 'getdlopenflags',
# 'getfilesystemencodeerrors', 'getfilesystemencoding', 'getprofile',
# 'getrecursionlimit', 'getrefcount', 'getsizeof', 'getswitchinterval',
# 'gettrace', 'hash_info', 'hexversion', 'implementation', 'int_info',
# 'intern', 'is_finalizing', 'last_traceback', 'last_type', 'last_value',
# 'maxsize', 'maxunicode', 'meta_path', 'modules', 'path', 'path_hooks',
# 'path_importer_cache', 'platform', 'prefix', 'ps1', 'ps2', 'pycache_prefix',
# 'set_asyncgen_hooks', 'set_coroutine_origin_tracking_depth', 'setdlopenflags',
# 'setprofile', 'setrecursionlimit', 'setswitchinterval', 'settrace', 'stderr',
# 'stdin', 'stdout', 'thread_info', 'unraisablehook', 'version', 'version_info',
# 'warnoptions']
```

Without arguments, `dir()` lists the names you have defined in the current module.

```python
import fibo

a = [1, 2, 3, 4, 5]
fib = fibo.fib
dir()

# Out: ['__builtins__', '__name__', 'a', 'fib', 'fibo', 'sys']
```

> [!NOTE]
> 
> `dir()` lists all types of names, i.e., variables, modules, functions … **Built-in functions and variables are excluded**; they are stored in the standard module builtins.

```python
import builtins
dir(builtins)  
# Out: ['ArithmeticError', 'AssertionError', 'AttributeError', 'BaseException',
# 'BlockingIOError', 'BrokenPipeError', 'BufferError', 'BytesWarning',
# 'ChildProcessError', 'ConnectionAbortedError', 'ConnectionError',
# 'ConnectionRefusedError', 'ConnectionResetError', 'DeprecationWarning',
# 'EOFError', 'Ellipsis', 'EnvironmentError', 'Exception', 'False',
# 'FileExistsError', 'FileNotFoundError', 'FloatingPointError',
# 'FutureWarning', 'GeneratorExit', 'IOError', 'ImportError',
# 'ImportWarning', 'IndentationError', 'IndexError', 'InterruptedError',
# 'IsADirectoryError', 'KeyError', 'KeyboardInterrupt', 'LookupError',
# 'MemoryError', 'NameError', 'None', 'NotADirectoryError', 'NotImplemented',
# 'NotImplementedError', 'OSError', 'OverflowError',
# 'PendingDeprecationWarning', 'PermissionError', 'ProcessLookupError',
# 'ReferenceError', 'ResourceWarning', 'RuntimeError', 'RuntimeWarning',
# 'StopIteration', 'SyntaxError', 'SyntaxWarning', 'SystemError',
# 'SystemExit', 'TabError', 'TimeoutError', 'True', 'TypeError',
# 'UnboundLocalError', 'UnicodeDecodeError', 'UnicodeEncodeError',
# 'UnicodeError', 'UnicodeTranslateError', 'UnicodeWarning', 'UserWarning',
# 'ValueError', 'Warning', 'ZeroDivisionError', '_', '__build_class__',
# '__debug__', '__doc__', '__import__', '__name__', '__package__', 'abs',
# 'all', 'any', 'ascii', 'bin', 'bool', 'bytearray', 'bytes', 'callable',
# 'chr', 'classmethod', 'compile', 'complex', 'copyright', 'credits',
# 'delattr', 'dict', 'dir', 'divmod', 'enumerate', 'eval', 'exec', 'exit',
# 'filter', 'float', 'format', 'frozenset', 'getattr', 'globals', 'hasattr',
# 'hash', 'help', 'hex', 'id', 'input', 'int', 'isinstance', 'issubclass',
# 'iter', 'len', 'license', 'list', 'locals', 'map', 'max', 'memoryview',
# 'min', 'next', 'object', 'oct', 'open', 'ord', 'pow', 'print', 'property',
# 'quit', 'range', 'repr', 'reversed', 'round', 'set', 'setattr', 'slice',
# 'sorted', 'staticmethod', 'str', 'sum', 'super', 'tuple', 'type', 'vars',
# 'zip']
```

## (Advanced) Python Packages

A Python package is a way to organize and structure code by grouping related modules into a directory. This directory must contain a special file named `__init__.py` which signals to Python that the directory should be treated as a package.

A package is essentially a directory containing multiple Python modules (files with `.py` suffix). Packages can contain sub-packages, which are simply subdirectories with their own `__init__.py` file, allowing hierarchical organization. The `__init__.py` file is executed when the package (or sub-package) is imported, and it can be used to initialize the package or control the modules that get exposed.

Using packages helps manage and reuse code efficiently, especially in larger projects, by logically grouping related modules. ***Packages can be distributed and installed using package managers*** like `pip`, often via repositories like the Python Package Index (***PyP***I).

A "one-level package", is structured as follows:  
```
my_package/
├── __init__.py
├── module1.py
└── module2.py
```

If a package contains sub-packages, the result can be as follows: 

```
my_package/
├── __init__.py
├── module1.py
├── module2.py
└── sub_package/
    ├── __init__.py
    └── module3.py
```

### Importing Packages

Importing packages works like importing modules. 

```python
import my_package

...
```

It is also possible importing modules, using the dot notation `import <package_name>.<module_name>` or `from <package_name>.<sub_package> import <module_name>`.

```python
import my_package.module1
from my_package.sub_package import module3

...
```

## Python `import` Guidelines

[PEP8](https://peps.python.org/pep-0008/) defines, among other things, [guidelines for imports](https://peps.python.org/pep-0008/#imports). In the following, some of them are reported: 

- Imports must be placed at the beginning of the file, so that anyone working with your program knows which modules have been used;

- The first imports to be mentioned are those that reference standard Python modules;

- Imports of third-party libraries (e.g., `pygame`, `pandas`, `matplotlib`, etc.) should be mentioned next;

- Module names must be placed on separate lines, i.e.: 

    ```python
    # Don't do like this ... 
    import os, sys, math

    # Instead, do like this ... 
    import os
    import sys
    import math

    ```
    