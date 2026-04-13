# fake_database.py
from typing import List, Optional
from models.book import Book
from helpers.file_helper import save_to_file, load_from_file

FILENAME = 'books.json'

# Lista in memoria — simulazione del database
books_db: List[Book] = []


def load_books():
    """Carica i libri dal file JSON. Se il file non esiste, crea i dati di default."""
    global books_db
    try:
        data = load_from_file(FILENAME)
        books_db = [Book(**b) for b in data]
    except FileNotFoundError:
        _create_default_data()


def _create_default_data():
    """Crea dati iniziali se il file non esiste."""
    global books_db
    books_db = [
        Book(1, 'Il Nome della Rosa', 'Umberto Eco','Mistero', 15.90, 502),
        Book(2, 'Il Signore degli Anelli', 'J.R.R. Tolkien', 'Fantasy', 22.50, 1200),
        Book(3, '1984', 'George Orwell', 'Distopico', 12.00, 328),
        Book(4, 'Il Grande Gatsby', 'F. Scott Fitzgerald', 'Classico', 10.50, 180),
        Book(5, 'La Divina Commedia', 'Dante Alighieri', 'Classico', 18.00, 432),
        Book(6, 'Il Nome della Rosa II', 'Umberto Eco','Mistero', 15.91, 1),
    ]
    save_books()


def save_books():
    """Salva tutti i libri su file JSON."""
    data = [b.serialize() for b in books_db]
    save_to_file(data, FILENAME)


def get_all_books() -> List[Book]:
    return [b.serialize() for b in books_db] #qui un giorno andremo a mettere le query per recuperare i dati da db reale

def get_book_by_id(book_id: int) -> Optional[Book]:
    return next((b for b in books_db if b.id == book_id), None)

def get_books_by_author(author: str) -> List[Book]:
    return [b.serialize() for b in books_db if author.lower() in b.author.lower()]

def search_books_by_text(text: str) -> List[Book]:
    text = text.lower()
    return [b.serialize() for b in books_db if text in b.title.lower() 
            or text in b.author.lower() 
            or text in b.genre.lower()]
    
def insert_book(book: Book) -> Optional[int]:
    try:
        new_id = max((b.id for b in books_db), default=0) + 1
        book.id = new_id
        books_db.append(book)
        save_books()
        return new_id
    except Exception:
        return None
    
def update_book(book: Book, data: dict) -> bool:
    if 'title' in data:
        book.title = data['title']
    if 'author' in data:
        book.author = data['author']
    if 'genre' in data:
        book.genre = data['genre']
    if 'price' in data:
        book.price = float(data['price'])
    if 'pages' in data:
        book.pages = int(data['pages'])
    try:
        save_books()
        return True
    except Exception as ex:
        print(ex)
        return False

def delete_book(id: int) -> bool:
    global books_db
    try:
        len_before = len(books_db)
        books_db = [b for b in books_db if b.id !=id]
        len_after = len(books_db)
        if len_before == len_after:
            return False
        save_books()
        return True
    except Exception as ex:
        print(ex)
        return False

