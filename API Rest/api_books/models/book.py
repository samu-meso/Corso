# models/book.py

class Book:
    def __init__(self, id: int, title: str, author: str, genre: str, price: float, pages: int):
        self.id = id
        self.title = title
        self.author = author
        self.genre = genre
        self.price = price
        self.pages = pages

    def __repr__(self):
        return f"Book(id={self.id}, title='{self.title}', author='{self.author}', genre='{self.genre}')"

    def serialize(self):
        return {
            'id': self.id,
            'title': self.title,
            'author': self.author,
            'genre': self.genre,
            'price': self.price,
            'pages': self.pages
        }
