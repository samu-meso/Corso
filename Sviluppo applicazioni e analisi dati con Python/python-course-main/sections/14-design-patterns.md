# Design Patterns

Design patterns are standard solutions to common problems in software engineering. In other words, they can be considered as ***emerged and collected software design best practices***. They are reusable patterns that describe how to solve specific design problems in an efficient and scalable way.

The concept originates in the book "***Design Patterns: Elements of Reusable Object-Oriented Software***" by Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides (known as the Gang of Four).

Characteristics:

- **Reusability**: applicable in similar contexts;
- **Language Agnostic** : adaptable to different programming languages;
- **Documentation**: they provide shared terminology for developers.

Benefits of Design Patterns:

1. Effectiveness: They offer tested solutions to common development problems
2. Maintainability: They promote modular and readable design
3. Collaboration: They facilitate communication between developers thanks to a common language
4. Flexibility: They allow you to address software changes and extensions in a structured way

When to use them?

- During the design phase of an application.
- When a recurring problem is encountered.
- To ensure consistency and quality in code writing.

## Design Patterns: Classification and Catalog

Classic Design Patterns are divided into 3 main categories:
- ***Creational patterns***
- ***Structural patterns***
- ***Behavioral patterns***

Each Design Pattern is characterized by:
- A recurring **problem**
- A recognized **solution**
- A **description of the pattern** using visualization tools (e.g., Class Diagram)
- Possibly, **pseudocode**

The best resource for documentation is [this](https://refactoring.guru/design-patterns). In the following, there are some practical examples.

## ***Singleton*** Design Pattern

- **Problem**: How can I ensure that only one instance of an object exists in my entire program?
- **Solution**: Implement the associated class according to the ***Singleton*** pattern.

```python
class Singleton:
    _instance = None
    def __new__(cls, *args, **kwargs):
        if not cls._instance:
            cls._instance = super().__new__(cls)
        return cls._instance

# Usage
singleton1 = Singleton()
singleton2 = Singleton()
print(singleton1 is singleton2)  # True
```

## ***Factory*** Design Pattern

- **Problem**: How can I create objects without specifying implementation details or concerning the concrete class?
- **Solution**: implement a class that follows the ***Factory*** pattern.

```python
from abc import ABC, abstractmethod


# Common Interface
class Shape(ABC):
    @abstractmethod
    def draw(self):
        pass


# Concrete Implementations
class Circle(Shape):
    def draw(self):
        return "Drawing a Circle"

class Square(Shape):
    def draw(self):

class Triangle(Shape):
    def draw(self):
        return "Drawing a Triangle"


# Class Factory Class
class ShapeFactory:
    @staticmethod
    def get_shape(shape_type):
        if shape_type == "circle":
            return Circle()
        elif shape_type == "square":
            return Square()
        elif shape_type == "triangle":
            return Triangle()
        else:
            raise ValueError(f"Unknown shape type: {shape_type}")


# Utilizzo del Pattern Factory
shape = ShapeFactory.get_shape("circle")
print(shape.draw())  # Output: Drawing  a Circle

shape = ShapeFactory.get_shape("triangle")
print(shape.draw())  # Output: Drawing a Triangle
```

Typically, the classes of the produced objects have a ***common interface*** or ***base class*** (in this case, `Shape`). The `Factory` is responsible for ***creating and returning instances*** based on the input received, through a `@staticmethod` (or a dedicated class). 

The code that uses the Factory (or client) ***does not know the implementation details behind it***.

## ***Observer*** Design Pattern

- **Problem**: How can I perform an action at a specific point in a program when some element changes in another?
- **Solution**: Implement a class that follows the ***Observer*** pattern.

```python
class Subject:
    def __init__(self):
        self._observers = []
    def add_observer(self, observer):
        self._observers.append(observer)
    def notify(self):
        for observer in self._observers:
            observer.update()

class Observer: 
    def update(self):
        print('Observer notified!')

# Usage
subject = Subject()
observer = Observer()
subject.attach(observer)
subject.notify()  # Output: "Observer notified!"
```

## Design Patterns: Let's Experiment!

### Exercise 1

Provide the description "A single object is needed to manage application configurations accessible from anywhere, avoiding multiple instances." Identify the pattern, justify its suitability in 4-6 lines, list 2 benefits and 1 potential drawback, then write 6-10 lines in Python showing that two variables point to the same instance.

Criteria:

- Correct terminology (problem/solution/benefits) and pattern category indicated.
- Code with identity checking that prints True for the same instance and no additional public constructors.

--

### Exercise 2

Given the lesson fragment with Shape/Factory, complete the Square draw method and add a new "hexagon" shape without modifying the client code; update the Factory to return the correct instance; write three quick tests demonstrating the creation of a circle, square, and hexagon, all of which must expose draw().

Criteria:

- A common interface/base must be present and creation must be delegated to the Factory, without instantiating concrete classes in the client.
- Extendability: Adding "hexagon" does not break existing code; the Factory handles invalid input by throwing a meaningful exception.

--

### Exercise 4

Implement `Subject` with `add_observer`, `remove_observer`, and `notify` methods; define two separate `Observers` that print different messages when received; create a small `event` in `Subject` (e.g., `set_state(value)`) that calls `notify` when the value changes; demonstrate with a script that with two `Observers`, both receive the notification, then remove the first and show that only the second receives the next notification.

Criteria:

- Clear separation between provider (`Subject`) and `Observers`, with a container to track subscriptions and no guaranteed notification order.

Minimal Observer API with an `update`/`receive` method and a call to notify when the state changes; error-free removal management.
