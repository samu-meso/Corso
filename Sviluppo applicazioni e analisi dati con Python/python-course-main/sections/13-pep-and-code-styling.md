# PEP and Code Styling

Writing styles represent the conventions and rules followed in writing source code, aimed at improving:
- Readability
- Maintainability
- Consistency within a project

Over time, each language has specified its own writing style guidelines.

Among the characteristics that writing styles define are:
- Indentation;
- Maximum line length;
- Naming;
- ...

Here are some characteristics for Java, C, and PHP:
- PHP is very flexible and allows for different writing styles; however, it is recommended to follow the PHP-FIG PSR-12 to maintain consistency.
- C does not have standard naming rules, but there are generally accepted conventions.


| Feature                     | C                         | PHP                                   | Java                         |
|:---------------------------:|:-------------------------:|:-------------------------------------:|:----------------------------:|
| **Indentation**             | Free, but { } required    | Free, but { } required                | Free, but { } required       |
| **Classes Nomenclature**    | PascalCase                | PascalCase                            | PascalCase                   |
| **Functions Nomenclature**  | camelCase                 | snake_case                            | snake_case or camelCase      |
| **Variables Nomenclature**  | camelCase variables       | snake_case                            | snake_case or camelCase      |
| **Constants Nomenclature**  | UPPER_SNAKE_CASE          | UPPER_SNAKE_CASE                      | UPPER_SNAKE_CASE             |
| **Maximum line length**     | 120 characters            | Not specified, depends on the project | 120 characters (recommended) |


Python's writing style was defined in [PEP8](https://peps.python.org/pep-0008/). PEPs (*Python Enhancements Proposals*) are community-backed proposals for language improvement.

[PEP8](https://peps.python.org/pep-0008/) defines some guidelines, including:
- Indentation: 4 spaces.
- Maximum line length: 79 characters
- Classes and functions surrounded by 2 blank lines
- Class names in CamelCase
- Function and variable names in LowerCase
- Constants in UpperCase

Another interesting [PEP](https://peps.python.org/pep-0000/) to read is [PEP20](https://peps.python.org/pep-0020/), where is described *the zen of Python*: check it out if you are curious!