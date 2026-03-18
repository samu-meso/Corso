class Book:
    def __init__(self, id: int, title: str, author: str, price: float, pages: int, genre: str):
        self.id = id
        self.title = title
        self.author = author
        self.price = price
        self.pages = pages,
        self.genre = genre

    def __repr__(self):
        return f"Book(id={self.id}, title='{self.title}', author='{self.author}')"

    def serialize(self):
        return {
            "id": self.id,
            "title": self.title,
            "author": self.author,
            "price": self.price,
            "pages": self.pages,
            "genre": self.genre
        }
