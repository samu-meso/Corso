from flask import Blueprint, abort, jsonify, request
from fake_database import get_all_books, get_book_by_id, get_books_by_author, insert_book, search_books_by_text, update_book
from models.book import Book

# Creiamo il Blueprint
book_controller = Blueprint('book_controller', __name__)



@book_controller.route('/api/books', methods=['GET'])
def api_get_all_books():
    result = get_all_books()
    return jsonify(result)

@book_controller.route('/api/books/<int:id>', methods=['GET'])
def api_get_book_by_id(id:int):
    # for b in books:
    #     if b.id == id:
    #         return jsonify(b.serialize())
    book = get_book_by_id(id)
    if book is None:
        #return jsonify({'error': f'Book with ID {id} not found'}), 404
        abort(404, f'Book with ID {id} not found')
    return jsonify(book.serialize())

@book_controller.route('/api/books/author/<author>', methods=['GET'])
def api_get_books_by_author(author:str):
    if author is None or len(author)< 3:
        return jsonify({'error': 'Author parameter is required and must be at least 3 characters long'}), 400 #Bad request
    #questo funziona quando il testo passato corrispnde esattamente ad un author,
    #result = [b.serialize() for b in books if b.author.lower() == author.lower()]
    #ma sarebbe meglio se fosse più flessibile, ad esempio cercando se il testo è contenuto nell'author
    result = get_books_by_author(author)
    if not result:
        #return jsonify({'error': f'No books found for author {author}'}), 404
        abort(404, f'No books found for author {author}')
    return jsonify(result)

@book_controller.route('/api/books/search/<text>', methods=['GET'])
def api_search_books_by_text(text:str):
    if text is None or len(text) < 3:
        return jsonify({'error': 'Search text is required and must be at least 3 characters long'}), 400
    result = search_books_by_text(text)
    if not result:
        #return jsonify({'error': f'No books found for search term {text}'}), 404
        abort(404, f'No books found for search term {text}')
    return jsonify(result)

@book_controller.route('/api/books', methods=['POST'])
def api_insert_book():
    data = request.json #dati passati dal client in formato json, che devono essere convertiti in un oggetto Book
    #validazione dei dati
    if 'title' not in data or len(data['title']) <= 3:
        abort(400, 'Title is required and must be at least 3 characters long')
        
    if 'author' not in data or len(data['author']) <= 3:
        abort(400, 'Author is required and must be at least 3 characters long')
        
    if 'genre' not in data or len(data['genre']) <= 3:
        abort(400, 'Genre is required and must be at least 3 characters long')
    
    try:
        price = float(data['price'])
        if price <= 0:
            abort(400, 'Price must be a positive number')
    except (ValueError, KeyError):
        abort(400, 'Price is required and must be a valid number')
        
    try:
        pages = int(data['pages'])
        if pages <= 0:
            abort(400, 'Pages must be a positive number')
    except (ValueError, KeyError):
        abort(400, 'Pages is required and must be a valid number')
        
    #creazione del nuovo libro
    new_book = Book(0, data['title'], data['author'], data['genre'], price, pages)
    new_id = insert_book(new_book)
    if new_id is None:
        abort(500, 'An error occurred while inserting the book, retry later or contact support')
    return jsonify({'message': 'Book inserted successfully', 'id': new_id}), 201



@book_controller.route('/api/books/<int:id>', methods=['PUT'])
def api_put_book_by_id(id:int):
    #carico il libro per controllare che esista
    book = get_book_by_id(id)
    if book is None:
        abort(404, f'Book with ID {id} not found')
    data = request.json
    #validazione dei dati singola, un solo errore per volta, se ci sono più errori, 
    # restituisco solo il primo che incontro
    # if 'title' not in data or len(data['title']) <= 3:
    #     abort(400, 'Title is required and must be at least 3 characters long')
        
    # if 'author' not in data or len(data['author']) <= 3:
    #     abort(400, 'Author is required and must be at least 3 characters long')
        
    # if 'genre' not in data or len(data['genre']) <= 3:
    #     abort(400, 'Genre is required and must be at least 3 characters long')
    
    # try:
    #     price = float(data['price'])
    #     if price <= 0:
    #         abort(400, 'Price must be a positive number')
    # except (ValueError, KeyError):
    #     abort(400, 'Price is required and must be a valid number')
        
    # try:
    #     pages = int(data['pages'])
    #     if pages <= 0:
    #         abort(400, 'Pages must be a positive number')
    # except (ValueError, KeyError):
    #     abort(400, 'Pages is required and must be a valid number')
    
    #validazione dei dati complessiva, un solo errore per tutti i campi. 
    # Se ci sono più errori, restituisco tutti gli errori in un unico messaggio
    errors = []
    if 'title' not in data or len(data['title']) <= 3:
        errors.append('Title is required an must be at least 3 characters long')
    
    if 'author' not in data or len(data['author']) <= 3:
        errors.append('Author is required and must be at least 3 characters long')
    
    if 'genre' not in data or len(data['genre']) <= 3:
        errors.append('Genre is required and must be at least 3 characters long')


    try:
        if 'price' in data:
            price = float(data['price'])
            if price <= 0:
                errors.append('Price must be a positive number')
        else:
            errors.append('Price is required and must be a positive number')

    except (ValueError, KeyError):
            errors.append('Price is required and must be a positive number')

    try:
        if 'pages' in data:
            pages = int(data['pages'])
            if pages <= 0:
                errors.append('Pages must be a positive number')
        else:
            errors.append('Pages is required and must be a positive number')
    except (ValueError, KeyError):
        errors.append('Pages is required and must be a positive number')



    if errors:
        abort(400, {'errors': errors})

    #update del libro   
    update_result = update_book(book, data)
    if not update_result:
        abort(500, 'An error occurred while updating the book, retry later or contact support')
    return jsonify({'message': 'Book updated successfully'}),204 #ok, but no content, non restituiamo nulla al client, ma è comunque un successo


@book_controller.route('/api/books/<int:id>', methods=['PATCH'])
def api_patch_book_by_id(id:int):
    #carico il libro per controllare che esista
    book = get_book_by_id(id)
    if book is None:
        abort(404, f'Book with ID {id} not found')
    data = request.json
    
    if not data:
        abort(400, 'No data provided for update')
        
    errors = []
    if 'title' in data and len(data['title']) <= 3:
        errors.append('Title must be at least 3 characters long')
    if 'author' in data and len(data['author']) <= 3:
        errors.append('Author must be at least 3 characters long')
    if 'genre' in data and len(data['genre']) <= 3:
        errors.append('Genre must be at least 3 characters long')
    if 'price' in data:
        try:
            price = float(data['price'])
            if price <= 0:
                errors.append('Price must be a positive number')
        except (ValueError, KeyError):
            errors.append('Price must be a valid number')
    if 'pages' in data:
        try:
            pages = int(data['pages'])
            if pages <= 0:
                errors.append('Pages must be a positive number')
        except (ValueError, KeyError):
            errors.append('Pages must be a valid number')   
    
    if errors:
        abort(400, {'errors': errors})
        
    #update del libro
    update_result = update_book(book, data)
    if not update_result:
        abort(500, 'An error occurred while updating the book, retry later or contact support')
    return jsonify({'message': 'Book updated successfully'}),204 #ok, but no content