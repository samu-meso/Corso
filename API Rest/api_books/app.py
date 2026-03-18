from flask import Flask, jsonify
from models.book import Book

app = Flask(__name__)

books = [
    Book(1, "Il Nome della Rosa", "Umberto Eco", 15.90, 502, "classic"),
    Book(2, "Il Signore degli Anelli", "J.R.R. Tolkien", 22.50, 1200, "fantasy"),
    Book(3, "il dio ghane", "J.R.R. Tolkien", 22.50, 1200, "d"),
    Book(4, "Come una notte a Bali", "Gianluca Gotto", 14.25, 264, "narrative"),
    Book(5, "Come una notte a Bali2", "Gianluca Gotto", 14.25, 264, "narrative"),
]


@app.route("/", methods=["GET"])
def homepage():
    return "<h1>API Books</h1>"


@app.route("/api/books", methods=["GET"])
def get_all_books():
    result = [b.serialize() for b in books]
    return jsonify(result)


@app.route("/api/books/<int:id>", methods=["GET"])
def get_book(id):
    book = next((b for b in books if b.id == id), None)
    if book is None:
        return jsonify({"error": "Book not found"}), 404
    return jsonify(book.serialize())


@app.route("/api/books/author/<string:author>", methods=["GET"])
def get_book_author(author):
    if author is None or len(author) < 3:
        return jsonify({"error": "Author must be > 3 and is required"}), 404
    result = [b.serialize() for b in books if b.author.lower() == author.lower()]
    if not result:
        return jsonify({"error": "No book found with named author"}), 404

    return jsonify(result), 200


@app.route("/api/books/search/<string:text>", methods=["GET"])
def search_books(text):
    if text is None or len(text) < 3:
        return jsonify({"error": "Text must be > 3 and is required"}), 404

    result = [
        b.serialize()
        for b in books
        if text.lower() in b.title.lower()
        or text.lower() in b.author.lower()
        or text.lower() in b.genre.lower()
    ]
    if not result:
        return jsonify({'error': f'No books found in seartch term {text}'}), 404
    return jsonify(result)


if __name__ == "__main__":
    app.run(debug=True, port=5001)
